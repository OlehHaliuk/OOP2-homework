package com.nhlstenden.vegetablegarden.market;

import com.nhlstenden.vegetablegarden.vegetable.Vegetable;

import java.util.HashSet;

public class VegetableMarket
{
    private final HashSet<Vegetable> vegetables;

    public VegetableMarket()
    {
        this.vegetables = new HashSet<>();
    }

    public void addVegetable(Vegetable vegetable)
    {
        if (vegetable.isMarketable())
        {
            this.vegetables.add(vegetable);
        }
    }

    public double getRevenue()
    {
        double revenue = 0;
        
        for (Vegetable vegetable : this.vegetables)
        {
            revenue += vegetable.getPrice();
        }

        return revenue;
    }
}