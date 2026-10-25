package com.nhlstenden.smartbbq.thermometer;

public class Thermometer
{
    private static final double COLD_LIMIT = 10;
    private static final double HOT_LIMIT = 70;
    private static final String MEASUREMENT_FORMAT = "I measured a temperature of %.1f degrees Celsius (%s)";

    public String measureTemperature(Measurable measurable)
    {
        double temperature = measurable.getTemperature();

        return String.format(MEASUREMENT_FORMAT, temperature, this.classify(temperature));
    }

    private HeatLevel classify(double temperature)
    {
        if (temperature < COLD_LIMIT)
        {
            return HeatLevel.COLD;
        }

        if (temperature <= HOT_LIMIT)
        {
            return HeatLevel.MEDIUM;
        }

        return HeatLevel.HOT;
    }
}
