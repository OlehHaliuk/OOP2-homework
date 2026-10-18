package com.nhlstenden.vegetablegarden.vegetable;

public class Kale extends Vegetable
{
    public Kale()
    {
        super(0.0057, 0.032, 16, Color.YELLOW);
    }

    @Override
    public void grow(int lux, int mm, int amountOfDays)
    {
        if (this.isDestroyed())
        {
            return;
        }
        
        if (amountOfDays < 6 || mm <= 40)
        {
            this.applyGrowth(lux, mm, amountOfDays);
        }
    }

    @Override
    public boolean isMarketable()
    {
        return false;
    }

    @Override
    public double getPricePerCm()
    {
        return 0.0;
    }
}
