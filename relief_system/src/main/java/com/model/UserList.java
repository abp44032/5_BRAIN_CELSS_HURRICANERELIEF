package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserList {
    private static UserList userList;
    private ArrayList<User> users;

    public UserList() {
        this.users = new ArrayList<>();
        //add temparory data
    }

    public static UserList getInstance() {
        if (userList == null) {
            userList = new UserList();
        }
        return userList;        
    }

    public User getUser(String username, String password) {
        //finish
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
