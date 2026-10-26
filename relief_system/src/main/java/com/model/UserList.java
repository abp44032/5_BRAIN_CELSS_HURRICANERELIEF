package com.model;

import java.util.ArrayList;

/**
 * Manages the apps users and saves changes to the user data file.
 * UserList
 */
public class UserList {
    private static UserList instance;
    private ArrayList<User> users;

    /**
     * creates the user list using users loaded from storage
     */
    public UserList() {
        users = DataLoader.getUsers();
    }

    /**
     * Returns the singleton instance of the UserList.
     *
     * @return the UserList instance
     */
    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }

        return instance;
    }

    /**
     * returns the list of users
     *
     * @return the list of users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * find a user by username.
     *
     * @param username the username of the user to find
     * @return the User object if found, null otherwise
     */
    public User getUser(String username) {
        if (username == null) {
            return null;
        }

        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }

        return null;
    }

    /**
     * finds a user only when the username and password match.
     * @param username the username of the user to find
     * @param password the password of the user to find
     * @return the User object if found and the password matches, null otherwise
     */
    public User getUser(String username, String password) {
        User user = getUser(username);

        if (user != null && user.checkPassword(password)) {
            return user;
        }

        return null;
    }

    /**
     * Adds a new user to the list if the username is not already taken.
     * 
     * @param firstName the first name of the user to add
     * @param lastName the last name of the user to add
     * @param username the username of the user to add
     * @param password the password of the user to add
     * @param email the email of the user to add
     * @param location the location of the user to add
     * @param shareLocation whether the user wants to share their location
     * @return true if the user was successfully added and saved, false otherwise   
     */
    public boolean addUser(String firstName, String lastName,
            String username, String password, String email,
            Location location, boolean shareLocation) {

        if (getUser(username) != null) {
            return false;
        }

        users.add(new User(firstName, lastName, username, password,
                email, location, shareLocation));

        return save();
    }

    /**
     * Adds a new user with a specified role to the list if the username is not already taken.
     *
     * @param firstName the first name of the user to add
     * @param lastName the last name of the user to add
     * @param username the username of the user to add
     * @param password the password of the user to add
     * @param email the email of the user to add
     * @param location the location of the user to add
     * @param shareLocation whether the user wants to share their location
     * @param role the role of the user to add
     * @return true if the user was successfully added and saved, false otherwise
     */
    public boolean addUser(String firstName, String lastName,
            String username, String password, String email,
            Location location, boolean shareLocation, String role) {

        if (getUser(username) != null) {
            return false;
        }

        users.add(new User(firstName, lastName, username, password,
                email, location, shareLocation, role));

        return save();
    }

    /**
     * Removes a user from the list if they exist.
     * @param user the user to remove
     * @return true if the user was successfully removed and saved, false otherwise
     */
    public boolean removeUser(User user) {
        if (users.remove(user)) {
            return save();
        }

        return false;
    }

    /**
     * Saves all users to the data writer.
     * @return true if the users were successfully saved, false otherwise
     */
    public boolean save() {
        return DataWriter.saveUsers();
    }
}

