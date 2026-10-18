package com.nhlstenden.vegetablegarden.vegetable;

public abstract class Vegetable
{
    private double sizeInCm;
    private double cmPerLux;
    private double cmPerMillimeter;
    private int ripeLength;
    private Color color;
    private Status status;

    public Vegetable(double cmPerLux, double cmPerMillimeter,
                     int ripeLength, Color color)
    {
        this.setCmPerLux(cmPerLux);
        this.setCmPerMillimeter(cmPerMillimeter);
        this.setRipeLength(ripeLength);
        this.setColor(color);
        this.setStatus(Status.GROWING);
    }

    public double getSizeInCm()
    {
        return this.sizeInCm;
    }

    public void setSizeInCm(double sizeInCm)
    {
        if (sizeInCm < 0)
        {
            throw new IllegalArgumentException("Size cannot be  negative");
        }
        this.sizeInCm = sizeInCm;
    }

    public double getCmPerLux()
    {
        return this.cmPerLux;
    }

    public void setCmPerLux(double cmPerLux)
    {
        if (cmPerLux <= 0)
        {
            throw new IllegalArgumentException("cmPerLux cannot be 0 or negative");
        }
        this.cmPerLux = cmPerLux;
    }

    public double getCmPerMillimeter()
    {
        return this.cmPerMillimeter;
    }

    public void setCmPerMillimeter(double cmPerMillimeter)
    {
        if (cmPerMillimeter <= 0)
        {
            throw new IllegalArgumentException("cmPerMillimeter cannot be 0 or negative");
        }
        this.cmPerMillimeter = cmPerMillimeter;
    }

    public int getRipeLength()
    {
        return this.ripeLength;
    }

    public void setRipeLength(int ripeLength)
    {
        if (ripeLength <= 0)
        {
            throw new IllegalArgumentException("Length cannot be 0 or negative");
        }
        this.ripeLength = ripeLength;
    }

    public Color getColor()
    {
        return this.color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public Status getStatus()
    {
        return this.status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public abstract void grow(int lux, int mm, int amountOfDays);

    
    protected void applyGrowth(int lux, int mm, int amountOfDays)
    {
        this.setSizeInCm(this.getSizeInCm()
                + (this.getCmPerLux() * lux
                + this.getCmPerMillimeter() * mm) * amountOfDays);

        if (this.getSizeInCm() >= this.getRipeLength())
        {
            this.setStatus(Status.READYTOHARVEST);
        }
    }

    public boolean isDestroyed()
    {
        return this.status == Status.DESTROYED;
    }

    public abstract boolean isMarketable();

    public abstract double getPricePerCm();

    public double getPrice()
    {
        return this.getPricePerCm() * this.getSizeInCm();
    }
}
