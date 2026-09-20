package com.nhlstenden.appstore;

import java.util.regex.Pattern;

public class EmailValidator
{
    private static final Pattern SIMPLE_EMAIL = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+(?:\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,}$"
    );

    private EmailValidator()
    {

    }

    public static boolean validateEmail(String email)
    {
        return email != null && SIMPLE_EMAIL.matcher(email).matches();
    }
}
