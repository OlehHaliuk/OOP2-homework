package com.nhlstenden.uservalidation;

public class PasswordValidation implements ValidationRule
{
    private final boolean spacesAllowed;
    private final boolean specialCharacterRequired;
    private final boolean numberRequired;
    private final boolean lowercaseRequired;
    private final boolean uppercaseRequired;

    public PasswordValidation(boolean spacesAllowed, boolean specialCharacterRequired,
        boolean numberRequired, boolean lowercaseRequired, boolean uppercaseRequired)
    {
        this.spacesAllowed = spacesAllowed;
        this.specialCharacterRequired = specialCharacterRequired;
        this.numberRequired = numberRequired;
        this.lowercaseRequired = lowercaseRequired;
        this.uppercaseRequired = uppercaseRequired;
    }

    @Override
    public void validate(User user, UserStorage storage)
    {
        boolean hasSpace = false;
        boolean hasSpecialCharacter = false;
        boolean hasNumber = false;
        boolean hasLowercase = false;
        boolean hasUppercase = false;

        for (char character : user.getPassword().toCharArray())
        {
            boolean space = Character.isWhitespace(character) || Character.isSpaceChar(character);
            hasSpace = hasSpace || space;
            hasSpecialCharacter = hasSpecialCharacter
                || (!Character.isLetterOrDigit(character) && !space && !Character.isISOControl(character));
            hasNumber = hasNumber || Character.isDigit(character);
            hasLowercase = hasLowercase || Character.isLowerCase(character);
            hasUppercase = hasUppercase || Character.isUpperCase(character);
        }

        if (!this.spacesAllowed && hasSpace)
        {
            throw new IllegalArgumentException("Password cannot contain spaces.");
        }
        if (this.specialCharacterRequired && !hasSpecialCharacter)
        {
            throw new IllegalArgumentException("Password needs a special character.");
        }
        if (this.numberRequired && !hasNumber)
        {
            throw new IllegalArgumentException("Password needs a number.");
        }
        if (this.lowercaseRequired && !hasLowercase)
        {
            throw new IllegalArgumentException("Password needs a lowercase letter.");
        }
        if (this.uppercaseRequired && !hasUppercase)
        {
            throw new IllegalArgumentException("Password needs an uppercase letter.");
        }
    }
}
