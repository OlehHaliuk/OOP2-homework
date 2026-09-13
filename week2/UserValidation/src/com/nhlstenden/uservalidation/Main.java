package com.nhlstenden.uservalidation;

import java.time.LocalDate;

public class Main
{
    public static void main(String[] arguments)
    {
        UserStorage storage = new UserStorage();
        UserValidator validator = new UserValidator(storage);

        // Add only the rules that the application needs.
        validator.addRule(new PasswordValidation(false, true, true, true, true));
        validator.addRule(new MinimumAgeValidation(18));
        validator.addRule(new EmailValidation());
        validator.addRule(new UsernameValidation());

        User user = new User("Alex", "Example1!", "alex@example.com", LocalDate.of(2000, 5, 20));

        try
        {
            validator.register(user);
            System.out.println("User registered: " + user.getName());

            // This attempt demonstrates the duplicate-username check.
            validator.register(user);
        }
        catch (IllegalArgumentException exception)
        {
            System.out.println("Registration failed: " + exception.getMessage());
        }

        System.out.println("Stored users: " + storage.getUserCount());
    }
}
