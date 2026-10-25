package com.nhlstenden.smartbbq.food;

public class Sausage extends Meat
{
    private static final MeatType MEAT_TYPE = MeatType.PIG;
    private static final double COOKING_FACTOR = 80;
    private static final double BROWNING_FACTOR = 0.05;
    private static final String NAME = "Sausage";

    public Sausage()
    {
        super(MEAT_TYPE, COOKING_FACTOR, BROWNING_FACTOR);
    }

    @Override
    public String toString()
    {
        return NAME;
    }
}
