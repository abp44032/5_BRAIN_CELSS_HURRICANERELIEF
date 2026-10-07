package com.model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;  


public class DataLoader extends DataConstants {

    public ArrayList<User> getUsers() {
        // TODO: commented out to priortize getShelters() working properly
        /* ArrayList<User> users = new ArrayList<>();

        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONArray usersJSON = (JSONArray) new JSONParser().parse(reader);
            for(int i = 0; i < usersJSON.size(); i++) {
                JSONObject user = (JSONObject) usersJSON.get(i);
                UUID id = UUID.fromString((String) user.get(USER_ID_KEY));
                String firstName = (String)user.get(USER_FIRSTNAME);
                String lastName = (String)user.get(USER_LASTNAME);
                String email = (String)user.get(USER_EMAIL);
                String userName = (String)user.get(USER_USERNAME);
                // TODO: location
                boolean shareLocation = (boolean)user.get(USER_SHARE_LOCATION);

                users.add(new User(id, firstName, lastName, userName, password, email, location, shareLocation));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } */
        return null;
    }
    
    public ArrayList<Shelter> getShelters() {
        ArrayList<Shelter> shelters = new ArrayList<>();

        try {
            FileReader reader = new FileReader(SHELTER_FILE_NAME);
            JSONArray sheltersJSON = (JSONArray) new JSONParser().parse(reader);
            for(int i = 0; i < sheltersJSON.size(); i++) {
                JSONObject shelter = (JSONObject) sheltersJSON.get(i);
                UUID id = UUID.fromString((String) shelter.get(SHELTER_ID_KEY));
                String shelterName = (String)shelter.get(SHELTER_NAME);
                int capacity = (int)(long)shelter.get(SHELTER_CAPACITY);
                // TODO: resources
                // TODO: accommodations
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<ReliefRequest> getReliefRequests() {
        return null;
    }

    public ArrayList<Hurricane> getHurricanes() {
        return null;
    }
}
