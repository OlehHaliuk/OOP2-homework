package com.nhlstenden.uservalidation;

public class EmailValidation implements ValidationRule
{
    @Override
    public void validate(User user, UserStorage storage)
    {
        String email = user.getEmail();
        String pattern = "[A-Za-z0-9_+%-]+(?:\\.[A-Za-z0-9_+%-]+)*"
            + "@(?:[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?\\.)+[A-Za-z]{2,63}";

        if (email.length() > 254 || email.indexOf('@') > 64 || !email.matches(pattern))
        {
            throw new IllegalArgumentException("Email address is invalid.");
        }
    }
}
