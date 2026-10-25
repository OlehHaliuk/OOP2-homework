package com.nhlstenden.smartbbq.exceptions;

public class FridgeIsEmptyException extends Exception
{
    private static final String MESSAGE = "There is no food in the fridge";

    public FridgeIsEmptyException()
    {
        super(MESSAGE);
    }
}
