package com.model;

import java.util.ArrayList;

public class EmergencyReliefApplication {

    private UserList userList;
    private User currentUser;

    public EmergencyReliefApplication() {
        userList = UserList.getInstance();
    }

    /**
     * Logs a user into the emergny releif system.
     * @param username the username of the user
     * @param password the password of the user
     * @return true if successful login, otherwise false
     */
    public boolean login(String username, String password) {
        User user = userList.getUser(username, password);

        if (user == null) {
            return false;
        }

        currentUser = user;
        return true;
    }

    /**
     * Returns currently logged in user.
     * @return the current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Logs out the current user.
     */
    public void logout(){
        currentUser = null;
    }

    /**
     * Creates a new user account for a resident.
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param username the username of the user
     * @param password the password of the user
     * @param email the email of the user
     * @return the created user, otherwise null if unsuccessful
     */
    public User createAccount(String firstName, String lastName,
            String username, String password, String email) {

        return createAccount(firstName, lastName, username, password,
                email, User.RESIDENT, null, null, false);
    }

    /**
     * Creates a new user account for a specified role.
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param username the username of the user
     * @param password the password of the user
     * @param email the email of the user
     * @param role the role of the user
     * @return the created user, otherwise null if unsiccessful
     */
    public User createAccount(String firstName, String lastName,
            String username, String password, String email, String role) {

        return createAccount(firstName, lastName, username, password,
                email, role, null, null, false);
    }

    /**
     * Creates a new user account with more information.
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param username the username of the user
     * @param password the password of the user
     * @param email the email of the user
     * @param role the role of the user
     * @param specialty the specialty of the user
     * @param location the location of the user
     * @param shareLocation whether the user shares their location
     * @return the created user, otherwise null if unsuccessful
     */
    public User createAccount(String firstName, String lastName,
            String username, String password, String email, String role,
            String specialty, Location location, boolean shareLocation) {

        boolean added = userList.addUser(
                firstName, lastName, username, password, email,
                location, shareLocation, role);

        if (!added) {
            return null;
        }

        User user = userList.getUser(username);

        if (user != null && specialty != null) {
            user.setSpecialty(specialty);
            DataWriter.saveUsers();
        }

        return user;
    }

    /**
     * Returns the available shelters in the system.
     * @return a list of shelters
     */
    public ArrayList<Shelter> getShelters() {
        return Shelter.getInstance().getShelters();
    }

    public void sendMessage(User recipient, String message) {
        // TODO
    }

    public void shareLocation(Location location) {
        // TODO
    }

    public void commentOnRequest(ReliefRequest request, String comment) {
        // TODO
    }

    /**
     * Allows the volunteer to accept a relief request,
     * Checks that the user is a volunteer and the request is available,
     * Assigns the volunteer, updates the status, and saves the request.
     * @param request the relief request being accepted
     * @param volunteer the volunteer accepting the request
     * @return true if the request is saved successfully, otherwise return false
     */
    public boolean acceptRequest(ReliefRequest request, User volunteer) {
        if (request == null || volunteer == null) {
            return false;
        }

        if (!User.VOLUNTEER.equals(volunteer.getRole())) {
            return false;
        }
        
        if (request.getStatus() != RequestStatus.RECEIVED) {
            return false;
        }
        
        request.setAcceptedBy(
        volunteer.getFirstName() + " " + volunteer.getLastName()
        );

        request.updateStatus(RequestStatus.ACCEPTED);

        return DataWriter.saveReliefRequests();
    }

    public boolean completeRequest(ReliefRequest request) {
    if (request == null || currentUser == null) {
        return false;
    }

        // Only the shelter manager can complete a request.
        if (!"SHELTER_MANAGER".equals(currentUser.getRole())) {
            System.out.println(
                "Permission denied: Volunteers cannot complete relief requests."
            );
            return false;
        }

        if (request.getStatus() != RequestStatus.ACCEPTED) {
            System.out.println(
                "The request must be accepted before it can be completed."
            );
            return false;
        }

        request.updateStatus(RequestStatus.COMPLETED);

        if (!DataWriter.saveReliefRequests()) {
            System.out.println("Failed to save the completed request.");
            return false;
        }

        System.out.println("Relief request marked as completed.");
        return true;
    }

    public boolean checkInFamily(
        Shelter shelter,
        ArrayList<String> familyMembers,
        ArrayList<String> pets) {

        if (currentUser == null || shelter == null) {
            return false;
        }

        if (!"SHELTER_MANAGER".equals(currentUser.getRole())) {
            System.out.println(
                "Permission denied: Only a shelter manager can check in a family."
            );
            return false;
        }

        if (familyMembers == null || pets == null) {
            return false;
        }

        if (familyMembers.size() > shelter.getAvailableSpaces()) {
            System.out.println("Not enough available spaces at this shelter.");
            return false;
        }

        if (!shelter.checkInFamily(familyMembers, pets)) {
            System.out.println("Family check-in failed.");
            return false;
        }

        if (!DataWriter.saveShelters()) {
            System.out.println("Failed to save shelter information.");
            return false;
        }

        System.out.println("Family and pets checked in successfully.");
        return true;
    }

    public void updateVolunteerStatus(String status) {
        // TODO
    }

    public String getVolunteerStatus() {
        // TODO
        return null;
    }

    public ArrayList<ReliefRequest> viewRequestsNearby(double radius) {
        // TODO
        return null;
    }

    public ArrayList<String> getNeeds() {
        // TODO
        return null;
    }

    public String getLiveLocation() {
        // TODO
        return null;
    }

    public boolean contactEMS() {
        // TODO
        return false;
    }

    public void requestAid(ReliefRequest request) {
        // TODO
    }

    public void markSafe() {
        // TODO
    }

    public int viewETA() {
        // TODO
        return 0;
    }

    /**
     * Submits a new relief request to the system,
     * Adds the request to the request list and saves it.
     * @param request the relief request being submitted
     */
    public void submitRequest(ReliefRequest request) {
        if (request == null) { 
            System.out.println("Cannot submit an empty relief request.");
            return;
        }

        ReliefRequestList.getInstance().addRequest(request);

        boolean saved = DataWriter.saveReliefRequests();

        if (saved) {
            System.out.println("Relief request submitted successfully.");
        } else {
            System.out.println("Failed to save relief request.");
        }
    }

    public void deleteRequest(ReliefRequest request) {
        // TODO
    }

    public void reviewRequest(ReliefRequest request) {
        // TODO
    }

    public boolean approveRequest(ReliefRequest request) {
        // TODO
        return false;
    }

    public void flagRequest(ReliefRequest request) {
        // TODO
    }

    public void updateOutdated() {
        // TODO
    }

    public void reportDamage() {
        // TODO
    }

    public void attachDamagePhoto(String photo) {
        // TODO
    }

    public ArrayList<Resource> viewEmergencySupplies() {
        // TODO
        return null;
    }
}
