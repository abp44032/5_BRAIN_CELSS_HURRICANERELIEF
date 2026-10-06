package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class User {
    protected UUID id;
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String password;
    protected String email;
    protected Location location;
    protected boolean shareLocation;

    public User(UUID id, String firstName, String lastName, String username, String password, String email, Location location, boolean shareLocation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.location = null;
        this.shareLocation = false;
    }

    public Information viewHurricaneInfo() {
        return null;
    }
    public List<Shelter> viewShelters() {
        return null;
    }

    public  List<Service> viewServices() {
        return null;
    }

    public void commentOnRequest(ReliefRequest request, String comment) {
    }
}
