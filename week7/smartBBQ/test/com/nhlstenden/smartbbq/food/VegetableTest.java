package com.nhlstenden.smartbbq.food;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VegetableTest
{
    private static final double DELTA = 0.0001;

    @Test
    void grill_cornAt180Degrees_moistureDecreasesByNinePercentPerSecond()
    {
        Corn corn = new Corn();

        corn.grill(180);
        assertEquals(91, corn.getMoistPercentage(), DELTA);

        corn.grill(180);
        assertEquals(82, corn.getMoistPercentage(), DELTA);
    }

    @Test
    void grill_bellPepperAt180Degrees_moistureDecreasesByEighteenPercentPerSecond()
    {
        BellPepper bellPepper = new BellPepper();

        bellPepper.grill(180);
        assertEquals(82, bellPepper.getMoistPercentage(), DELTA);

        bellPepper.grill(180);
        assertEquals(64, bellPepper.getMoistPercentage(), DELTA);
    }

    @Test
    void grill_bellPepperUntilDry_moistureStopsAtZero()
    {
        BellPepper bellPepper = new BellPepper();

        for (int second = 0; second < 10; second++)
        {
            bellPepper.grill(180);
        }

        assertEquals(0, bellPepper.getMoistPercentage(), DELTA);
    }

    @Test
    void grill_zeroDegrees_moistureUnchanged()
    {
        Corn corn = new Corn();

        corn.grill(0);

        assertEquals(100, corn.getMoistPercentage(), DELTA);
    }
}