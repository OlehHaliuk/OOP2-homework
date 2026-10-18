package com.nhlstenden.vegetablegarden.garden;

public class Greenhouse extends Garden
{

    public Greenhouse()
    {
        super();
    }

    public void runWeather()
    {
        int lux = WeatherGenerator.getRandomLux();
        int mm = WeatherGenerator.getRandomMm();
        this.weather(lux, mm, 1);
    }
}