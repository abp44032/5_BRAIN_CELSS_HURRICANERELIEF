package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserList {
    private static UserList instance;
    private List<User> users;

    public UserList() {
        this.users = new ArrayList<>();
    }

    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public User getUserById(UUID id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    public List<User> getUsers() {
        return users;
    }
}
