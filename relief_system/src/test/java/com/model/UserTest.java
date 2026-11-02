package com.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * -------------------------------------------------------------------------
 * TEST                         | REASONING
 * correct password             | Checks that a matching password is accepted.
 * incorrect password           | Checks that a nonmatching password is rejected.
 * null password                | Checks that a null password is rejected.
 * user string representation   | Checks that the user's information is
 *                              | formatted the right way.
 * user with supplied ID        | Checks that the constructor uses the
 *                              | provided ID.
 * -------------------------------------------------------------------------
 */
class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(
                "Amy", "Smith", "asmith",
                "password123", "amy@email.com", null, true
        );
    }

    /**
     * Checks that a matching password is accepted.
     */
    @Test
    void testCheckPasswordCorrect() {
        assertTrue(user.checkPassword("password123"));
    }

    /**
     * Checks that a nonmatching password is rejected.
     */
    @Test
    void testCheckPasswordIncorrect() {
        assertFalse(user.checkPassword("wrongpassword"));
    }

    /**
     * Checks that a null password is rejected.
     */
    @Test
    void testCheckPasswordNull() {
        assertFalse(user.checkPassword(null));
    }

    /**
     * Checks that the user's information is formatted the right way.
     */
    @Test
    void testToString() {
        assertEquals("Amy Smith (asmith)", user.toString());
    }

    /**
     * Checks that the constructor uses the provided ID.
     */
    @Test
    void testConstructorWithSpecificId() {
        UUID id = UUID.randomUUID();

        User userWithId = new User(
                id, "Amy", "Smith", "asmith",
                "password123", "amy@email.com", null, true
        );

        assertEquals(id, userWithId.getId());
    }
}