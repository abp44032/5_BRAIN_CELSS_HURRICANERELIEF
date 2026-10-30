package com.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
+--------------------------+--------------------------------------------------+
TEST                       | REASONING
writing zero users         | checks that saving an empty user should work
writing one user           | checks that one user is saved successfully
writing multiple users     | checks that multiple users can be saved at a time - like a family
writing user with role     | checks that a user's role is included in the saved JSON
writing user with location | checks that a user's location is included in the saved JSON
+--------------------------+--------------------------------------------------+
 */

class DataWriterTest {

    private UserList users = UserList.getInstance();
    private ArrayList<User> userList = users.getUsers();

    @BeforeEach
    public void setup() {
        UserList.getInstance().getUsers().clear();
        DataWriter.saveUsers();
    }

    @AfterEach
    public void tearDown() {
        UserList.getInstance().getUsers().clear();
        DataWriter.saveUsers();
    }

    /**
     * FULFILLED - writing zero users : checks that saving an empty user should work
     */
    @Test
    void testWritingZeroUsers() {
        userList = DataLoader.getUsers();
        assertEquals(0, userList.size());
    }

    /**
     * FULFILLED - writing one user : checks that one user is saved successfully
     */
    @Test
    void testWritingOneUser() {
        userList.add(new User("Amy", "Smith", "asmith",
                "password123", "amy@email.com", null, true));

        DataWriter.saveUsers();

        assertEquals("asmith", DataLoader.getUsers().get(0).getUsername());
    }

    /**
     * FULFILLED - writing multiple users : checks that multiple users can be saved at a time - like a family
     */
     @Test
    void testWritingMultipleUsers() {
        userList.add(new User("Amy", "Smith", "asmith",
                "password123", "amy@email.com", null, true));
        userList.add(new User("Ben", "Smith", "bsmith",
                "password123", "ben@email.com", null, true));
        userList.add(new User("Chris", "Smith", "csmith",
                "password123", "chris@email.com", null, true));
        userList.add(new User("Drew", "Smith", "dsmith",
                "password123", "drew@email.com", null, true));
        userList.add(new User("Evan", "Smith", "esmith",
                "password123", "evan@email.com", null, true));

        DataWriter.saveUsers();

        assertEquals("esmith", DataLoader.getUsers().get(4).getUsername());
    }

    /**
     * FULFILLED - writing user with role : checks that a user's role is included in the saved JSON
     */
    @Test
    void testWritingUserWithRole() {
        userList.add(new User("Amy", "Smith", "asmith",
                "password123", "amy@email.com", null, true, User.VOLUNTEER));

        DataWriter.saveUsers();

        assertEquals(User.VOLUNTEER, DataWriter.getUserJSON(userList.get(0)).get("role"));
    }

    /**
     * FULFILLED - writing user with location : checks that a user's location is included in the saved JSON
     */
    @Test
    void testWritingUserWithLocation() {
        Location location = new Location("123 Main Street", "Columbia", "SC", "29201");

        userList.add(new User("Amy", "Smith", "asmith",
                "password123", "amy@email.com", location, true));

        DataWriter.saveUsers();

        assertTrue(DataWriter.getUserJSON(userList.get(0)).containsKey("location"));
    }
}