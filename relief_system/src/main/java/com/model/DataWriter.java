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

        JSONArray jsonShelters = new JSONArray();
        
        for(int i=0; i < shelterList.size(); i++) {
            jsonShelters.add(getShelterJSON(shelterList.get(i)));
        }

        try (FileWriter file = new FileWriter(SHELTER_FILE_NAME)) {

            file.write(jsonShelters.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static JSONObject getShelterJSON(Shelter shelter) {
        JSONObject shelterDetails = new JSONObject();
        shelterDetails.put(SHELTER_ID_KEY, shelter.getId().toString());
        shelterDetails.put(SHELTER_NAME, shelter.getName());
        shelterDetails.put(SHELTER_ACCOMMODATIONS, shelter.getAccommodations());
        shelterDetails.put(SHELTER_CAPACITY, shelter.getCapacity());
        shelterDetails.put(SHELTER_RESOURCES, shelter.getResources());
        return shelterDetails;
    }
        public static void main(String[] args) {
        DataWriter.saveShelters();
    }


        public static void saveReliefRequests() {

        ReliefRequest requests = ReliefRequest.getInstance();
        ArrayList<ReliefRequest> requestList = requests.getRequestId();

        JSONArray jsonRequests = new JSONArray();
        
        for(int i=0; i < requestList.size(); i++) {
            jsonRequests.add(getReliefRequestJSON(requestList.get(i)));
        }

        try (FileWriter file = new FileWriter(REQUEST_FILE_NAME)) {

            file.write(jsonRequests.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static JSONObject getReliefRequestJSON(ReliefRequest request) {
        JSONObject requestDetails = new JSONObject();
        requestDetails.put(REQUEST_FILE_NAME, request.getFile().toString());
        requestDetails.put(REQUEST_ID_KEY, request.getRequestId().toString());
        requestDetails.put(REQUEST_TYPE, request.getType().toString());
        requestDetails.put(REQUEST_URGENCY, request.getUrgency().toString());
        requestDetails.put(REQUEST_SEVERITY, request.getSeverity().toString());
        requestDetails.put(REQUEST_LOCATION, request.getLocation() != null ? request.getLocation().toString() : null);
        return requestDetails;
    }
        public static void main(String[] args) {
        DataWriter.saveReliefRequests();
    }



        public static void saveHurricane() {
        Hurricane hurricane = Hurricane.getInstance();
        ArrayList<User> userList = hurricane.getUsers();

        JSONArray jsonHurricane = new JSONArray();
        
        for(int i=0; i < userList.size(); i++) {
            jsonHurricane.add(getUserJSON(userList.get(i)));
        }

        try (FileWriter file = new FileWriter(HURRICANE_FILE_NAME)) {

            file.write(jsonHurricane.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static JSONObject getHurricaneJSON(Hurricane hurricane) {
        JSONObject hurricaneDetails = new JSONObject();
        hurricaneDetails.put(HURRICANE_ID_KEY, hurricane.getId().toString());
        hurricaneDetails.put(HURRICANE_NAME, hurricane.getName());
        hurricaneDetails.put(HURRICANE_CATAGORY, hurricane.getCategory().toString());
        hurricaneDetails.put(HURRICANE_LOCATION, hurricane.getLocation() != null ? hurricane.getLocation().toString() : null);


        return hurricaneDetails;
    }
        public static void main(String[] args) {
        DataWriter.saveHurricane();
    }
}