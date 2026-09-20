package com.nhlstenden.appstore;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest
{
    @Test
    void validEmailIsAcceptedAndSaved()
    {
        String email = "sander@example.com";
        assertTrue(EmailValidator.validateEmail(email));
        assertEquals(email, new User("Sander", email, LocalDate.now()).getEmail());
    }

    @Test
    void invalidAndNullEmailAreRejectedAndSavedAsNull()
    {
        assertFalse(EmailValidator.validateEmail("sanderexample.com"));
        assertFalse(EmailValidator.validateEmail(null));
        assertNull(new User("Sander", "sanderexample.com", LocalDate.now()).getEmail());
        assertNull(new User("Sander", null, LocalDate.now()).getEmail());
    }
}
