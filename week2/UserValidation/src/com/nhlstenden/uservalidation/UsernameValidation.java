package com.nhlstenden.uservalidation;

public class UsernameValidation implements ValidationRule
{
    @Override
    public void validate(User user, UserStorage storage)
    {
        if (storage.usernameExists(user.getName()))
        {
            throw new IllegalArgumentException("Username already exists.");
        }
    }
}
