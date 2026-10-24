package com.model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;  


public class DataLoader extends DataConstants {

    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();

        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONArray usersJSON = (JSONArray) new JSONParser().parse(reader);
            for(int i = 0; i < usersJSON.size(); i++) {
                JSONObject userJSON = (JSONObject) usersJSON.get(i);
                UUID id = UUID.fromString((String) userJSON.get(USER_ID_KEY));
                String userName = (String)userJSON.get(USER_USERNAME);
                String firstName = (String)userJSON.get(USER_FIRSTNAME);
                String lastName = (String)userJSON.get(USER_LASTNAME);
                String email = (String)userJSON.get(USER_EMAIL);
                String password = (String)userJSON.get(USER_PASSWORD_KEY);
                
                JSONObject locationJSON = (JSONObject) userJSON.get("location");
                Location location = null;

                if (locationJSON != null) {
                    location = new Location(
                        (String) locationJSON.get("address"),
                        (String) locationJSON.get("city"),
                        (String) locationJSON.get("state"),
                        (String) locationJSON.get("zip")
                    );
                }
                
                boolean shareLocation = Boolean.TRUE.equals(userJSON.get(USER_SHARE_LOCATION));

                users.add(new User(id, firstName, lastName, userName, password, email, location, shareLocation));
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        } 

        return users;
    }

    public static ArrayList<Shelter> getShelters() {
        ArrayList<Shelter> shelters = new ArrayList<>();

        try {
            FileReader reader = new FileReader(SHELTER_FILE_NAME);
            JSONArray sheltersJSON = (JSONArray) new JSONParser().parse(reader);
            reader.close();

            for (int i = 0; i < sheltersJSON.size(); i++) {
                JSONObject shelter = (JSONObject) sheltersJSON.get(i);
                UUID id = UUID.fromString((String) shelter.get(SHELTER_ID_KEY));
                String shelterName = (String)shelter.get(SHELTER_NAME);
                int capacity = (int)(long)shelter.get(SHELTER_CAPACITY);

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

                JSONObject locationJSON =
                    (JSONObject) shelter.get("location");
                Location location = null;
                if (locationJSON != null) {
                    location = new Location(
                        (String) locationJSON.get("address"),
                        (String) locationJSON.get("city"),
                        (String) locationJSON.get("state"),
                        (String) locationJSON.get("zip")
                    );
                }

                JSONObject hoursJSON = (JSONObject) shelter.get(SHELTER_HOURS_OF_OPERATION);
                String hoursOfOperation = null;
                if (hoursJSON != null) {
                    hoursOfOperation = hoursJSON.get("open") + " - " + hoursJSON.get("close");
                }

                shelters.add(new Shelter(
                    id, shelterName, capacity,
                    resources, accommodations, location, hoursOfOperation
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shelters;
    }
    
    public static void main(String[] args) {
        ArrayList<ReliefRequest> requests = DataLoader.getReliefRequests();
        for (ReliefRequest request : requests) {
            System.out.println(request);
        }
    }

    public static ArrayList<ReliefRequest> getReliefRequests() {
        ArrayList<ReliefRequest> requests = new ArrayList<>();

        try {
            FileReader reader = new FileReader(REQUEST_FILE_NAME);
            JSONArray requestsJSON = (JSONArray) new JSONParser().parse(reader);
            reader.close();

            for (int i = 0; i < requestsJSON.size(); i++) {
                JSONObject requestJSON = (JSONObject) requestsJSON.get(i);

                UUID id = UUID.fromString((String) requestJSON.get(REQUEST_ID_KEY));
                RequestStatus status =
                    RequestStatus.valueOf((String) requestJSON.get(REQUEST_STATUS));
                RequestType type =
                    RequestType.valueOf((String) requestJSON.get(REQUEST_TYPE));
                SeverityLevel severity =
                    SeverityLevel.valueOf((String) requestJSON.get(REQUEST_SEVERITY));
                UrgencyLevel urgency =
                    UrgencyLevel.valueOf((String) requestJSON.get(REQUEST_URGENCY));
                String description = (String) requestJSON.get(REQUEST_DESCRIPTION);
                int totalPeople = (int) (long) requestJSON.get(REQUEST_TOTAL_PEOPLE);

                ArrayList<Pet> pets = new ArrayList<>();
                JSONArray petsJSON = (JSONArray) requestJSON.get("pets");
                if (petsJSON != null) {
                    for (int j = 0; j < petsJSON.size(); j++) {
                        JSONObject petJSON = (JSONObject) petsJSON.get(j);
                        pets.add(new Pet((String) petJSON.get("name"), (String) petJSON.get("type")));
                    }
                }
                requests.add(new ReliefRequest(
                    id, status, type, severity, urgency, description, totalPeople, pets
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requests;
    }

    public ArrayList<Hurricane> getHurricanes() {
        return null;
    }
}