package com.nhlstenden.vegetablegarden.garden;

import java.util.Random;

public class WeatherGenerator
{
    private static final int MIN_LUX = 1000;
    private static final int MAX_LUX = 101592;
    private static final int MIN_MM = 0;
    private static final int MAX_MM = 48;

    private static final Random RANDOM = new Random();

    public static int getRandomLux()
    {
        return MIN_LUX + RANDOM.nextInt(MAX_LUX - MIN_LUX + 1);
    }

    public static int getRandomMm()
    {
        return MIN_MM + RANDOM.nextInt(MAX_MM - MIN_MM + 1);
    }
}