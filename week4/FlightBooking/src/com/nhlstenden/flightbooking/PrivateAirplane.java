package com.nhlstenden.flightbooking;

import java.util.Locale;
import java.util.Objects;

public class PrivateAirplane extends Airplane
{
    private int numberOfSeats;
    private int seatsTaken;
    private static final double FUEL_PER_SEAT = 1.31;
    private static final double FUEL_PER_TAKEN_SEAT = 1.87;
    private static final double FUEL_PER_LUGGAGE_KG = 0.4;

    public PrivateAirplane(String code, double fuelLevel, int numberOfSeats)
    {
        super(code, fuelLevel);
        this.setNumberOfSeats(numberOfSeats);
    }

    public int getNumberOfSeats()
    {
        return this.numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats)
    {
        if (numberOfSeats <= 0 || numberOfSeats < this.seatsTaken)
        {
            throw new IllegalArgumentException("Capacity must be positive and fit existing reservations");
        }
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public int getTotalSeats()
    {
        return this.numberOfSeats;
    }

    @Override
    public int getEmptySeats()
    {
        return this.numberOfSeats - this.seatsTaken;
    }

    @Override
    public void reserveSeat()
    {
        this.reserveSeat(SeatType.ECONOMY);
    }

    @Override
    SeatType getNextSeatType()
    {
        if (this.getEmptySeats() == 0)
        {
            throw new IllegalStateException("No seats available");
        }
        return SeatType.ECONOMY;
    }

    @Override
    void reserveSeat(SeatType type)
    {
        if (Objects.requireNonNull(type) != SeatType.ECONOMY)
        {
            throw new IllegalArgumentException("Private airplane tickets use ECONOMY");
        }
        this.getNextSeatType();
        this.seatsTaken++;
    }

    @Override
    void changeSeat(SeatType oldType, SeatType newType)
    {
        if (Objects.requireNonNull(newType) != SeatType.ECONOMY)
        {
            throw new IllegalArgumentException("Private airplane tickets use ECONOMY");
        }
    }

    @Override
    public double getFuelConsumption(int distance, double totalLuggageWeight)
    {
        super.getFuelConsumption(distance, totalLuggageWeight);
        return this.numberOfSeats * FUEL_PER_SEAT * distance
                + this.seatsTaken * FUEL_PER_TAKEN_SEAT
                + totalLuggageWeight * FUEL_PER_LUGGAGE_KG;
    }

    @Override
    public String toFlight24String()
    {
        return String.format(Locale.ROOT, "PRIVATE;%s;%.2f;%d;%d",
                this.getCode(), this.getFuelLevel(), this.getTotalSeats(), this.getEmptySeats());
    }
}
