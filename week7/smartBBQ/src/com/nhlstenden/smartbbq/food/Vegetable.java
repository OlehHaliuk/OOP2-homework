package com.nhlstenden.smartbbq.food;

public abstract class Vegetable extends Food
{
    private static final double MAX_MOIST_PERCENTAGE = 100;
    private static final double MIN_MOIST_PERCENTAGE = 0;
    private double moistPercentage;
    private double moistFactor;

    public Vegetable(double moistFactor, double browningFactor)
    {
        super(browningFactor);
        this.setMoistPercentage(100);
        this.setMoistFactor(moistFactor);
    }

    public double getMoistPercentage()
    {
        return this.moistPercentage;
    }

    public void setMoistPercentage(double moistPercentage)
    {
        if (moistPercentage > MAX_MOIST_PERCENTAGE)
        {
            throw new IllegalArgumentException("Percentage cannot be more than 100");
        }
        else if (moistPercentage < MIN_MOIST_PERCENTAGE)
        {
            throw new IllegalArgumentException("Percentage cannot be less than 0");
        }

        this.moistPercentage = moistPercentage;
    }

    public double getMoistFactor()
    {
        return this.moistFactor;
    }

    public void setMoistFactor(double moistFactor)
    {
        this.moistFactor = moistFactor;
    }

    public void decreaseMoistPercentage(double difference)
    {
        this.setMoistPercentage(Math.max(MIN_MOIST_PERCENTAGE, this.getMoistPercentage() - difference));
    }

    @Override
    public void grill(double temperature)
    {
        this.increaseTemperature();
        this.increaseBrownPercentage(this.getBrowningFactor() * temperature);
        this.decreaseMoistPercentage(this.getMoistFactor() * temperature);
    }

}
