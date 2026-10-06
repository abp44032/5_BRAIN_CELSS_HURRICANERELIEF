package com.model;
import java.util.ArrayList;

public class ShelterFacade {
    private User currentUser;
    private UserList userList;
    private ShelterList shelterList;
    public ShelterFacade() {   

    }

    public boolean login(String username, String password) {
        return false;
    }

    public void logout() {
        
    }

    public User getCurrentUser() {
        return null;
    }

    public ArrayList<Shelter> getShelters() {
        return null;
    }

    public Shelter findShelter(String name) {
        return null;
    }

    public boolean checkShelterAvailability(String shelterName) {
        return false;
    }

    public double getDistanceToShelter(String shelterName, Location userLoc) {
        return 0.0;
    }

}
