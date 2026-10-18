package com.nhlstenden.vegetablegarden.vegetable;

public class Asparagus extends Vegetable
{

    public Asparagus()
    {
        super(0.000027, 0.0049, 20, Color.BLUE);
    }

    @Override
    public void grow(int lux, int mm, int amountOfDays)
    {
        if (this.isDestroyed())
        {
            return;
        }
        
        if (mm < 14 && amountOfDays >= 10)
        {
            this.setStatus(Status.DESTROYED);
        }
        else
        {
            this.applyGrowth(lux, mm, amountOfDays);
        }
    }

    @Override
    public boolean isMarketable()
    {
        return this.getStatus() == Status.READYTOHARVEST && this.getSizeInCm() <= 29;
    }

    @Override
    public double getPricePerCm()
    {
        return 0.12;
    }
}
