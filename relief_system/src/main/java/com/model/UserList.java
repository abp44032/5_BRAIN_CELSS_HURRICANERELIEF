package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class UserList {

    private static UserList userList;
    private ArrayList<User> users;

    public UserList() {
        users = new DataLoader().getUsers();

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

    public User getUser(String username, String password) {
        if (username == null || password == null) {
            return null;
        }
        
        for (User user : users) {
            if (user.getUsername() != null
                    && user.getPassword() != null
                    && user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
    
    public boolean addUser(UUID id, String firstName, String lastName,
            String username, String password, String email,
            Location location, boolean shareLocation) {

        if (id == null || firstName == null || lastName == null
                || username == null || password == null || email == null) {
            return false;
        }

        // Prevent duplicate usernames.
        for (User user : users) {
            if (user.getUsername() != null
                    && user.getUsername().equals(username)) {
                return false;
            }
        }

        User newUser = new User(
            id, firstName, lastName, username, password,
            email, location, shareLocation
        );

        users.add(newUser);

        return true;
    }

    public boolean save() {
        return new DataWriter().saveUsers();
    }
}