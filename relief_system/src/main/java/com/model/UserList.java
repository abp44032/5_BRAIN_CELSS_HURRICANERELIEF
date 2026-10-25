package com.model;

import java.util.ArrayList;
import java.util.UUID;


public class UserList {

    private static UserList userList;
    private ArrayList<User> users;

    public UserList() {
        users = DataLoader.getUsers();

        if (users == null) {
            users = new ArrayList<>();
        }
    }

    public static UserList getInstance() {
        if (userList == null) {
            userList = new UserList();
        }

        return userList;
    }

    /**
     * Gets a user by username
     * @param username username to look up
     * @return the user with given username. Returns null if there is no matching user
     */
    public User getUser(String username) {
        if (username == null) {
            return null;
        }

        for (User user : users) {
            if (user.getUsername() != null
                    && user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }

        return null;
    }
    
    /**
     * Gets a user by username and verifies the password
     * @param username username to look up
     * @param password password to check
     * @return the matching user. Returns null if credentials are invalid
     */
    public User getUser(String username, String password) {
        if (isBlank(username) || isBlank(password)) {
            return null;
        }
        User user = getUser(username);
        if (user != null && user.checkPassword(password)) {
            return user;
        }
        return null;
    }

    /**
     * ArrayList of Users
     * @return an array list of each user
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Creates a new user, adds it to the user list, and saves to JSON file
     * @param firstName user's first name
     * @param lastName user's last name
     * @param username username
     * @param password password
     * @param email email
     * @param location user's location
     * @param shareLocation user's choice to share their location or not
     * @return true if the user is created and saved; false if any field is blank or username is taken
     */
    public boolean addUser(String firstName, String lastName,
            String username, String password, String email,
            Location location, boolean shareLocation) {

        if (isBlank(firstName) || isBlank(lastName)
                || isBlank(username) || isBlank(password) || isBlank(email)) {
            return false;
        }

        // Prevent duplicate usernames.
        if (getUser(username) != null) {
            return false;
        }
        
        users.add(new User(firstName, lastName, username, password,
            email, location, shareLocation));

        return save();
    }


    public boolean addUser(String firstName, String lastName,
            String username, String password, String email,
            Location location, boolean shareLocation, String role) {

        if (isBlank(firstName) || isBlank(lastName)
                || isBlank(username) || isBlank(password)
                || isBlank(email) || isBlank(role)) {
            return false;
        }

        if (!role.equals(User.VOLUNTEER) && !role.equals(User.RESIDENT)) {
            return false;
        }

        if (getUser(username) != null) {
            return false;
        }

        users.add(new User(firstName, lastName, username, password,
                email, location, shareLocation, role));

        return save();
    }


    /**
     * Removes a user from the user list
     * @param user
     * @return true if the user was successfully removed
     */
    public boolean removeUser(User user) {
        return users.remove(user);
    }

    /**
     * Saves the user list to JSON
     * @return true if saved successfully
     */
    public boolean save() {
        return DataWriter.saveUsers();
    }

    /**
     * Helper method to check if a string is null or empty
     * @param s string
     * @return true if the string is null or blank
     */
    private static boolean isBlank(String s) {
        return s == null || s.isBlank();
    }

    // -------- MAIN METHOD --------- 

    public static void main(String[] args) {

        UserList userList = UserList.getInstance();

        // Add a user
        boolean added = userList.addUser(
            "John",
            "Doe",
            "johndoe",
            "password123",
            "john@example.com",
            null,
            false
        );

        System.out.println("User added: " + added);

        // Test getting a user
        User user = userList.getUser("johndoe", "password123");

        if (user != null) {
            System.out.println("User found: " + user.getUsername());
        } else {
            System.out.println("User not found.");
        }

        // Save users
        System.out.println("Saved: " + userList.save());
    }
}
