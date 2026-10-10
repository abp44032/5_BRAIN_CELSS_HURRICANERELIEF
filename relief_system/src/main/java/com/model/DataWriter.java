package com.model;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class DataWriter extends DataConstants {
   
    public static void saveUsers() {
        User users = User.getInstance();
        ArrayList<User> userList = users.getUser();

        JSONArray jsonUsers = new JSONArray();
        
        for(int i=0; i < userList.size(); i++) {
            jsonUsers.add(getUserJSON(userList.get(i)));
        }

        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {

            file.write(jsonUsers.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_ID_KEY, user.getId().toString());
        userDetails.put(USER_FIRSTNAME, user.getFirstName());
        userDetails.put(USER_LASTNAME, user.getLastName());
        userDetails.put(USER_EMAIL, user.getEmail());
        userDetails.put(USER_USERNAME, user.getUsername());
        userDetails.put(USER_PASSWORD, user.getPassword());
        userDetails.put(USER_LOCATION, user.getLocation() != null ? user.getLocation().toString() : null);
        userDetails.put(USER_SHARE_LOCATION, user.isShareLocation());

        return userDetails;
    }
        public static void main(String[] args) {
        DataWriter.saveUsers();
    }

        public static void saveShelters() {
        Shelter shelters = Shelter.getInstance();
        ArrayList<Shelter> shelterList = shelters.getShelter();

        JSONArray jsonShelter = new JSONArray();
        
        for(int i=0; i < shelterList.size(); i++) {
            jsonShelter.add(getShelterJSON(shelterList.get(i)));
        }

        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {

            file.write(jsonShelter.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static JSONObject getShelterJSON(Shelter shelter) {
        JSONObject shelterDetails = new JSONObject();
        shelterDetails.put(SHELTER_ID_KEY, shelter.getId().toString());
        shelterDetails.put(SHELTER_NAME, shelter.getName());
        shelterDetails.put(SHELTER_CAPACITY, shelter.getAddress());
        shelterDetails.put(SHELTER_RESOURCES, shelter.getCapacity());
        return shelterDetails;
    }
        public static void main(String[] args) {
        DataWriter.saveShelters();
    }
    
        public static void saveReliefRequests() {
        ReliefRequest requests = ReliefRequest.getInstance();
        ArrayList<ReliefRequest> requestList = requests.getReliefRequests();

        JSONArray jsonRequests = new JSONArray();
        
        for(int i=0; i < requestList.size(); i++) {
            jsonRequests.add(getReliefRequestJSON(requestList.get(i)));
        }

        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {

            file.write(jsonRequests.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
public static JSONObject getReliefRequestJSON(ReliefRequest reliefRequest) {
        JSONObject reliefRequestDetails = new JSONObject();
        reliefRequestDetails.put(REQUEST_ID_KEY, reliefRequest.getId().toString());
        reliefRequestDetails.put(REQUEST_TYPE, reliefRequest.getName());
        reliefRequestDetails.put(REQUEST_URGENCY, reliefRequest.getDescription());
        reliefRequestDetails.put(REQUEST_SEVERITY, reliefRequest.getStatus());
        reliefRequestDetails.put(REQUEST_LOCATION, reliefRequest.getStatus());

        return reliefRequestDetails;
    }
        public static void main(String[] args) {
        DataWriter.saveReliefRequests();
    }
}