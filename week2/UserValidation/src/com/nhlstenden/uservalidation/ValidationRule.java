package com.nhlstenden.uservalidation;

public interface ValidationRule
{
    void validate(User user, UserStorage storage);
}
