package com.nhlstenden.appstore;

import java.time.LocalDate;
import java.util.Objects;

public class Purchase
{
    private final LocalDate date;
    private final App app;
    private final User user;

    public Purchase(LocalDate date, App app, User user)
    {
        this.date = Objects.requireNonNull(date, "date");
        this.app = Objects.requireNonNull(app, "app");
        this.user = Objects.requireNonNull(user, "user");
    }

    public LocalDate getDate()
    {
        return this.date;
    }

    public App getApp()
    {
        return this.app;
    }

    public User getUser()
    {
        return this.user;
    }
}
