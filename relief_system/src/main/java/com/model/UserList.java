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
    
    public User getUser(String username, String password) {
        User user = getUser(username);

        if (user != null && user.getPassword() != null
                && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    
    public boolean addUser(String firstName, String lastName,
            String username, String password, String email,
            Location location, boolean shareLocation) {

        if (firstName == null || lastName == null
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
        
        users.add(new User(firstName, lastName, username, password,
                email, location, shareLocation));

        return save();
    }

    public boolean removeUser(User user) {
        return users.remove(user);
    }

    public boolean save() {
        return DataWriter.saveUsers();
    }

    private static boolean isBlank(String s) {
        return s == null || s.isBlank();
    }
}