package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * represents a user in the relief system.
 */
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
    protected String specialty;
    protected String vehicle;


    public static final String VOLUNTEER = "VOLUNTEER";
    public static final String RESIDENT = "RESIDENT";
    
    /**
     * Constructs a new User with the specified details.
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param username the username of the user
     * @param password the password of the user
     * @param email the email of the user
     * @param location the location of the user
     * @param shareLocation whether the user wants to share their location
     */
    public User(String firstName, String lastName, String username, String password,
                String email, Location location, boolean shareLocation) {
        this(UUID.randomUUID(), firstName, lastName, username, password,
                email, location, shareLocation);
 
    }

    /**
     * Constructs a new User with the specified ID and details.
     * @param id the unique identifier of the user
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param username the username of the user
     * @param password the password of the user
     * @param email the email of the user
     * @param location the location of the user
     * @param shareLocation whether the user wants to share their location
     */
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

    /**
     * Constructs a new User with the specified details and role.
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param username the username of the user
     * @param password the password of the user
     * @param email the email of the user
     * @param location the location of the user
     * @param shareLocation whether the user wants to share their location
     * @param role the role of the user
     */
    public User(String firstName, String lastName, String username,
        String password, String email, Location location,
        boolean shareLocation, String role) {
    this(UUID.randomUUID(), firstName, lastName, username,
            password, email, location, shareLocation, role);
    }

    /**
     * Constructs a new User with the specified ID, details, and role.
     * @param id the unique identifier of the user
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param username the username of the user
     * @param password the password of the user
     * @param email the email of the user
     * @param location the location of the user
     * @param shareLocation whether the user wants to share their location
     * @param role the role of the user
     */
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
    
    /**
     * returns the unique identifier of the user
     * @return the UUID of the user
     */
    public UUID getId() {
    return this.id;
    }

    /**
     * returns the first name of the user
     * @return the first name of the user
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * returns the last name of the user
     * @return the last name of the user
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * returns the role of the user
     * @return the role of the user
     */
    public String getRole() {
    return role;
    }

    /**
     * sets the role of the user
     * @param role the role to set for the user
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * returns the username of the user
     * @return the username of the user
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * returns the password of the user
     * @return the password of the user
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * returns the email of the user
     * @return the email of the user
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * returns the location of the user
     * @return the location of the user
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * sets the location of the user
     * @param location the location to set for the user
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * returns whether the user wants to share their location
     * @return true if the user wants to share their location, false otherwise
     */
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

    // fulfilling rosas request
    public String getSpecialty() {
    return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + username + ")";
    }

    /**
     * sets whether the user wants to share their location
     * @param shareLocation true if the user wants to share their location, false otherwise
     */
    public void setShareLocation(boolean shareLocation) {
        this.shareLocation = shareLocation;
    }

    /**
     * runs the main method for testing the User class
     * @param args the command line arguments
     */
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

    /**
     * creates a new User object with the specified parameters
     * @param id2 the unique identifier for the user
     * @param firstName2 the first name of the user
     * @param lastName2 the last name of the user
     * @param username2 the username of the user
     * @param password2 the password of the user
     * @param email2 the email of the user
     * @param location2 the location of the user
     * @param shareLocation2 whether the user wants to share their location
     * @return a new User object with the specified parameters
     */
    public static User create(UUID id2, String firstName2, String lastName2, String username2, String password2,
            String email2, Location location2, boolean shareLocation2) {
                System.out.println("Creating user with ID: " + id2);
        return new User(id2, firstName2, lastName2, username2, password2, email2, location2, shareLocation2);
    }

}
