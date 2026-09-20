package com.nhlstenden.appstore;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AppStoreTest
{
    private static final double EPS = 1e-9;

    private static User aged(int age)
    {
        return new User("Sander", "sander@example.com", LocalDate.now().minusYears(age));
    }

    private static App app(String name, double price, boolean violence, boolean nudity)
    {
        return new App(name, price, violence, nudity);
    }

    private static Purchase buy(AppStore store, App app, User user) throws DownloadNotAllowedException
    {
        store.addApp(app);
        store.registerUser(user);
        return store.buyApp(app, user);
    }

    @Test
    void appleRejectsNudityButAllowsNormalApps()
    {
        AppStore apple = new AppleAppStore(Currency.EUROS);
        assertThrows(IllegalArgumentException.class,
                () -> apple.addApp(app("Adult", 3, false, true)));
        apple.addApp(app("Notes", 2, false, false));
        assertEquals(1, apple.getApps().size());
    }

    @Test
    void googleAllowsNudity()
    {
        AppStore google = new GooglePlayStore(Currency.DOLLARS);
        google.addApp(app("Adult", 3, false, true));
        assertEquals(1, google.getApps().size());
    }

    @Test
    void belowMinimumAgePurchaseIsRejectedAndNotRecorded()
    {
        AppStore store = new GooglePlayStore(Currency.EUROS);
        App violent = app("Action", 10, true, false);
        App adult = app("Adult", 10, false, true);
        User fifteen = aged(15);
        User seventeen = aged(17);
        store.addApp(violent);
        store.addApp(adult);
        store.registerUser(fifteen);
        store.registerUser(seventeen);
        assertThrows(DownloadNotAllowedException.class, () -> store.buyApp(violent, fifteen));
        assertThrows(DownloadNotAllowedException.class, () -> store.buyApp(adult, seventeen));
        assertTrue(store.getPurchases().isEmpty());
    }

    @Test
    void exactlyMinimumAgePurchaseSucceedsAndIsRecorded() throws DownloadNotAllowedException
    {
        AppStore store = new GooglePlayStore(Currency.EUROS);
        App violent = app("Action", 10, true, false);
        App adult = app("Adult", 20, false, true);
        User sixteen = aged(16);
        User eighteen = aged(18);
        Purchase first = buy(store, violent, sixteen);
        Purchase second = buy(store, adult, eighteen);
        assertSame(violent, first.getApp());
        assertSame(sixteen, first.getUser());
        assertEquals(LocalDate.now(), second.getDate());
        assertEquals(2, store.getPurchases().size());
    }

    @Test
    void aboveMinimumAgePurchaseSucceeds() throws DownloadNotAllowedException
    {
        AppStore store = new GooglePlayStore(Currency.EUROS);
        App adult = app("Adult", 10, false, true);
        User nineteen = aged(19);
        Purchase purchase = buy(store, adult, nineteen);
        assertSame(nineteen, purchase.getUser());
        assertEquals(1, store.getPurchases().size());
    }

    @Test
    void zeroPurchasesGiveZeroRevenue()
    {
        AppStore store = new AppleAppStore(Currency.EUROS);
        App notes = app("Notes", 10, false, false);
        store.addApp(notes);
        assertEquals(0.0, store.getAppRevenue(notes), EPS);
        assertEquals(0.0, store.getTotalRevenue(), EPS);
    }

    @Test
    void onePurchaseUsesEachStoresRevenueShare() throws DownloadNotAllowedException
    {
        AppStore apple = new AppleAppStore(Currency.EUROS);
        AppStore google = new GooglePlayStore(Currency.DOLLARS);
        App appleApp = app("Notes Apple", 10, false, false);
        App googleApp = app("Notes Google", 10, false, false);
        buy(apple, appleApp, aged(20));
        buy(google, googleApp, aged(20));
        assertEquals(7.0, apple.getAppRevenue(appleApp), EPS);
        assertEquals(7.0, apple.getTotalRevenue(), EPS);
        assertEquals(5.0, google.getAppRevenue(googleApp), EPS);
        assertEquals(5.0, google.getTotalRevenue(), EPS);
    }

    @Test
    void multiplePurchasesAreCountedForSpecificAppAndWholeStore() throws DownloadNotAllowedException
    {
        AppStore google = new GooglePlayStore(Currency.EUROS);
        App first = app("First", 10, false, false);
        App second = app("Second", 5, false, false);
        User sander = aged(25);
        User other = new User("Other", "other@example.com", LocalDate.now().minusYears(30));
        buy(google, first, sander);
        buy(google, second, sander);
        buy(google, first, other);
        assertEquals(3, google.getPurchases().size());
        assertEquals(10.0, google.getAppRevenue(first), EPS);
        assertEquals(2.5, google.getAppRevenue(second), EPS);
        assertEquals(12.5, google.getTotalRevenue(), EPS);
    }
}
