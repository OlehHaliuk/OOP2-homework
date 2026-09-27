package com.nhlstenden.flightbooking;

import java.util.Locale;
import java.util.Objects;

public class CommercialAirplane extends Airplane
{
    private int economySeats;
    private int businessSeats;
    private int economySeatsTaken;
    private int businessSeatsTaken;
    private static final double FUEL_PER_ECONOMY_SEAT = 1.75;
    private static final double FUEL_PER_BUSINESS_SEAT = 1.98;
    private static final double FUEL_PER_TAKEN_ECONOMY_SEAT = 2.02;
    private static final double FUEL_PER_TAKEN_BUSINESS_SEAT = 2.87;
    private static final double FUEL_PER_LUGGAGE_KG = 0.3;

    public CommercialAirplane(String code, double fuelLevel, int economySeats, int businessSeats)
    {
        super(code, fuelLevel);
        this.checkCapacity(economySeats, businessSeats);
        this.economySeats = economySeats;
        this.businessSeats = businessSeats;
    }

    private void checkCapacity(int economy, int business)
    {
        if (economy < this.economySeatsTaken || business < this.businessSeatsTaken
                || economy < 0 || business < 0 || (long) economy + business <= 0
                || (long) economy + business > Integer.MAX_VALUE)
        {
            throw new IllegalArgumentException("Capacity must be positive and fit existing reservations");
        }
    }

    public int getEconomySeats()
    {
        return this.economySeats;
    }

    public int getBusinessSeats()
    {
        return this.businessSeats;
    }

    public void setEconomySeats(int economySeats)
    {
        this.checkCapacity(economySeats, this.businessSeats);
        this.economySeats = economySeats;
    }

    public void setBusinessSeats(int businessSeats)
    {
        this.checkCapacity(this.economySeats, businessSeats);
        this.businessSeats = businessSeats;
    }

    @Override
    public int getTotalSeats()
    {
        return this.economySeats + this.businessSeats;
    }

    @Override
    public int getEmptySeats()
    {
        return this.getTotalSeats() - this.economySeatsTaken - this.businessSeatsTaken;
    }

    @Override
    SeatType getNextSeatType()
    {
        if (this.economySeatsTaken < this.economySeats)
        {
            return SeatType.ECONOMY;
        }
        if (this.businessSeatsTaken < this.businessSeats)
        {
            return SeatType.BUSINESS;
        }
        throw new IllegalStateException("No seats available");
    }

    @Override
    public void reserveSeat()
    {
        this.reserveSeat(this.getNextSeatType());
    }

    @Override
    void reserveSeat(SeatType type)
    {
        Objects.requireNonNull(type, "Seat type cannot be null");
        if (type == SeatType.ECONOMY && this.economySeatsTaken < this.economySeats)
        {
            this.economySeatsTaken++;
        }
        else if (type == SeatType.BUSINESS && this.businessSeatsTaken < this.businessSeats)
        {
            this.businessSeatsTaken++;
        }
        else
        {
            throw new IllegalStateException("No seats available in " + type);
        }
    }

    @Override
    void changeSeat(SeatType oldType, SeatType newType)
    {
        Objects.requireNonNull(newType, "Seat type cannot be null");
        if (oldType == newType)
        {
            return;
        }
        this.reserveSeat(newType);
        if (oldType == SeatType.ECONOMY)
        {
            this.economySeatsTaken--;
        }
        else
        {
            this.businessSeatsTaken--;
        }
    }

    @Override
    public double getFuelConsumption(int distance, double totalLuggageWeight)
    {
        super.getFuelConsumption(distance, totalLuggageWeight);
        return (this.economySeats * FUEL_PER_ECONOMY_SEAT
                + this.businessSeats * FUEL_PER_BUSINESS_SEAT) * distance
                + this.economySeatsTaken * FUEL_PER_TAKEN_ECONOMY_SEAT
                + this.businessSeatsTaken * FUEL_PER_TAKEN_BUSINESS_SEAT
                + totalLuggageWeight * FUEL_PER_LUGGAGE_KG;
    }

    @Override
    public String toFlight24String()
    {
        return String.format(Locale.ROOT, "COMMERCIAL;%s;%.2f;%d;%d;%d",
                this.getCode(), this.getFuelLevel(), this.economySeats,
                this.businessSeats, this.getEmptySeats());
    }
}
