package com.nhlstenden.flightbooking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class FlightBookingService
{
    private List<Flight> flights;

    public FlightBookingService(List<Flight> flights)
    {
        this.flights = List.copyOf(Objects.requireNonNull(flights));
    }

    public Flight findAvailableFlight(Airport departure, Airport arrival, LocalDateTime dateTime)
    {
        Objects.requireNonNull(departure);
        Objects.requireNonNull(arrival);
        Objects.requireNonNull(dateTime);
        for (Flight flight : this.flights)
        {
            if (flight.getDepartureAirport() == departure && flight.getArrivalAirport() == arrival
                    && flight.getDepartureDateTime().equals(dateTime)
                    && (flight.getStatus() == FlightStatus.AWAITING_DEPARTURE
                    || flight.getStatus() == FlightStatus.BOARDING)
                    && flight.getAirplane().getEmptySeats() > 0)
            {
                return flight;
            }
        }
        return null;
    }

    public Ticket bookTicket(Passenger passenger, Airport departure, Airport arrival,
                             LocalDateTime dateTime, List<Luggage> luggageList)
    {
        Objects.requireNonNull(passenger);
        // Validate everything before consuming a seat.
        List<Luggage> checkedLuggage = List.copyOf(Objects.requireNonNull(luggageList));
        Flight flight = this.findAvailableFlight(departure, arrival, dateTime);
        if (flight == null)
        {
            throw new IllegalStateException("No available flight for this route and departure time");
        }
        Ticket ticket = new Ticket(passenger, flight, flight.getAirplane().getNextSeatType());
        for (Luggage luggage : checkedLuggage)
        {
            ticket.addLuggage(luggage);
        }
        return ticket;
    }

    public void reserveSeat(Flight flight)
    {
        if (!this.flights.contains(Objects.requireNonNull(flight)))
        {
            throw new IllegalArgumentException("Flight does not belong to this service");
        }
        flight.checkBookingOpen();
        flight.getAirplane().reserveSeat();
    }

    public double getTotalLuggageWeight(List<Luggage> luggageList)
    {
        Objects.requireNonNull(luggageList);
        double total = 0;
        for (Luggage luggage : luggageList)
        {
            total += Objects.requireNonNull(luggage).getWeight();
        }
        return total;
    }
}
