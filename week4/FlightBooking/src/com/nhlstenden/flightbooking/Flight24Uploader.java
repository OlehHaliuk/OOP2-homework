package com.nhlstenden.flightbooking;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Flight24Uploader
{
    public String uploadFlight(Flight flight)
    {
        return Objects.requireNonNull(flight).toFlight24String();
    }

    public String uploadAirplane(Airplane airplane)
    {
        return Objects.requireNonNull(airplane).toFlight24String();
    }

    public void uploadAllFlights(List<Flight> flights)
    {
        List<String> lines = new ArrayList<>();
        for (Flight flight : Objects.requireNonNull(flights))
        {
            lines.add(this.uploadFlight(flight));
        }
        this.writeFile("flights.txt", lines);
    }

    public void uploadAllAirplanes(List<Airplane> airplanes)
    {
        List<String> lines = new ArrayList<>();
        for (Airplane airplane : Objects.requireNonNull(airplanes))
        {
            lines.add(this.uploadAirplane(airplane));
        }
        this.writeFile("airplanes.txt", lines);
    }

    private void writeFile(String name, List<String> lines)
    {
        try
        {
            Path directory = Path.of("exports");
            Files.createDirectories(directory);
            Files.write(directory.resolve(name), lines, StandardCharsets.UTF_8);
        }
        catch (IOException exception)
        {
            throw new UncheckedIOException("Could not export " + name, exception);
        }
    }
}
