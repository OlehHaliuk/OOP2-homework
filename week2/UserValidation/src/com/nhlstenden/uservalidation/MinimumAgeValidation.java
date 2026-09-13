package com.nhlstenden.uservalidation;

import java.time.LocalDate;
import java.time.Period;

public class MinimumAgeValidation implements ValidationRule
{
    private final int minimumAge;

    public MinimumAgeValidation(int minimumAge)
    {
        if (minimumAge < 0)
        {
            throw new IllegalArgumentException("Minimum age cannot be negative.");
        }

        this.minimumAge = minimumAge;
    }

    @Override
    public void validate(User user, UserStorage storage)
    {
        LocalDate today = LocalDate.now();

        if (user.getDateOfBirth().isAfter(today)
            || Period.between(user.getDateOfBirth(), today).getYears() < this.minimumAge)
        {
            throw new IllegalArgumentException("User must be at least " + this.minimumAge + " years old.");
        }
    }
}
