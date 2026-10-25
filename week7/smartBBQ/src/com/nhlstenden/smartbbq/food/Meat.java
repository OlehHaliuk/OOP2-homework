package com.nhlstenden.smartbbq.food;

import java.lang.Math;

public abstract class Meat extends Food
{
    private static final double MIN_COOKED_PERCENTAGE = 0;
    private double currentCookedPercentage;
    private double cookingFactor;
    private MeatType meatType;

    public Meat(MeatType type, double cookingFactor, double browningFactor)
    {
        super(browningFactor);
        this.setMeatType(type);
        this.setCookingFactor(cookingFactor);
        this.currentCookedPercentage = 0;
    }

    public double getCurrentCookedPercentage()
    {
        return this.currentCookedPercentage;
    }

    public void setCurrentCookedPercentage(double currentCookedPercentage)
    {
        if (currentCookedPercentage < MIN_COOKED_PERCENTAGE)
        {
            throw new IllegalArgumentException("Cooked percentage cannot be negative");
        }

        this.currentCookedPercentage = currentCookedPercentage;
    }

    public double getCookingFactor()
    {
        return this.cookingFactor;
    }

    public void setCookingFactor(double cookingFactor)
    {
        this.cookingFactor = cookingFactor;
    }

    public MeatType getMeatType()
    {
        return this.meatType;
    }

    public void setMeatType(MeatType meatType)
    {
        this.meatType = meatType;
    }

    @Override
    public void grill(double temperature)
    {
        this.increaseTemperature();
        this.increaseBrownPercentage(this.getBrowningFactor() * temperature / 100);
        this.setCurrentCookedPercentage(
                this.getCurrentCookedPercentage() + temperature / (this.getCookingFactor() * Math.PI));
    }
}
