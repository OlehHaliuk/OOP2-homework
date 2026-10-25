package com.nhlstenden.smartbbq.system;

import com.nhlstenden.smartbbq.food.Food;
import com.nhlstenden.smartbbq.thermometer.Measurable;

import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

public class SmartBBQ implements Measurable
{
    private static final int MAX_TEMPERATURE = 250;
    private static final int OFF_TEMPERATURE = 0;
    private static final int MAX_NUMBER_OF_FOOD = 6;
    private static final long START_DELAY_IN_MILLISECONDS = 0;
    private static final long ONE_SECOND_IN_MILLISECONDS = 1000;
    private final HashSet<Food> food;
    private int temperature;
    private Timer timer;

    public SmartBBQ()
    {
        this.food = new HashSet<>();
        this.temperature = 0;
    }

    public HashSet<Food> getFood()
    {
        return new HashSet<>(this.food);
    }

    @Override
    public double getTemperature()
    {
        return this.temperature;
    }

    public void setTemperature(int temperature)
    {
        if (temperature < OFF_TEMPERATURE || temperature > MAX_TEMPERATURE)
        {
            throw new IllegalArgumentException("Temperature out of range");
        }

        this.temperature = temperature;
    }

    public void addFood(Food food)
    {
        if (food == null)
        {
            throw new IllegalArgumentException("Food cannot be null");
        }

        if (this.food.size() >= MAX_NUMBER_OF_FOOD)
        {
            throw new IllegalStateException("BBQ is already full");
        }

        this.food.add(food);
    }

    public void turnOn(int temperature)
    {
        this.setTemperature(temperature);
        this.stopTimer();

        this.timer = new Timer();
        this.timer.schedule(new GrillTask(), START_DELAY_IN_MILLISECONDS, ONE_SECOND_IN_MILLISECONDS);
    }

    public void turnOff()
    {
        this.stopTimer();
        this.temperature = OFF_TEMPERATURE;
    }

    private void stopTimer()
    {
        if (this.timer != null)
        {
            this.timer.cancel();
            this.timer = null;
        }
    }

    private void grillAllFood()
    {
        for (Food foodItem : this.food)
        {
            foodItem.grill(this.temperature);
        }
    }

    private class GrillTask extends TimerTask
    {
        @Override
        public void run()
        {
            SmartBBQ.this.grillAllFood();
        }
    }
}
