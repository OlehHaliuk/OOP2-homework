package com.nhlstenden.smartbbq.food;

public class BellPepper extends Vegetable
{
    private static final double MOIST_FACTOR = 0.1;
    private static final double BROWNING_FACTOR = 0.03;
    private static final String NAME = "BellPepper";

    public BellPepper()
    {
        super(MOIST_FACTOR, BROWNING_FACTOR);
    }

    @Override
    public String toString()
    {
        return NAME;
    }
}
