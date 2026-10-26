package com.model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;  


public class DataLoader extends DataConstants {

    /**
     * Loads users from the JSON file and returns them as an ArrayList of User objects
     * @return ArrayList of User objects loaded from the JSON file
     */
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
                
                Location location = toLocation((JSONObject) userJSON.get("location"));
                boolean shareLocation = Boolean.TRUE.equals(userJSON.get(USER_SHARE_LOCATION));

                String role = (String) userJSON.get("role");

                String specialty = (String) userJSON.get("specialty");
                String vehicle = (String) userJSON.get("vehicle");

                User user = new User(id, firstName, lastName, userName, password, email, location, shareLocation, role);

                user.setSpecialty(specialty);
                user.setVehicle(vehicle);
                users.add(user);
            }
 

            reader.close();
 
        } catch (Exception e) {
            e.printStackTrace();
        } 
 
        return users;
    }


    /**
     * Loads shelters from the JSON file and returns them as an ArrayList of Shelter objects
     * @return ArrayList of Shelter objects loaded from the JSON file
     */
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

                Location location = toLocation((JSONObject) shelter.get("location"));
 
                Object hoursValue = shelter.get(SHELTER_HOURS_OF_OPERATION);
                String hoursOfOperation = null;
                if (hoursValue instanceof JSONObject) {
                    JSONObject hoursJSON = (JSONObject) hoursValue;
                    hoursOfOperation = hoursJSON.get("open") + " - " + hoursJSON.get("close");
                } else if (hoursValue != null) {
                    hoursOfOperation = hoursValue.toString();
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
    
    /**
     * Loads relief requests from the JSON file and returns them as an ArrayList of ReliefRequest objects
     * @return ArrayList of ReliefRequest objects loaded from the JSON file
     */
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

                JSONObject locationJSON = (JSONObject) requestJSON.get(REQUEST_LOCATION);
                Location location = null;
                if (locationJSON != null) {
                    location = new Location(
                        (String) locationJSON.get("address"),
                        (String) locationJSON.get("city"),
                        (String) locationJSON.get("state"),
                        (String) locationJSON.get("zip")
                    );
                }

                String contactInformation =
                    (String) requestJSON.get(REQUEST_CONTACT_INFORMATION);
                if (contactInformation == null) {
                    contactInformation = "";
                }

                String acceptedBy = (String) requestJSON.get("acceptedBy");

                ArrayList<Pet> pets = new ArrayList<>();
                JSONArray petsJSON = (JSONArray) requestJSON.get("pets");
                if (petsJSON != null) {
                    for (int j = 0; j < petsJSON.size(); j++) {
                        JSONObject petJSON = (JSONObject) petsJSON.get(j);
                        pets.add(new Pet((String) petJSON.get("name"), (String) petJSON.get("type")));
                    }
                }
                ReliefRequest request = new ReliefRequest(
                    id, status, type, severity, urgency, description,
                    totalPeople, pets, location, contactInformation
                );

                request.setAcceptedBy(acceptedBy);
                requests.add(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requests;
    }

    //builds a location object from a JSON object containing address, city, state, and zip code.
    private static Location toLocation(JSONObject locationJSON) {
        if (locationJSON == null) {
            return null;
        }
        return new Location(
            (String) locationJSON.get("address"),
            (String) locationJSON.get("city"),
            (String) locationJSON.get("state"),
            (String) locationJSON.get("zip")
        );
    }


    /**
     * Main method for testing getHurricanes() 
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<Hurricane> hurricanes = DataLoader.getHurricanes();
        for (Hurricane hurricane : hurricanes) {
            System.out.println(hurricane);
        }
    }

    /**
     * Loads hurricanes from the JSON file and returns them as an ArrayList of Hurricane objects
     * @return ArrayList of Hurricane objects loaded from the JSON file
     */
    public static ArrayList<Hurricane> getHurricanes() {
        ArrayList<Hurricane> hurricanes = new ArrayList<>();

        try {
            FileReader reader = new FileReader(HURRICANE_FILE_NAME);
            JSONArray hurricanesJSON = (JSONArray) new JSONParser().parse(reader);
            reader.close();

            for (int i = 0; i < hurricanesJSON.size(); i++) {
                JSONObject hurricaneJSON = (JSONObject) hurricanesJSON.get(i);

                System.out.println("ID key expected: " + HURRICANE_ID_KEY);
                System.out.println("Hurricane JSON: " + hurricaneJSON);

                UUID id = UUID.fromString((String) hurricaneJSON.get(HURRICANE_ID_KEY));
                String name = (String) hurricaneJSON.get(HURRICANE_NAME);
                ArrayList<String> zipCodes = new ArrayList<>();
                JSONArray zipCodesJSON = (JSONArray) hurricaneJSON.get(HURRICANE_ZIP_CODES);
                if (zipCodesJSON != null) {
                    for (int j = 0; j < zipCodesJSON.size(); j++) {
                        zipCodes.add((String) zipCodesJSON.get(j));
                    }
                }
                HurricaneStatus hurricaneStatus =
                    HurricaneStatus.valueOf((String) hurricaneJSON.get(HURRICANE_STATUS));
                ArrayList<String> evacuationSteps = new ArrayList<>();
                JSONArray stepsJSON = (JSONArray) hurricaneJSON.get(HURRICANE_EVACUATION_STEPS);
                if (stepsJSON != null) {
                    for (int j = 0; j < stepsJSON.size(); j++) {
                        evacuationSteps.add((String) stepsJSON.get(j));
                    }
                }
                ArrayList<Resource> emergencySupplies = new ArrayList<>();
                JSONArray suppliesJSON = (JSONArray) hurricaneJSON.get(HURRICANE_EMERGENCY_SUPPLIES);
                if (suppliesJSON != null) {
                    for (int j = 0; j < suppliesJSON.size(); j++) {
                        JSONObject supplyJSON = (JSONObject) suppliesJSON.get(j);
                        int resourceID = (int) (long) supplyJSON.get(RESOURCE_ID);
                        String resourceName = (String) supplyJSON.get(RESOURCE_NAME);
                        int resourceQuantity = (int) (long) supplyJSON.get(RESOURCE_QUANTITY);
                        emergencySupplies.add(new Resource(resourceID, resourceName, resourceQuantity));
                    }
                }
                hurricanes.add(new Hurricane(
                    id, name, zipCodes, hurricaneStatus, evacuationSteps, emergencySupplies
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hurricanes;
    }
}