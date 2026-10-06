package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserList {
    private List<User> users;

    public UserList() {
        this.users = new ArrayList<>();
    }

    public static UserList getInstance() {
        return null;
    }

    public void addUser(User user) {
    }

    public User getUserById(UUID id) {
        return null;
    }

    public User getUserByUsername(String username) {
        return null;
    }

    public void removeUser(User user) {
    }

    public List<User> getUsers() {
        return null;
    }
}
