package com.nhlstenden.flightbooking;

public class Passenger
{
    private String name;

    public Passenger(String name)
    {
        this.setName(name);
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
}
