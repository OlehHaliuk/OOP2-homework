package com.nhlstenden.flightbooking;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FlightBookingTest
{
    private static final LocalDateTime DATE = LocalDateTime.of(2026, 10, 15, 10, 30);
    private static final double EPS = 0.000001;

    private Flight flight(Airplane airplane)
    {
        return new Flight(Airport.AMS, Airport.JFK, DATE, airplane);
    }

    private Ticket book(FlightBookingService service)
    {
        return service.bookTicket(new Passenger("Oleh"), Airport.AMS, Airport.JFK, DATE,
                List.of(new Luggage(10, LuggageType.HOLD)));
    }

    @Test
    void airportDistancesAreSymmetricAndIncludeAmsterdam()
    {
        assertEquals(5848, Airport.JFK.getDistanceTo(Airport.AMS));
        for (Airport first : Airport.values())
        {
            for (Airport second : Airport.values())
            {
                assertEquals(first.getDistanceTo(second), second.getDistanceTo(first));
                assertEquals(first == second, first.getDistanceTo(second) == 0);
            }
        }
        assertThrows(NullPointerException.class, () -> Airport.AMS.getDistanceTo(null));
    }

    @Test
    void privateFuelIncludesOccupiedSeatsAndLuggage()
    {
        PrivateAirplane airplane = new PrivateAirplane("P1", 10000, 3);
        airplane.reserveSeat();
        assertEquals(2, airplane.getEmptySeats());
        assertEquals(3 * 1.31 * 100 + 1.87 + 20 * 0.4,
                airplane.getFuelConsumption(100, 20), EPS);
    }

    @Test
    void commercialBookingUsesEconomyThenBusinessWithoutDoubleReservation()
    {
        Airplane airplane = new CommercialAirplane("C1", 100000, 1, 1);
        Flight flight = this.flight(airplane);
        FlightBookingService service = new FlightBookingService(List.of(flight));
        Ticket first = this.book(service);
        Ticket second = this.book(service);
        assertEquals(SeatType.ECONOMY, first.getSeatType());
        assertEquals(SeatType.BUSINESS, second.getSeatType());
        assertSame(flight, first.getFlight());
        assertEquals(0, airplane.getEmptySeats());
        assertThrows(IllegalStateException.class, () -> this.book(service));
        assertEquals((1.75 + 1.98) * 100 + 2.02 + 2.87 + 20 * 0.3,
                airplane.getFuelConsumption(100, 20), EPS);
    }

    @Test
    void invalidLuggageDoesNotConsumeASeat()
    {
        Airplane airplane = new PrivateAirplane("P1", 100000, 1);
        FlightBookingService service = new FlightBookingService(List.of(this.flight(airplane)));
        assertThrows(NullPointerException.class, () -> service.bookTicket(new Passenger("Oleh"),
                Airport.AMS, Airport.JFK, DATE, Arrays.asList(new Luggage(1, LuggageType.HOLD), null)));
        assertEquals(1, airplane.getEmptySeats());
        assertThrows(NullPointerException.class, () -> new Ticket(null, this.flight(
                new PrivateAirplane("P2", 100, 1)), SeatType.ECONOMY));
    }

    @Test
    void searchMatchesRouteTimeAvailabilityAndStatus()
    {
        Flight flight = this.flight(new PrivateAirplane("P1", 100000, 2));
        FlightBookingService service = new FlightBookingService(List.of(flight));
        assertSame(flight, service.findAvailableFlight(Airport.AMS, Airport.JFK, DATE));
        assertNull(service.findAvailableFlight(Airport.JFK, Airport.AMS, DATE));
        assertNull(service.findAvailableFlight(Airport.AMS, Airport.JFK, DATE.plusMinutes(1)));
        flight.setStatus(FlightStatus.BOARDING);
        flight.depart();
        assertNull(service.findAvailableFlight(Airport.AMS, Airport.JFK, DATE));
        assertThrows(IllegalStateException.class, () -> this.book(service));
    }

    @Test
    void failedSeatClassChangeKeepsExistingReservation()
    {
        CommercialAirplane airplane = new CommercialAirplane("C1", 100000, 1, 1);
        Flight flight = this.flight(airplane);
        Ticket economy = new Ticket(new Passenger("A"), flight, SeatType.ECONOMY);
        new Ticket(new Passenger("B"), flight, SeatType.BUSINESS);
        assertThrows(IllegalStateException.class, () -> economy.setSeatType(SeatType.BUSINESS));
        assertEquals(SeatType.ECONOMY, economy.getSeatType());
        assertEquals(0, airplane.getEmptySeats());
    }

    @Test
    void successfulSeatClassChangeUpdatesCountersAndFuel()
    {
        CommercialAirplane airplane = new CommercialAirplane("C1", 100000, 1, 1);
        Ticket ticket = new Ticket(new Passenger("A"), this.flight(airplane), SeatType.ECONOMY);
        ticket.setSeatType(SeatType.BUSINESS);
        assertEquals(SeatType.BUSINESS, ticket.getSeatType());
        assertEquals(1, airplane.getEmptySeats());
        assertEquals((1.75 + 1.98) * 100 + 2.87, airplane.getFuelConsumption(100, 0), EPS);
        airplane.reserveSeat();
        assertEquals(0, airplane.getEmptySeats());
    }

    @Test
    void luggageIsOwnedByTicketAndWeightIncludesEveryItem()
    {
        Ticket ticket = new Ticket(new Passenger("A"), this.flight(
                new PrivateAirplane("P1", 100000, 2)), SeatType.ECONOMY);
        Luggage luggage = new Luggage(10, LuggageType.HOLD);
        ticket.addLuggage(luggage);
        ticket.addLuggage(new Luggage(5, LuggageType.CARRY_ON));
        luggage.setWeight(100);
        ticket.getLuggageList().get(0).setWeight(200);
        assertEquals(15, ticket.getTotalLuggageWeight(), EPS);
        assertThrows(UnsupportedOperationException.class, () -> ticket.getLuggageList().clear());
    }

    @Test
    void departureChecksFuelAndDeductsItExactlyOnce()
    {
        Airplane airplane = new PrivateAirplane("P1", 0, 1);
        Flight flight = this.flight(airplane);
        Ticket ticket = new Ticket(new Passenger("A"), flight, SeatType.ECONOMY);
        ticket.addLuggage(new Luggage(10, LuggageType.HOLD));
        double required = airplane.getFuelConsumption(5848, 10);
        assertFalse(flight.canDepart());
        flight.setStatus(FlightStatus.BOARDING);
        airplane.setFuelLevel(required - 1);
        assertFalse(flight.canDepart());
        assertThrows(IllegalStateException.class, flight::depart);
        assertEquals(required - 1, airplane.getFuelLevel(), EPS);
        airplane.setFuelLevel(required);
        assertTrue(flight.canDepart());
        flight.setStatus(FlightStatus.DEPARTED);
        assertEquals(0, airplane.getFuelLevel(), EPS);
        assertThrows(IllegalStateException.class, flight::depart);
        assertThrows(IllegalStateException.class, () -> ticket.addLuggage(new Luggage(1, LuggageType.HOLD)));
        flight.setStatus(FlightStatus.LANDED);
        assertThrows(IllegalStateException.class, () -> flight.setStatus(FlightStatus.BOARDING));
    }

    @Test
    void capacityCannotBeReducedBelowReservations()
    {
        PrivateAirplane privateAirplane = new PrivateAirplane("P1", 100, 2);
        privateAirplane.reserveSeat();
        privateAirplane.reserveSeat();
        assertThrows(IllegalArgumentException.class, () -> privateAirplane.setNumberOfSeats(1));
        assertThrows(IllegalStateException.class, privateAirplane::reserveSeat);
        CommercialAirplane commercial = new CommercialAirplane("C1", 100, 1, 1);
        commercial.reserveSeat();
        assertThrows(IllegalArgumentException.class, () -> commercial.setEconomySeats(0));
        assertThrows(IllegalArgumentException.class, () -> new CommercialAirplane("C2", 0, 0, 0));
    }

    @Test
    void invalidNumbersNamesAndRoutesAreRejected()
    {
        assertThrows(IllegalArgumentException.class, () -> new Passenger(" "));
        assertThrows(IllegalArgumentException.class, () -> new Luggage(-1, LuggageType.HOLD));
        assertThrows(IllegalArgumentException.class, () -> new Luggage(Double.NaN, LuggageType.HOLD));
        assertThrows(IllegalArgumentException.class, () -> new PrivateAirplane("P1", Double.POSITIVE_INFINITY, 2));
        Airplane airplane = new PrivateAirplane("P1", 100, 1);
        assertThrows(IllegalArgumentException.class, () -> airplane.getFuelConsumption(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> airplane.getFuelConsumption(100, Double.NaN));
        assertThrows(IllegalArgumentException.class, () -> new Flight(Airport.AMS, Airport.AMS, DATE, airplane));
    }

    @Test
    void compositionPreventsOneAirplaneBelongingToTwoFlights()
    {
        Airplane airplane = new PrivateAirplane("P1", 100000, 2);
        this.flight(airplane);
        assertThrows(IllegalStateException.class, () -> this.flight(airplane));
    }

    @Test
    void uploaderProducesLocalTextFiles() throws Exception
    {
        Airplane airplane = new PrivateAirplane("P1", 100000, 2);
        Flight flight = this.flight(airplane);
        Flight24Uploader uploader = new Flight24Uploader();
        assertEquals("PRIVATE;P1;100000.00;2;2", uploader.uploadAirplane(airplane));
        assertEquals("FLIGHT;P1;AMS;JFK;2026-10-15T10:30;AWAITING_DEPARTURE", uploader.uploadFlight(flight));
        uploader.uploadAllFlights(List.of(flight));
        uploader.uploadAllAirplanes(List.of(airplane));
        assertEquals(List.of(uploader.uploadFlight(flight)), Files.readAllLines(Path.of("exports/flights.txt")));
        assertEquals(List.of(uploader.uploadAirplane(airplane)), Files.readAllLines(Path.of("exports/airplanes.txt")));
    }
}
