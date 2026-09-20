package com.nhlstenden.appstore;

public class App
{
    private String name;
    private double price;
    private boolean violence;
    private boolean nudity;

    public App(String name, double price, boolean violence, boolean nudity)
    {
        this.setName(name);
        this.setPrice(price);
        this.setNudity(nudity);
        this.setViolence(violence);
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }

        this.name = name;
    }

    public double getPrice()
    {
        return this.price;
    }

    public void setPrice(double price)
    {
        if (price < 0)
        {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public boolean isViolence()
    {
        return this.violence;
    }

    public void setViolence(boolean violence)
    {
        this.violence = violence;
    }

    public boolean isNudity()
    {
        return this.nudity;
    }

    public void setNudity(boolean nudity)
    {
        this.nudity = nudity;
    }

    public int getAgeRestriction()
    {
        if (nudity)
        {
            return 18;
        }
        if (violence)
        {
            return 16;
        }
        return 0;
    }
}
