package com.nhlstenden.smartbbq.fridge;

import com.nhlstenden.smartbbq.exceptions.FridgeIsEmptyException;
import com.nhlstenden.smartbbq.exceptions.NoMoreFoodException;
import com.nhlstenden.smartbbq.food.Food;
import com.nhlstenden.smartbbq.food.Meat;
import com.nhlstenden.smartbbq.food.Vegetable;
import com.nhlstenden.smartbbq.thermometer.Measurable;

import java.util.ArrayList;

public class Fridge implements Measurable
{
    private static final int DEFAULT_TEMPERATURE = 8;
    private static final String FOOD_NULL_MESSAGE = "Food cannot be null";
    private static final String NO_MORE_MEAT_MESSAGE = "There is no more meat in the fridge";
    private static final String NO_MORE_VEGETABLES_MESSAGE = "There are no more vegetables in the fridge";

    private static Fridge instance;

    private final ArrayList<Food> foodInFridge;
    private int temperature;

    private Fridge()
    {
        this.foodInFridge = new ArrayList<>();
        this.temperature = DEFAULT_TEMPERATURE;
    }

    public static Fridge getInstance()
    {
        if (instance == null)
        {
            instance = new Fridge();
        }

        return instance;
    }

    public ArrayList<Food> getFoodInFridge()
    {
        return new ArrayList<>(this.foodInFridge);
    }

    @Override
    public double getTemperature()
    {
        return this.temperature;
    }

    public void setTemperature(int temperature)
    {
        this.temperature = temperature;
    }

    public void addFood(Food food)
    {
        if (food == null)
        {
            throw new IllegalArgumentException(FOOD_NULL_MESSAGE);
        }

        this.foodInFridge.add(food);
    }

    public Meat getNextMeat() throws FridgeIsEmptyException, NoMoreFoodException
    {
        return this.getNextFood(Meat.class, NO_MORE_MEAT_MESSAGE);
    }

    public Vegetable getNextVegetable() throws FridgeIsEmptyException, NoMoreFoodException
    {
        return this.getNextFood(Vegetable.class, NO_MORE_VEGETABLES_MESSAGE);
    }

    public void clear()
    {
        this.foodInFridge.clear();
    }

    private <T extends Food> T getNextFood(Class<T> foodType, String noMoreFoodMessage)
            throws FridgeIsEmptyException, NoMoreFoodException
    {
        if (this.foodInFridge.isEmpty())
        {
            throw new FridgeIsEmptyException();
        }

        for (int index = 0; index < this.foodInFridge.size(); index++)
        {
            if (foodType.isInstance(this.foodInFridge.get(index)))
            {
                return foodType.cast(this.foodInFridge.remove(index));
            }
        }

        throw new NoMoreFoodException(noMoreFoodMessage);
    }
}