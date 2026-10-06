package com.model;
import java.util.ArrayList;

public class EmergencyReliefApplication {
        
    public User createAccount(String username, String password, String email) {
        // TODO
        return null;
    }

    public User login(String username, String password) {
        // TODO
        return null;
    }

    public ArrayList<Shelter> getShelters() {
        // TODO
        return null;
    }
    
    public boolean login(String username, String password) {
        User user = userList.getUserByUsername(username);

        if (user == null) {
            return false;
        }
        return user.checkPassword(password);
    }
    
    public void sendMessage(User recipient, String message) {
        //TODO
    }

    public void shareLocation(Location location) {
        //TODO
    }

    public void commentOnRequest(ReliefRequest request, String comment) {
        //TODO
    }

    public boolean acceptRequest(ReliefRequest request, Volunteer volunteer) {
        //TODO
        return false;
    }

    public void updateVolunteerStatus(String status){
        //TODO
    }

    public String getVolunteerStatus() {
        //TODO
        return null;
    }

    public ArrayList<ReliefRequest> viewRequestsNearby(double radius) {
        //TODO
        return null;
    }

    public ArrayList<String> getNeeds(){
        //TODO
        return null;
    }

    public String getLiveLocation(){
        //TODO
        return null;
    }

    public boolean contactEMS() {
        //TODO
        return false;
    }

    public void requestAid(ReliefRequest request) {
        //TODO
    }

    public void markSafe(){
        //TODO
    }

    public int viewETA() {
        //TODO
        return 0;
    }

    public void submitRequest(ReliefRequest request) {
        //TODO
    }

    public void deleteRequest(ReliefRequest request) {
        //TODO
    }

    public void verifyInfo(Information info) {
        //TODO
    }

    public void updateInfo(Information info) {
        //TODO
    }

    public void reviewRequest(ReliefRequest request) {
        //TODO
    }

    public boolean approveRequest(ReliefRequest request) {
        //TODO
        return false;
    }

    public void flagRequest(ReliefRequest request) {
        //TODO
    }

    public void updateOutdated(){
        //TODO
    }

    public void reportDamage() {
        //TODO
    }

    public void attachDamagePhoto(String photo) {
        //TODO
    }

    public ArrayList<Resource> viewEmergencySupplies(){
        //TODO
        return null;

    }
}

