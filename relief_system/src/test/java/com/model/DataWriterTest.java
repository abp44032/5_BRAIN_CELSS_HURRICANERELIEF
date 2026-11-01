package com.model;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 ----------------------------------------------------------------------------------------
 * TEST                       | REASONING
 * writing zero users         | Checks that saving an empty user list works.
 * writing one user           | Checks that one user is saved successfully.
 * writing multiple users     | Checks that multiple users are saved successfully.
 * writing user with role     | Checks that a user's role is included in the saved JSON.
 * writing user with location | Checks that a user's location is included in the saved JSON
 -----------------------------------------------------------------------------------------
 */

class DataWriterTest {

    private final UserList users = UserList.getInstance();
    private final ArrayList<User> userList = users.getUsers();

    /**
     * Clears the test JSON file so each test starts with no saved users.
     */
    private void resetTestUsersFile() throws Exception {
        Path testUsersPath = Path.of(
                DataWriter.class.getResource("/data/users.json").toURI());
        Files.writeString(testUsersPath, "[]");
    }

    @BeforeEach
    void setup() throws Exception {
        userList.clear();
        resetTestUsersFile();
    }

    @AfterEach
    void tearDown() throws Exception {
        userList.clear();
        resetTestUsersFile();
    }

    /**
     * Checks that saving zero users results in an empty saved user list.
     */
    @Test
    void testWritingZeroUsers() {
        DataWriter.saveUsers();

        assertEquals(0, DataLoader.getUsers().size());
    }

    /**
     * Checks that one user is saved successfully.
     */
    @Test
    void testWritingOneUser() {
        userList.add(new User(
                "Amy", "Smith", "asmith",
                "password123", "amy@email.com", null, true));

        DataWriter.saveUsers();

        ArrayList<User> savedUsers = DataLoader.getUsers();

        assertEquals(1, savedUsers.size());
        assertEquals("asmith", savedUsers.get(0).getUsername());
    }

    /**
     * Checks that multiple users are saved successfully.
     */
    @Test
    void testWritingMultipleUsers() {
        userList.add(new User(
                "Amy", "Smith", "asmith",
                "password123", "amy@email.com", null, true));
        userList.add(new User(
                "Ben", "Smith", "bsmith",
                "password123", "ben@email.com", null, true));
        userList.add(new User(
                "Chris", "Smith", "csmith",
                "password123", "chris@email.com", null, true));
        userList.add(new User(
                "Drew", "Smith", "dsmith",
                "password123", "drew@email.com", null, true));
        userList.add(new User(
                "Evan", "Smith", "esmith",
                "password123", "evan@email.com", null, true));

        DataWriter.saveUsers();

        ArrayList<User> savedUsers = DataLoader.getUsers();

        assertEquals(5, savedUsers.size());
        assertEquals("asmith", savedUsers.get(0).getUsername());
        assertEquals("bsmith", savedUsers.get(1).getUsername());
        assertEquals("csmith", savedUsers.get(2).getUsername());
        assertEquals("dsmith", savedUsers.get(3).getUsername());
        assertEquals("esmith", savedUsers.get(4).getUsername());
    }

    /**
     * Checks that a user's role is included in the saved JSON.
     */
    @Test
    void testWritingUserWithRole() {
        userList.add(new User(
                "Amy", "Smith", "asmith",
                "password123", "amy@email.com", null, true,
                User.VOLUNTEER));

        DataWriter.saveUsers();

        assertEquals(
                User.VOLUNTEER,
                DataWriter.getUserJSON(userList.get(0)).get("role"));
    }

    /**
     * Checks that a user's location is included in the saved JSON.
     */
    @Test
    void testWritingUserWithLocation() {
        Location location = new Location(
                "123 Main Street", "Columbia", "SC", "29201");

        userList.add(new User(
                "Amy", "Smith", "asmith",
                "password123", "amy@email.com", location, true));

        DataWriter.saveUsers();

        assertTrue(
                DataWriter.getUserJSON(userList.get(0)).containsKey("location"));
    }
}