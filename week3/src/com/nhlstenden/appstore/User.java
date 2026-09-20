package com.nhlstenden.appstore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class User
{
    private String name;
    private String email;
    private LocalDate dateOfBirth;

    public User(String name, String email, LocalDate dateOfBirth)
    {
        this.setName(name);
        this.setDateOfBirth(dateOfBirth);
        this.email = EmailValidator.validateEmail(email) ? email : null;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("name cannot be null or blank");
        }

        this.name = name;
    }

    public String getEmail()
    {
        return this.email;
    }

    public LocalDate getDateOfBirth()
    {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        if (dateOfBirth == null)
        {
            throw new IllegalArgumentException("dateOfBirth cannot be null");
        }

        if (dateOfBirth.isAfter(LocalDate.now()))
        {
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }

        this.dateOfBirth = dateOfBirth;
    }

    public int getAge()
    {
        return Math.toIntExact(ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now()));
    }
}
