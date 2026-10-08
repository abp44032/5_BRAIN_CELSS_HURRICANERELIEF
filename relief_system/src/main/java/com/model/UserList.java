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
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public void addUser(UUID id, String firstName, String lastName, String username, String password, String email, Location location, boolean shareLocation) {
        if (id == null || firstName == null || lastName == null || username == null || password == null || email == null) {
            return;
        }
        User newUser = User.create(id, firstName, lastName, username, password, email, location, shareLocation);
        users.add(newUser);
    }

    public boolean save() {
        return DataWriter.saveUsers();
    }
    public static void main(String[] args) {
        UserList list = UserList.getInstance();
        list.addUser(UUID.randomUUID(), "Jane", "Doe", "janedoe", "pass123", "jane@example.com", null, true);

        System.out.println("Total Users: " + list.getUsers().size());
        System.out.println("Valid User: " + list.getUser("janedoe", "pass123").getUsername());
        System.out.println("Invalid User: " + list.getUser("janedoe", "wrong"));
        System.out.println("Singleton Match: " + (list == UserList.getInstance()));
    }
}
