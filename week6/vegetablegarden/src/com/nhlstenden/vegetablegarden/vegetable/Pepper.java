package com.nhlstenden.vegetablegarden.vegetable;

public class Pepper extends Vegetable
{

    public Pepper()
    {
        super(0.000044, 0.0013, 7, Color.PINK);
    }

    @Override
    public void grow(int lux, int mm, int amountOfDays)
    {
        if (this.isDestroyed())
        {
            return;
        }
        
        if (amountOfDays > 4 && mm > 31)
        {
            this.setStatus(Status.DESTROYED);
        }
        else if (amountOfDays < 10 || mm >= 25)
        {
            this.applyGrowth(lux, mm, amountOfDays);
        }
    }

    @Override
    public boolean isMarketable()
    {
        return this.getStatus() == Status.READYTOHARVEST && this.getSizeInCm() <= 21 && this.getSizeInCm() >= 15;
    }

    @Override
    public double getPricePerCm()
    {
        return 0.15;
    }
}
