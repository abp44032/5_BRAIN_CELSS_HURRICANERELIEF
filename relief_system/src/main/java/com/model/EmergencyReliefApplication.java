package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class EmergencyReliefApplication {

    private UserList userList;
    private User currentUser;

    public EmergencyReliefApplication() {
        userList = UserList.getInstance();
    }

    /**
     * ---------LOGGING IN--------------
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username, String password) {
        User user = userList.getUser(username, password);

        if (user == null) {
            return false;
        }

        currentUser = user;
        return true;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout(){
        currentUser = null;
    }

    /**
     * ---------CREATING ACCOUNT--------------
     * @param firstName
     * @param lastName
     * @param username
     * @param password
     * @param email
     * @return
     */
    public User createAccount(String firstName, String lastName,
            String username, String password, String email) {

        return createAccount(firstName, lastName, username, password,
            email, User.RESIDENT);
    }

    public User createAccount(String firstName, String lastName,
        String username, String password, String email, String role) {

        boolean added = userList.addUser(firstName, lastName, username,
            password, email, null, false, role);

        return added ? userList.getUser(username) : null;
    }

    public ArrayList<Shelter> getShelters() {
        return DataLoader.getShelters();
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
        
        request.updateStatus(RequestStatus.ACCEPTED);

        return DataWriter.saveReliefRequests();
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

    public void submitRequest(ReliefRequest request) {
        if (request == null) { 
            System.out.println("Cannot submit an empty relief request.");
            return;
        }

        ReliefRequest.getInstance().addRequest(request);

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
