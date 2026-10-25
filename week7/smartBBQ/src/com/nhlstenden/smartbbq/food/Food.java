package com.nhlstenden.smartbbq.food;

import com.nhlstenden.smartbbq.thermometer.Measurable;

public abstract class Food implements Measurable
{
    private static final double TEMPERATURE_INCREASE_BY_SECOND = 0.5;
    private static final double MIN_BROWNING_PERCENTAGE = 0;
    private double currentBrownPercentage;
    private double browningFactor;
    private double currentTemperature;

    public Food(double browningFactor)
    {
        this.currentBrownPercentage = 0;
        this.setBrowningFactor(browningFactor);
        this.currentTemperature = 0;
    }

    public double getCurrentTemperature()
    {
        return this.currentTemperature;
    }

    public void setCurrentTemperature(double currentTemperature)
    {
        this.currentTemperature = currentTemperature;
    }

    public double getBrowningFactor()
    {
        return this.browningFactor;
    }

    public void setBrowningFactor(double browningFactor)
    {
        this.browningFactor = browningFactor;
    }

    public double getCurrentBrownPercentage()
    {
        return this.currentBrownPercentage;
    }

    public void setCurrentBrownPercentage(double currentBrownPercentage)
    {
        if (currentBrownPercentage < MIN_BROWNING_PERCENTAGE)
        {
            throw new IllegalArgumentException("Browning percentage cannot be negative");
        }
        this.currentBrownPercentage = currentBrownPercentage;
    }

    public abstract void grill(double temperature);

    @Override
    public double getTemperature()
    {
        return this.currentTemperature;
    }

    protected void increaseTemperature()
    {
        this.currentTemperature += TEMPERATURE_INCREASE_BY_SECOND;
    }

    protected void increaseBrownPercentage(double increase)
    {
        this.currentBrownPercentage += increase;
    }

}
