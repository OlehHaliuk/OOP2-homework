package com.nhlstenden.vegetablegarden.garden;

import com.nhlstenden.vegetablegarden.exception.TooManyVegetablesException;
import com.nhlstenden.vegetablegarden.vegetable.Status;
import com.nhlstenden.vegetablegarden.vegetable.Vegetable;

import java.util.HashSet;

public class Garden
{
    public static final int MAX_VEGETABLES = 27;
    private final HashSet<Vegetable> vegetables;

    public Garden()
    {
        this.vegetables = new HashSet<>();
    }

    public HashSet<Vegetable> getVegetables()
    {
        return new HashSet<>(this.vegetables);
    }

    public void addVegetable(Vegetable vegetable) throws TooManyVegetablesException
    {
        if (this.vegetables.size() >= MAX_VEGETABLES)
        {
            throw new TooManyVegetablesException("You reached plant limit");
        }
        
        this.vegetables.add(vegetable);
    }

    public void weather(int lux, int mm, int amountOfDays)
    {
        for (Vegetable vegetable : this.vegetables)
        {
            vegetable.grow(lux, mm, amountOfDays);
        }
    }

    public HashSet<Vegetable> getReadyForHarvest()
    {
        HashSet<Vegetable> harvest = new HashSet<>();

        for (Vegetable vegetable : this.vegetables)
        {
            if (vegetable.getStatus() == Status.READYTOHARVEST)
            {
                harvest.add(vegetable);
            }
        }

        this.vegetables.removeAll(harvest);

        return harvest;
    }
}
