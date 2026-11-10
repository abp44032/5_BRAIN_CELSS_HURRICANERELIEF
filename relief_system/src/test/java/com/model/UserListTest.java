package com.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * -------------------------------------------------------------------------------------------------------------------------------------
 * TEST                                                   | REASONING                                                                  |
 * -------------------------------------------------------------------------------------------------------------------------------------
 * getUserReturnsNullForUnknownUsername                   | Checks that an unknown username returns null.                              |
 * getUserReturnsNullForNullUsername                      | Checks that a null username returns null instead of crashing.              |
 * getUserFindsUsernameIgnoringCase                       | Checks that usernames match regardless of capitalization.                  |
 * getUserWithPasswordReturnsUserWhenPasswordMatches      | Checks that a matching username and password find the user.                |
 * getUserWithPasswordReturnsNullWhenPasswordDoesNotMatch | Checks that an incorrect password does not find the user.                  |
 * addUserRejectsDuplicateUsernameIgnoringCase            | Checks that a duplicate username is rejected regardless of capitalization. |
 * -------------------------------------------------------------------------------------------------------------------------------------
 */
public class UserListTest {
    private UserList userList;
    private String username;

    /**
     * Creates a UserList and a unique username for each test.
     */
    @BeforeEach
    public void setUp() {
        userList = new UserList();
        username = "testuser_" + UUID.randomUUID();
    }

    /**
     * Tests that an unknown username returns null.
     */
    @Test
    public void getUserReturnsNullForUnknownUsername() {
        assertNull(userList.getUser(username));
    }

    /**
     * Tests that a null username returns null.
     */
    @Test
    public void getUserReturnsNullForNullUsername() {
        assertNull(userList.getUser(null));
    }

    /**
     * Tests that usernames are matched without case sensitivity.
     */
    @Test
    public void getUserFindsUsernameIgnoringCase() {
        assertTrue(userList.addUser(
                "Test", "User", username, "password123",
                "test@example.com", null, false));

        assertSame(userList.getUser(username), userList.getUser(username.toUpperCase()));
    }

    /**
     * Tests that a matching username and password find the user.
     */
    @Test
    public void getUserWithPasswordReturnsUserWhenPasswordMatches() {
        assertTrue(userList.addUser(
                "Test", "User", username, "password123",
                "test@example.com", null, false));

        assertNotNull(userList.getUser(username, "password123"));
    }

    /**
     * Tests that an incorrect password does not find the user.
     */
    @Test
    public void getUserWithPasswordReturnsNullWhenPasswordDoesNotMatch() {
        assertTrue(userList.addUser(
                "Test", "User", username, "password123",
                "test@example.com", null, false));

        assertNull(userList.getUser(username, "wrongpassword"));
    }

    /**
     * Tests that adding a duplicate username with different capitalization returns false.
     */
    @Test
    public void addUserRejectsDuplicateUsernameIgnoringCase() {
        assertTrue(userList.addUser(
                "Test", "User", username, "password123",
                "test@example.com", null, false));

        assertFalse(userList.addUser(
                "Another", "Person", username.toUpperCase(), "anotherpassword",
                "another@example.com", null, false));
    }
}