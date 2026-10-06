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

    public UUID getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public Location getLocation() {
        return this.location;
    }

    public boolean isShareLocation() {
        this.shareLocation = shareLocation;
    }

    public Information viewHurricaneInfo() {
        return null;
    }
    public List<Shelter> viewShelters() {
        return new ArrayList<>();
    }

    public  List<Service> viewServices() {
        return new ArrayList<>();
    }

    public void commentOnRequest(ReliefRequest request, String comment) {
    }
}
