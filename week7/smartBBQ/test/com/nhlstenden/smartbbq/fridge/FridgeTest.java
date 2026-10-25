package com.nhlstenden.smartbbq.fridge;

import com.nhlstenden.smartbbq.exceptions.FridgeIsEmptyException;
import com.nhlstenden.smartbbq.exceptions.NoMoreFoodException;
import com.nhlstenden.smartbbq.food.BellPepper;
import com.nhlstenden.smartbbq.food.Corn;
import com.nhlstenden.smartbbq.food.Hamburger;
import com.nhlstenden.smartbbq.food.Sausage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FridgeTest
{
    private Fridge fridge;

    @BeforeEach
    void setUp()
    {
        this.fridge = Fridge.getInstance();
        this.fridge.clear();
    }

    @Test
    void getNextMeat_meatBetweenVegetables_returnsFirstMeat() throws FridgeIsEmptyException, NoMoreFoodException
    {
        Hamburger firstMeat = new Hamburger();
        this.fridge.addFood(new Corn());
        this.fridge.addFood(firstMeat);
        this.fridge.addFood(new BellPepper());
        this.fridge.addFood(new Sausage());

        assertSame(firstMeat, this.fridge.getNextMeat());
    }

    @Test
    void getNextMeat_calledTwice_returnsMeatInOrder() throws FridgeIsEmptyException, NoMoreFoodException
    {
        Hamburger firstMeat = new Hamburger();
        Sausage secondMeat = new Sausage();
        this.fridge.addFood(firstMeat);
        this.fridge.addFood(new Corn());
        this.fridge.addFood(secondMeat);

        assertSame(firstMeat, this.fridge.getNextMeat());
        assertSame(secondMeat, this.fridge.getNextMeat());
    }

    @Test
    void getNextMeat_meatTaken_meatRemovedVegetableStays() throws FridgeIsEmptyException, NoMoreFoodException
    {
        Corn corn = new Corn();
        this.fridge.addFood(corn);
        this.fridge.addFood(new Hamburger());

        this.fridge.getNextMeat();

        assertEquals(1, this.fridge.getFoodInFridge().size());
        assertSame(corn, this.fridge.getFoodInFridge().get(0));
    }

    @Test
    void getNextMeat_onlyVegetables_throwsNoMoreFoodException()
    {
        this.fridge.addFood(new Corn());

        assertThrows(NoMoreFoodException.class, () -> this.fridge.getNextMeat());
    }

    @Test
    void getNextMeat_emptyFridge_throwsFridgeIsEmptyException()
    {
        assertThrows(FridgeIsEmptyException.class, () -> this.fridge.getNextMeat());
    }
}