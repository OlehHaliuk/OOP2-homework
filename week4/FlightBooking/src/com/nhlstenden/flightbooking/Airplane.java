package com.nhlstenden.flightbooking;

import java.util.Objects;

public abstract class Airplane
{
    private String code;
    private double fuelLevel;
    private Flight flight;

    public Airplane(String code, double fuelLevel)
    {
        if (code == null || code.isBlank())
        {
            throw new IllegalArgumentException("Code cannot be null or blank");
        }
        this.code = code;
        this.setFuelLevel(fuelLevel);
    }

    public String getCode()
    {
        return this.code;
    }

    public double getFuelLevel()
    {
        return this.fuelLevel;
    }

    public void setFuelLevel(double fuelLevel)
    {
        if (!Double.isFinite(fuelLevel) || fuelLevel < 0)
        {
            throw new IllegalArgumentException("Fuel level must be finite and non-negative");
        }
        this.fuelLevel = fuelLevel;
    }

    public double getFuelConsumption(int distance, double totalLuggageWeight)
    {
        if (distance < 0 || !Double.isFinite(totalLuggageWeight) || totalLuggageWeight < 0)
        {
            throw new IllegalArgumentException("Distance and luggage weight must be non-negative and finite");
        }
        return 0;
    }

    public abstract int getTotalSeats();
    public abstract int getEmptySeats();
    public abstract void reserveSeat();
    public abstract String toFlight24String();

    abstract SeatType getNextSeatType();
    abstract void reserveSeat(SeatType type);
    abstract void changeSeat(SeatType oldType, SeatType newType);

    void assignTo(Flight flight)
    {
        if (this.flight != null)
        {
            throw new IllegalStateException("Airplane already belongs to a flight");
        }
        this.flight = Objects.requireNonNull(flight);
    }
}
