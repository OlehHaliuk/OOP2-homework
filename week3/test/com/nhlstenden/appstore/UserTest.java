package com.nhlstenden.appstore;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest
{
    private static User bornOn(LocalDate date)
    {
        return new User("Sander", "sander@example.com", date);
    }

    @Test
    void oneDayBeforeSixteenthBirthdayAgeIsFifteen()
    {
        LocalDate birthday = LocalDate.now().minusYears(16).plusDays(1);
        assertEquals(15, bornOn(birthday).getAge());
    }

    @Test
    void onSixteenthBirthdayAgeIsSixteen()
    {
        assertEquals(16, bornOn(LocalDate.now().minusYears(16)).getAge());
    }

    @Test
    void oneDayAfterSixteenthBirthdayAgeIsStillSixteen()
    {
        LocalDate birthday = LocalDate.now().minusYears(16).minusDays(1);
        assertEquals(16, bornOn(birthday).getAge());
    }
}
