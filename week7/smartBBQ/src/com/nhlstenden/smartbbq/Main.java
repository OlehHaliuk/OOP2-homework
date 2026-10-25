package com.nhlstenden.smartbbq;

import com.nhlstenden.smartbbq.exceptions.FridgeIsEmptyException;
import com.nhlstenden.smartbbq.exceptions.NoMoreFoodException;
import com.nhlstenden.smartbbq.food.BellPepper;
import com.nhlstenden.smartbbq.food.Corn;
import com.nhlstenden.smartbbq.food.Hamburger;
import com.nhlstenden.smartbbq.food.Meat;
import com.nhlstenden.smartbbq.food.Sausage;
import com.nhlstenden.smartbbq.fridge.Fridge;
import com.nhlstenden.smartbbq.system.SmartBBQ;
import com.nhlstenden.smartbbq.thermometer.Thermometer;

public class Main
{
    private static final int START_TEMPERATURE = 180;
    private static final int HIGHER_TEMPERATURE = 200;
    private static final int GRILL_TIME_IN_MILLISECONDS = 3000;

    public static void main(String[] args) throws InterruptedException
    {
        SmartBBQ smartBBQ = new SmartBBQ();
        Fridge fridge = Fridge.getInstance();
        Thermometer thermometer = new Thermometer();

        fridge.addFood(new Corn());
        fridge.addFood(new Corn());
        fridge.addFood(new Corn());
        fridge.addFood(new BellPepper());
        fridge.addFood(new BellPepper());
        fridge.addFood(new Hamburger());
        fridge.addFood(new Hamburger());
        fridge.addFood(new Sausage());
        fridge.addFood(new Sausage());
        fridge.addFood(new Sausage());

        try
        {
            Meat measuredMeat = fridge.getNextMeat();
            smartBBQ.addFood(measuredMeat);
            smartBBQ.addFood(fridge.getNextMeat());
            smartBBQ.addFood(fridge.getNextMeat());

            smartBBQ.turnOn(START_TEMPERATURE);
            Thread.sleep(GRILL_TIME_IN_MILLISECONDS);
            System.out.println(measuredMeat + ": " + thermometer.measureTemperature(measuredMeat));

            smartBBQ.setTemperature(HIGHER_TEMPERATURE);
            Thread.sleep(GRILL_TIME_IN_MILLISECONDS);
            System.out.println(measuredMeat + ": " + thermometer.measureTemperature(measuredMeat));
        }
        catch (FridgeIsEmptyException | NoMoreFoodException exception)
        {
            System.out.println(exception.getMessage());
        }
        finally
        {
            smartBBQ.turnOff();
        }
    }
}