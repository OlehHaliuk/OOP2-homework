package com.nhlstenden.uservalidation;

import java.time.LocalDate;

public class ValidationTest
{
    private int checks;

    public static void main(String[] arguments)
    {
        ValidationTest test = new ValidationTest();
        test.validate_passwordOptions_checksEachOption();
        test.validate_minimumAge_checksBirthday();
        test.validate_email_checksFormat();
        test.register_selectedRules_savesOnlyValidUsers();
        test.register_noRules_skipsOptionalChecks();
        System.out.println("Passed " + test.checks + " checks.");
    }

    public void validate_passwordOptions_checksEachOption()
    {
        PasswordValidation strict = new PasswordValidation(false, true, true, true, true);
        this.check(!this.isRejected(strict, this.user("Abc1!")));

        for (String password : new String[]{"Abc 1!", "Abc1", "Abc!", "ABC1!", "abc1!"})
        {
            this.check(this.isRejected(strict, this.user(password)));
        }

        this.check(!this.isRejected(new PasswordValidation(true, true, true, true, true), this.user("Abc 1!")));
        this.check(!this.isRejected(new PasswordValidation(false, false, true, true, true), this.user("Abc1")));
        this.check(!this.isRejected(new PasswordValidation(false, true, false, true, true), this.user("Abc!")));
        this.check(!this.isRejected(new PasswordValidation(false, true, true, false, true), this.user("ABC1!")));
        this.check(!this.isRejected(new PasswordValidation(false, true, true, true, false), this.user("abc1!")));
        this.check(!this.isRejected(new PasswordValidation(true, false, false, false, false), this.user("")));
        this.check(this.isRejected(new PasswordValidation(true, true, false, false, false), this.user(" ")));
    }

    public void validate_minimumAge_checksBirthday()
    {
        MinimumAgeValidation rule = new MinimumAgeValidation(18);
        LocalDate birthday = LocalDate.now().minusYears(18);
        this.check(!this.isRejected(rule, new User("Alex", "", "", birthday)));
        this.check(this.isRejected(rule, new User("Alex", "", "", birthday.plusDays(1))));
        this.check(this.isRejected(rule, new User("Alex", "", "", LocalDate.now().plusDays(1))));
    }

    public void validate_email_checksFormat()
    {
        EmailValidation rule = new EmailValidation();

        for (String email : new String[]{"alex@example.com", "alex.smith+tag@sub.example.org"})
        {
            this.check(!this.isRejected(rule, new User("Alex", "", email, LocalDate.of(2000, 1, 1))));
        }

        for (String email : new String[]{"", "alex", "alex@", "alex@@example.com", "alex..smith@example.com",
            "alex@-example.com", "alex@example", "alex@example.com ", "alex@exam_ple.com"})
        {
            this.check(this.isRejected(rule, new User("Alex", "", email, LocalDate.of(2000, 1, 1))));
        }
    }

    public void register_selectedRules_savesOnlyValidUsers()
    {
        UserStorage storage = new UserStorage();
        UserValidator validator = new UserValidator(storage);
        validator.addRule(new PasswordValidation(false, true, true, true, true));
        validator.addRule(new MinimumAgeValidation(18));
        validator.addRule(new EmailValidation());
        validator.addRule(new UsernameValidation());
        User user = this.user("Abc1!");
        validator.register(user);
        this.check(storage.getUserCount() == 1);

        try
        {
            validator.register(user);
            throw new AssertionError("A duplicate username was accepted.");
        }
        catch (IllegalArgumentException exception)
        {
            this.check(storage.getUserCount() == 1);
        }

        try
        {
            validator.register(this.user("weak"));
            throw new AssertionError("An invalid password was accepted.");
        }
        catch (IllegalArgumentException exception)
        {
            this.check(storage.getUserCount() == 1);
        }
    }

    public void register_noRules_skipsOptionalChecks()
    {
        UserStorage storage = new UserStorage();
        UserValidator validator = new UserValidator(storage);
        User user = new User("Alex", "", "invalid", LocalDate.now().plusYears(1));
        validator.register(user);
        validator.register(user);
        this.check(storage.getUserCount() == 2);
    }

    private User user(String password)
    {
        return new User("Alex", password, "alex@example.com", LocalDate.of(2000, 1, 1));
    }

    private boolean isRejected(ValidationRule rule, User user)
    {
        try
        {
            rule.validate(user, new UserStorage());
        }
        catch (IllegalArgumentException exception)
        {
            return true;
        }

        return false;
    }

    private void check(boolean condition)
    {
        if (!condition)
        {
            throw new AssertionError("Check " + (this.checks + 1) + " failed.");
        }

        this.checks++;
    }
}
