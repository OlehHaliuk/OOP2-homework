package com.nhlstenden.flightbooking;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Flight
{
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDateTime departureDateTime;
    private FlightStatus status;
    private Airplane airplane;
    private List<Ticket> tickets;

    public Flight(Airport departureAirport, Airport arrivalAirport,
                  LocalDateTime departureDateTime, Airplane airplane)
    {
        this.departureAirport = Objects.requireNonNull(departureAirport);
        this.arrivalAirport = Objects.requireNonNull(arrivalAirport);
        if (departureAirport == arrivalAirport)
        {
            throw new IllegalArgumentException("Departure and arrival airports must differ");
        }
        this.departureDateTime = Objects.requireNonNull(departureDateTime);
        this.airplane = Objects.requireNonNull(airplane);
        this.status = FlightStatus.AWAITING_DEPARTURE;
        this.tickets = new ArrayList<>();
        this.airplane.assignTo(this);
    }

    public Airport getDepartureAirport()
    {
        return this.departureAirport;
    }

    public Airport getArrivalAirport()
    {
        return this.arrivalAirport;
    }

    public LocalDateTime getDepartureDateTime()
    {
        return this.departureDateTime;
    }

    public FlightStatus getStatus()
    {
        return this.status;
    }

    public void setStatus(FlightStatus status)
    {
        Objects.requireNonNull(status);
        if (this.status == status)
        {
            return;
        }
        if (this.status == FlightStatus.AWAITING_DEPARTURE && status == FlightStatus.BOARDING
                || this.status == FlightStatus.DEPARTED && status == FlightStatus.LANDED)
        {
            this.status = status;
        }
        else if (status == FlightStatus.DEPARTED)
        {
            this.depart();
        }
        else
        {
            throw new IllegalStateException("Invalid flight status transition");
        }
    }

    public Airplane getAirplane()
    {
        return this.airplane;
    }

    private double getRequiredFuel()
    {
        double weight = 0;
        for (Ticket ticket : this.tickets)
        {
            weight += ticket.getTotalLuggageWeight();
        }
        return this.airplane.getFuelConsumption(this.departureAirport.getDistanceTo(this.arrivalAirport), weight);
    }

    public boolean canDepart()
    {
        return this.status == FlightStatus.BOARDING
                && this.airplane.getFuelLevel() >= this.getRequiredFuel();
    }

    public void depart()
    {
        if (!this.canDepart())
        {
            throw new IllegalStateException("Flight must be boarding and have enough fuel to depart");
        }
        this.airplane.setFuelLevel(this.airplane.getFuelLevel() - this.getRequiredFuel());
        this.status = FlightStatus.DEPARTED;
    }

    void checkBookingOpen()
    {
        if (this.status != FlightStatus.AWAITING_DEPARTURE && this.status != FlightStatus.BOARDING)
        {
            throw new IllegalStateException("Flight is closed for booking");
        }
    }

    void addTicket(Ticket ticket)
    {
        this.checkBookingOpen();
        this.airplane.reserveSeat(ticket.getSeatType());
        this.tickets.add(ticket);
    }

    public String toFlight24String()
    {
        return "FLIGHT;" + this.airplane.getCode() + ";" + this.departureAirport + ";"
                + this.arrivalAirport + ";" + this.departureDateTime + ";" + this.status;
    }
}
