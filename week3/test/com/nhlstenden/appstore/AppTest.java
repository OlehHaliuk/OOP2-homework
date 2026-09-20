package com.nhlstenden.appstore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest
{
    @Test
    void unrestrictedAppHasZeroAgeRestriction()
    {
        App app = new App("Notes", 0, false, false);
        assertEquals(0, app.getAgeRestriction());
    }

    @Test
    void violentAppRequiresSixteen()
    {
        App app = new App("Action", 5, true, false);
        assertEquals(16, app.getAgeRestriction());
    }

    @Test
    void nudityRequiresEighteenEvenWhenAppAlsoContainsViolence()
    {
        App nudityOnly = new App("Adult", 5, false, true);
        App both = new App("Both", 5, true, true);
        assertEquals(18, nudityOnly.getAgeRestriction());
        assertEquals(18, both.getAgeRestriction());
    }
}
