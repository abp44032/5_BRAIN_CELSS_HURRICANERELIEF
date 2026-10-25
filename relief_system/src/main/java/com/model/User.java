package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    protected UUID id;
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String password;
    protected String email;
    protected Location location;
    protected boolean shareLocation;
    protected String role;

    public static final String VOLUNTEER = "VOLUNTEER";
    public static final String RESIDENT = "RESIDENT";
    
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

    public User(String firstName, String lastName, String username,
        String password, String email, Location location,
        boolean shareLocation, String role) {
    this(UUID.randomUUID(), firstName, lastName, username,
            password, email, location, shareLocation, role);
    }

    public User(UUID id, String firstName, String lastName, String username,
            String password, String email, Location location,
            boolean shareLocation, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.location = location;
        this.shareLocation = shareLocation;
        this.role = role;
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

    public String getRole() {
    return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public List<Shelter> viewShelters() {
        return new ArrayList<>();
    }

    public boolean checkPassword(String password) {
        return this.password != null && this.password.equals(password);
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

        public void setShareLocation(boolean shareLocation) {
        this.shareLocation = shareLocation;
    }

    // -------- MAIN METHOD --------- 
    public static void main(String[] args) {
        UUID testId = UUID.randomUUID();
        Location testLocation = new Location();

        User testUser = new User(
        testId,
        "John",
        "Doe",
        "johndoe",
        "password",
        "johndoe@example.com",
        testLocation,
        false
        );

        UserList.getInstance().getUsers().add(testUser);

        System.out.println("User ID: " + testUser.getId());
        System.out.println("First Name: " + testUser.getFirstName());
        System.out.println("Last Name: " + testUser.getLastName());
        System.out.println("Username: " + testUser.getUsername());
        System.out.println("Password: " + testUser.getPassword());
        System.out.println("Email: " + testUser.getEmail());
        System.out.println("Location: " + testUser.getLocation());
        System.out.println("Share Location: " + testUser.isShareLocation());

        System.out.println("Total users: " + UserList.getInstance().getUsers().size());
    }

    public static User create(UUID id2, String firstName2, String lastName2, String username2, String password2,
            String email2, Location location2, boolean shareLocation2) {
                System.out.println("Creating user with ID: " + id2);
        return new User(id2, firstName2, lastName2, username2, password2, email2, location2, shareLocation2);
    }

}
