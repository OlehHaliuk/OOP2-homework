package com.nhlstenden.vegetablegarden;

import com.nhlstenden.vegetablegarden.exception.TooManyVegetablesException;
import com.nhlstenden.vegetablegarden.garden.Garden;
import com.nhlstenden.vegetablegarden.vegetable.Asparagus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GardenTest
{

    @Test
    void addVegetable_27thVegetable_noException() throws TooManyVegetablesException
    {
        Garden garden = new Garden();
        for (int i = 1; i < 27; i++)
        {
            garden.addVegetable(new Asparagus());
        }

        assertDoesNotThrow(() -> garden.addVegetable(new Asparagus()));
    }

    @Test
    void addVegetable_28thVegetable_throwsException() throws TooManyVegetablesException
    {
        Garden garden = new Garden();
        for (int i = 1; i < 28; i++)
        {
            garden.addVegetable(new Asparagus());
        }
        
        assertThrows(TooManyVegetablesException.class, () -> garden.addVegetable(new Asparagus()));
    }

}