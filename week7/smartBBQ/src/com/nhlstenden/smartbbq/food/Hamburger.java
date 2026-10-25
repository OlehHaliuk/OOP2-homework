package com.nhlstenden.smartbbq.food;

public class Hamburger extends Meat
{
    private static final MeatType MEAT_TYPE = MeatType.COW;
    private static final double COOKING_FACTOR = 100;
    private static final double BROWNING_FACTOR = 0.06;
    private static final String NAME = "Hamburger";

    public Hamburger()
    {
        super(MEAT_TYPE, COOKING_FACTOR, BROWNING_FACTOR);
    }

    @Override
    public String toString()
    {
        return NAME;
    }
}
