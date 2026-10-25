package com.model;

import java.util.ArrayList;

public class UserList {
    private static UserList instance;
    private ArrayList<User> users;

    public UserList() {
        users = DataLoader.getUsers();
    }

    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }

        return instance;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

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

    public User getUser(String username, String password) {
        User user = getUser(username);

        if (user != null && user.checkPassword(password)) {
            return user;
        }

        return null;
    }

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

    public boolean removeUser(User user) {
        if (users.remove(user)) {
            return save();
        }

        return false;
    }

    public boolean save() {
        return DataWriter.saveUsers();
    }
}

