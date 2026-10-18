package com.nhlstenden.vegetablegarden;

import com.nhlstenden.vegetablegarden.vegetable.Asparagus;
import com.nhlstenden.vegetablegarden.vegetable.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AsparagusTest
{

    @Test
    void grow_rainJustUnderLimit_destroyed()
    {
        Asparagus asparagus = new Asparagus();

        asparagus.grow(50000, 13, 10);

        assertEquals(Status.DESTROYED, asparagus.getStatus());
    }

    @Test
    void grow_rainExactlyAtLimit_notDestroyed()
    {
        Asparagus asparagus = new Asparagus();

        asparagus.grow(50000, 14, 10);

        assertNotEquals(Status.DESTROYED, asparagus.getStatus());
    }

    @Test
    void grow_rainJustOverLimit_notDestroyed()
    {
        Asparagus asparagus = new Asparagus();

        asparagus.grow(50000, 15, 10);

        assertNotEquals(Status.DESTROYED, asparagus.getStatus());
    }

    @Test
    void grow_daysJustUnderLimit_notDestroyed()
    {
        Asparagus asparagus = new Asparagus();

        asparagus.grow(50000, 13, 9);

        assertNotEquals(Status.DESTROYED, asparagus.getStatus());
    }

    @Test
    void grow_daysExactlyAtLimit_destroyed()
    {
        Asparagus asparagus = new Asparagus();

        asparagus.grow(50000, 13, 10);

        assertEquals(Status.DESTROYED, asparagus.getStatus());
    }

    @Test
    void grow_daysJustOverLimit_destroyed()
    {
        Asparagus asparagus = new Asparagus();

        asparagus.grow(50000, 13, 11);

        assertEquals(Status.DESTROYED, asparagus.getStatus());
    }

    @Test
    void grow_normalWeather_correctSize()
    {
        Asparagus asparagus = new Asparagus();

        asparagus.grow(50000, 14, 10);

        assertEquals(14.186, asparagus.getSizeInCm(), 0.0001);
    }

    @Test
    void grow_alreadyDestroyed_sizeUnchanged()
    {
        Asparagus destroyed = new Asparagus();
        destroyed.grow(50000, 13, 10);
        double sizeBefore = destroyed.getSizeInCm();

        Asparagus healthy = new Asparagus();

        destroyed.grow(50000, 30, 5);
        healthy.grow(50000, 30, 5);

        assertTrue(healthy.getSizeInCm() > 0);
        assertEquals(sizeBefore, destroyed.getSizeInCm(), 0.0001);
    }

    @Test
    void grow_sizeJustUnderRipeLength_statusGrowing()
    {
        Asparagus asparagus = new Asparagus();
        asparagus.setSizeInCm(19.99);

        asparagus.grow(0, 0, 1);

        assertEquals(Status.GROWING, asparagus.getStatus());
    }

    @Test
    void grow_sizeExactlyAtRipeLength_readyToHarvest()
    {
        Asparagus asparagus = new Asparagus();
        asparagus.setSizeInCm(20);

        asparagus.grow(0, 0, 1);

        assertEquals(Status.READYTOHARVEST, asparagus.getStatus());
    }

    @Test
    void grow_sizeJustOverRipeLength_readyToHarvest()
    {
        Asparagus asparagus = new Asparagus();
        asparagus.setSizeInCm(20.01);

        asparagus.grow(0, 0, 1);

        assertEquals(Status.READYTOHARVEST, asparagus.getStatus());
    }

}