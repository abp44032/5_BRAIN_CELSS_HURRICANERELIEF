package com.model;

import java.util.ArrayList;
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


    public User(String firstName, String lastName, String username, String password,
                String email, Location location, boolean shareLocation) {
        this(UUID.randomUUID(), firstName, lastName, username, password,
                email, location, shareLocation);
 
    }
    public User(UUID id, String firstName, String lastName, String username, String password, 
        String email, Location location, boolean shareLocation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.location = location;
        this.shareLocation = shareLocation;
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

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isShareLocation() {
        return this.shareLocation;
    }

    public void setShareLocation(boolean shareLocation) {
        this.shareLocation = shareLocation;
    }

    public boolean checkPassword(String password) {
        return this.password != null && this.password.equals(password);
    }

    public ArrayList<Shelter> viewShelters() {
        return ShelterList.getInstance().getShelters();
    }

    public  ArrayList<Service> viewServices() {
        ArrayList<Service> services = new ArrayList<>();
        return services;
    }

    public void commentOnRequest(ReliefRequest request, String comment) {
        request.addComment(username + ": " + comment);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + username + ")";
    }
}