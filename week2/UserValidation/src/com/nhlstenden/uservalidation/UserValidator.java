package com.nhlstenden.uservalidation;

import java.util.ArrayList;
import java.util.Objects;

public class UserValidator
{
    private final ArrayList<ValidationRule> rules;
    private final UserStorage storage;

    public UserValidator(UserStorage storage)
    {
        this.rules = new ArrayList<>();
        this.storage = Objects.requireNonNull(storage);
    }

    public ArrayList<ValidationRule> getRules()
    {
        return this.rules;
    }

    public UserStorage getStorage()
    {
        return this.storage;
    }

    public void addRule(ValidationRule rule)
    {
        this.rules.add(Objects.requireNonNull(rule));
    }

    public void register(User user)
    {
        Objects.requireNonNull(user);

        for (ValidationRule rule : this.rules)
        {
            rule.validate(user, this.storage);
        }

        this.storage.saveUser(user);
    }
}
