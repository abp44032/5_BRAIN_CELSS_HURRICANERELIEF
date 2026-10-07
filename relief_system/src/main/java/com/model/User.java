package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Location{

    public Location() {
        //TODO Auto-generated constructor stub
    }

    public Location() {
        //TODO Auto-generated constructor stub
    }

    public Location() {
        //TODO Auto-generated constructor stub
    }}
class Information{}
class Shelter{}
class Service{}
class ReliefRequest{}

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
        return this.shareLocation;
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

    private static class ConcreteUser extends User {
        public ConcreteUser(UUID id, String firstName, String lastName, String username, String password, String email, Location location, boolean shareLocation) {
            super(id, firstName, lastName, username, password, email, location, shareLocation);
        }
    }

    public static void main(String[] args) {
        UUID testId = UUID.randomUUID();
        Location testLocation = new Location();

        User testUser = new ConcreteUser(
        testId,
        "John",
        "Doe",
        "johndoe",
        "password",
        "johndoe@example.com",
        testLocation,
        false
        );

        System.out.println("User ID: " + testUser.getId());
        System.out.println("First Name: " + testUser.getFirstName());
        System.out.println("Last Name: " + testUser.getLastName());
        System.out.println("Username: " + testUser.getUsername());
        System.out.println("Password: " + testUser.getPassword());
        System.out.println("Email: " + testUser.getEmail());
        System.out.println("Location: " + testUser.getLocation());
        System.out.println("Share Location: " + testUser.isShareLocation());
    }

    public static User create(UUID id2, String firstName2, String lastName2, String username2, String password2,
            String email2, Location location2, boolean shareLocation2) {
                System.out.println("Creating user with ID: " + id2);
        return new ConcreteUser(id2, firstName2, lastName2, username2, password2, email2, location2, shareLocation2);
    }

}
