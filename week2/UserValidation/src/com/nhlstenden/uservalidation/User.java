package com.nhlstenden.uservalidation;

import java.time.LocalDate;
import java.util.Objects;

public class User
{
    private final String name;
    private final String password;
    private final String email;
    private final LocalDate dateOfBirth;

    public User(String name, String password, String email, LocalDate dateOfBirth)
    {
        this.name = Objects.requireNonNull(name);
        this.password = Objects.requireNonNull(password);
        this.email = Objects.requireNonNull(email);
        this.dateOfBirth = Objects.requireNonNull(dateOfBirth);
    }

    public String getName()
    {
        return this.name;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String getEmail()
    {
        return this.email;
    }

    public LocalDate getDateOfBirth()
    {
        return this.dateOfBirth;
    }


}
