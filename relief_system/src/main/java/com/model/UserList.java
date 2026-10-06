package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserList {
    private static UserList userList;
    private ArrayList<User> users;

    public UserList() {
        this.users = new ArrayList<>();
    }

    public static UserList getInstance() {
        return null;
    }

    public User getUser(String username, String password) {
        return null;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public void addUser(UUID id, String firstName, String lastName, String username, String password, String email, Location location, boolean shareLocation) {
        return;
    }

    public boolean save() {
        return false;
    }
}
