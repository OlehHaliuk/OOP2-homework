package com.nhlstenden.appstore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AppStore
{
    private Currency currency;
    private List<Purchase> purchases;
    private List<App> apps;
    private List<User> users;

    protected AppStore(Currency currency)
    {
        this.currency = Objects.requireNonNull(currency, "currency");
        this.purchases = new ArrayList<>();
        this.apps = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public Currency getCurrency()
    {
        return currency;
    }

    public List<Purchase> getPurchases()
    {
        return List.copyOf(purchases);
    }

    public List<App> getApps()
    {
        return List.copyOf(apps);
    }

    public List<User> getUsers()
    {
        return List.copyOf(users);
    }

    public void registerUser(User user)
    {
        Objects.requireNonNull(user, "user");
        if (!users.contains(user))
        {
            users.add(user);
        }
    }

    public void addApp(App app)
    {
        Objects.requireNonNull(app, "app");
        if (!apps.contains(app))
        {
            apps.add(app);
        }
    }

    public Purchase buyApp(App app, User user) throws DownloadNotAllowedException
    {
        if (app == null || !apps.contains(app))
        {
            throw new IllegalArgumentException("App is not available in this store");
        }
        if (user == null || !users.contains(user))
        {
            throw new IllegalArgumentException("User is not registered in this store");
        }
        checkAge(app, user);

        Purchase purchase = new Purchase(LocalDate.now(), app, user);
        purchases.add(purchase);
        return purchase;
    }

    private void checkAge(App app, User user) throws DownloadNotAllowedException
    {
        if (user.getAge() < app.getAgeRestriction())
        {
            throw new DownloadNotAllowedException(
                    "User is too young to download " + app.getName()
            );
        }
    }

    public double getAppRevenue(App app)
    {
        if (app == null || !apps.contains(app))
        {
            throw new IllegalArgumentException("App is not available in this store");
        }
        double grossSales = 0.0;
        for (Purchase purchase : purchases)
        {
            if (purchase.getApp() == app)
            {
                grossSales += purchase.getApp().getPrice();
            }
        }
        return grossSales * getRevenueShare();
    }

    public double getTotalRevenue()
    {
        double grossSales = 0.0;
        for (Purchase purchase : purchases)
        {
            grossSales += purchase.getApp().getPrice();
        }
        return grossSales * getRevenueShare();
    }

    public abstract double getRevenueShare();


}
