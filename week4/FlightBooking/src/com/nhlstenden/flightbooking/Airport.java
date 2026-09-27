package com.nhlstenden.flightbooking;

import java.util.Objects;

public enum Airport
{
    JFK, AMS, MEX, LAX;

    private static final int[][] DISTANCES = {
            {0, 5848, 3366, 3975},
            {5848, 0, 9206, 8956},
            {3366, 9206, 0, 2500},
            {3975, 8956, 2500, 0}
    };

    public int getDistanceTo(Airport other)
    {
        Objects.requireNonNull(other, "Airport cannot be null");
        return DISTANCES[this.ordinal()][other.ordinal()];
    }
}
