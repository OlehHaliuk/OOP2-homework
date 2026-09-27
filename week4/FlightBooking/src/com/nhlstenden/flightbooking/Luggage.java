package com.nhlstenden.flightbooking;

import java.util.Objects;

public class Luggage
{
    private double weight;
    private LuggageType type;

    public Luggage(double weight, LuggageType type)
    {
        this.setWeight(weight);
        this.setType(type);
    }

    public double getWeight()
    {
        return this.weight;
    }

    public void setWeight(double weight)
    {
        if (!Double.isFinite(weight) || weight < 0)
        {
            throw new IllegalArgumentException("Weight must be finite and non-negative");
        }
        this.weight = weight;
    }

    public LuggageType getType()
    {
        return this.type;
    }

    public void setType(LuggageType type)
    {
        this.type = Objects.requireNonNull(type, "Luggage type cannot be null");
    }
}
