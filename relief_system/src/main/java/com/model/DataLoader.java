package com.model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();

        try (FileReader reader = new FileReader(USER_FILE_NAME)) {
            JSONArray usersJSON =
                (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < usersJSON.size(); i++) {
                JSONObject userJSON = (JSONObject) usersJSON.get(i);

                UUID id = UUID.fromString(
                    (String) userJSON.get(USER_ID_KEY)
                );

                String firstName = 
                    (String) userJSON.get(USER_FIRSTNAME);
                String lastName =
                    (String) userJSON.get(USER_LASTNAME);
                String email =
                    (String) userJSON.get(USER_EMAIL);
                String username =
                    (String) userJSON.get(USER_USERNAME);
                String password =
                    (String) userJSON.get(USER_PASSWORD_KEY);

                JSONObject locationJSON =
                    (JSONObject) userJSON.get("location");

                Location location = null;

                if (locationJSON != null) {
                    location = new Location(
                        (String) locationJSON.get("address"),
                        (String) locationJSON.get("city"),
                        (String) locationJSON.get("state")
                    );
                }

                boolean shareLocation = Boolean.TRUE.equals(
                    userJSON.get(USER_SHARE_LOCATION)
                );

                users.add(new User(id, firstName, lastName, username, password, email, location, shareLocation));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public ArrayList<Shelter> getShelters() {
        ArrayList<Shelter> shelters = new ArrayList<>();

        try {
            JSONArray sheltersJSON;

            try (FileReader reader =
                    new FileReader(SHELTER_FILE_NAME)) {
                sheltersJSON =
                    (JSONArray) new JSONParser().parse(reader);
            }

            for (int i = 0; i < sheltersJSON.size(); i++) {
                JSONObject shelter =
                    (JSONObject) sheltersJSON.get(i);

                UUID id = UUID.fromString(
                    (String) shelter.get(SHELTER_ID_KEY)
                );

                String shelterName =
                    (String) shelter.get(SHELTER_NAME);

                int capacity =
                    (int) (long) shelter.get(SHELTER_CAPACITY);

                ArrayList<Resource> resources = new ArrayList<>();
                JSONArray resourcesJSON =
                    (JSONArray) shelter.get(SHELTER_RESOURCES);

                for (int j = 0; j < resourcesJSON.size(); j++) {
                    JSONObject resource =
                        (JSONObject) resourcesJSON.get(j);

                    int resourceID =
                        (int) (long) resource.get(RESOURCE_ID);
                    String resourceName =
                        (String) resource.get(RESOURCE_NAME);
                    int resourceQuantity =
                        (int) (long) resource.get(RESOURCE_QUANTITY);

                    resources.add(new Resource(
                        resourceID, resourceName, resourceQuantity
                    ));
                }

                ArrayList<AccommodationType> accommodations =
                    new ArrayList<>();

                JSONArray accommodationJSON =
                    (JSONArray) shelter.get(SHELTER_ACCOMMODATIONS);

                for (int j = 0; j < accommodationJSON.size(); j++) {
                    String accommodationName =
                        (String) accommodationJSON.get(j);

                    accommodations.add(
                        AccommodationType.valueOf(accommodationName)
                    );
                }

                shelters.add(new Shelter(
                    id, shelterName, capacity,
                    resources, accommodations
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return shelters;
    }

    public ArrayList<ReliefRequest> getReliefRequests() {
        return null;
    }

    public ArrayList<Hurricane> getHurricanes() {
        return null;
    }
}
