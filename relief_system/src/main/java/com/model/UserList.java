package com.model;

import java.util.ArrayList;
import com.model.User;
import com.model.DataWriter;

public class UserList {

    private ArrayList users;

    public UserList() {
        this.users = new ArrayList<>();
    }
// Parameterized Constructor
    public UserList(ArrayList users) {
        if (users == null) {
        this.users = new ArrayList<>();
    } else {
        this.users = new ArrayList<>(users);
        }
    }
// Accessors
    public ArrayList getUsers() {
        return new ArrayList<>(this.users);
    }
    public User getUser(String userName) {
        for (User u : this.users) {

        if (u.getUserName().equalsIgnoreCase(userName)) {

        return u;
        }
    }
    return null;
    }
// Mutators
    public void setUsers(ArrayList users) {
        if (users == null) {
        this.users = new ArrayList<>();
    } else {
        this.users = new ArrayList<>(users);
        }
    }
// Methods
    public boolean addUser(User user) {
    if (user == null || this.getUser(user.getUserName()) != null) {
         return false;
    }
        this.users.add(user);
        return true;
    }   
        public boolean save() {
        return DataWriter.saveUsers(this.users);
    }
}