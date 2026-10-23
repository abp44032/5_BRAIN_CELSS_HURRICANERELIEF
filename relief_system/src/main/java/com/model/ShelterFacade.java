package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class ShelterFacade {
    private User currentUser;
    private UserList userList;
    private ShelterList shelterList;

    public ShelterFacade() {   
        this.userList = UserList.getInstance();
        this.shelterList = ShelterList.getInstance();
        this.currentUser = null;
    }

    public boolean login(String username, String password) {
       if (username == null || password == null) {
            return false;
        }
        User user = userList.getUser(username, password);
        if (user != null) {
            this.currentUser = user;
            return true;
        }
        return false;
    }

    public void logout() {
        this.currentUser = null;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public ArrayList<Shelter> getShelters() {
        return this.shelterList.getShelters();
    }

    public Shelter findShelter(String name) {
        if (name == null) {
            return null;
        }
        return this.shelterList.getShelter(name);
    }

    public boolean checkShelterAvailability(String shelterName) {
        return false;
    }

    public double getDistanceToShelter(String shelterName, Location userLoc) {
        return 0.0;
    }

    // -------- MAIN METHOD --------- 
    public static void main(String[] args) {
        ShelterFacade facade = new ShelterFacade();
        UserList.getInstance().addUser(UUID.randomUUID(), "Alex", "Smith", "asmith", "pass456", "asmith@sc.edu", null, true);

        System.out.println("Login: " + facade.login("asmith", "pass456"));
        System.out.println("Current User: " + facade.getCurrentUser().getUsername());

        facade.logout();
        System.out.println("Logout: " + facade.getCurrentUser());
    }
}
