package com.nhlstenden.flightbooking;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ticket
{
    private SeatType seatType;
    private Passenger passenger;
    private Flight flight;
    private List<Luggage> luggage;

    public Ticket(Passenger passenger, Flight flight, SeatType seatType)
    {
        this.passenger = Objects.requireNonNull(passenger);
        this.flight = Objects.requireNonNull(flight);
        this.seatType = Objects.requireNonNull(seatType);
        this.luggage = new ArrayList<>();
        this.flight.addTicket(this);
    }

    public Passenger getPassenger()
    {
        return this.passenger;
    }

    public Flight getFlight()
    {
        return this.flight;
    }

    public SeatType getSeatType()
    {
        return this.seatType;
    }

    public void setSeatType(SeatType seatType)
    {
        Objects.requireNonNull(seatType);
        this.flight.checkBookingOpen();
        this.flight.getAirplane().changeSeat(this.seatType, seatType);
        this.seatType = seatType;
    }

    public void addLuggage(Luggage luggage)
    {
        Objects.requireNonNull(luggage);
        this.flight.checkBookingOpen();
        // Each ticket owns its own luggage objects (composition).
        this.luggage.add(new Luggage(luggage.getWeight(), luggage.getType()));
    }

    public List<Luggage> getLuggageList()
    {
        List<Luggage> copy = new ArrayList<>();
        for (Luggage item : this.luggage)
        {
            copy.add(new Luggage(item.getWeight(), item.getType()));
        }
        return List.copyOf(copy);
    }

    public double getTotalLuggageWeight()
    {
        double total = 0;
        for (Luggage item : this.luggage)
        {
            total += item.getWeight();
        }
        return total;
    }
}
