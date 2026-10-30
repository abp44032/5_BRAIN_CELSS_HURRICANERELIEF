package com.model;

	
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataWriter extends DataConstants {
	
	/**
     * Saves users to the user's JSON file
     * @return true if user is successfully saved
     */
    
	public static boolean saveUsers() {

		JSONArray jsonUsers = new JSONArray();

        // Read existing users first.
        try (BufferedReader reader = DataLoader.getReaderFromFile(USER_FILE_NAME, JUNIT_USER_FILE_NAME)) {
            JSONParser parser = new JSONParser();
            Object data = parser.parse(reader);

            if (data instanceof JSONArray) {
                jsonUsers = (JSONArray) data;
            }
        } catch (FileNotFoundException e) {
            // File does not exist yet.
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return false;
        }

        // Add users that are not already saved.
        ArrayList<User> users = UserList.getInstance().getUsers();

        for (User user : users) {
            if (user == null) {
                continue;
            }

            boolean alreadyExists = false;

            for (Object item : jsonUsers) {
                if (item instanceof JSONObject) {
                    JSONObject existing = (JSONObject) item;
                    Object existingId = existing.get(USER_ID_KEY);

                    if (existingId != null
                            && existingId.toString().equals(user.getId().toString())) {
                        alreadyExists = true;
                        
                        JSONObject updatedUser = getUserJSON(user);
                        jsonUsers.set(jsonUsers.indexOf(item), updatedUser);
                        break;
                    }
                }
            }

            if (!alreadyExists) {
                jsonUsers.add(getUserJSON(user));
            }
        }

        // Write the updated JSON array.
        try (FileWriter file = new FileWriter(getFileWritingPath(USER_FILE_NAME, JUNIT_USER_FILE_NAME))) {
            file.write(jsonUsers.toJSONString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

	}
	
    //data constants
	public static JSONObject getUserJSON(User user) {
		JSONObject userDetails = new JSONObject();

	    userDetails.put(USER_ID_KEY, user.getId().toString());
		userDetails.put(USER_USERNAME, user.getUsername());
		userDetails.put(USER_FIRSTNAME, user.getFirstName());
		userDetails.put(USER_LASTNAME, user.getLastName());
        userDetails.put(USER_EMAIL, user.getEmail());
        userDetails.put(USER_PASSWORD_KEY, user.getPassword());
        userDetails.put(USER_SHARE_LOCATION, user.isShareLocation());

        if (user.getRole() != null) {
            userDetails.put("role", user.getRole());
        }
        if (user.getLocation() != null) {
            userDetails.put("location", getLocationJSON(user.getLocation()));
        }

        if (user.getSpecialty() != null) {
            userDetails.put("specialty", user.getSpecialty());
        }

        if (user.getVehicle() != null) {
            userDetails.put("vehicle", user.getVehicle());
        }

        return userDetails;
	}
    /**
     * Saves shelters to the shelters's JSON file
     * @return true if shelter is successfully saved
     */
    public static boolean saveShelters() {
        Shelter shelter = Shelter.getInstance();
		ArrayList<Shelter> ShelterList = shelter.getShelters();

		JSONArray jsonShelter = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< ShelterList.size(); i++) {
			jsonShelter.add(getShelterJSON(ShelterList.get(i)));
		}
		
		//writing JSON file
        try (FileWriter file = new FileWriter(getFileWritingPath(SHELTER_FILE_NAME, JUNIT_SHELTER_FILE_NAME))) {
 
            file.write(jsonShelter.toJSONString());
            return true;
 
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
	}
	//data constants
	public static JSONObject getShelterJSON(Shelter shelter) {
		JSONObject shelterDetails = new JSONObject();

		shelterDetails.put(SHELTER_ID_KEY, shelter.getId().toString());
		shelterDetails.put(SHELTER_NAME, shelter.getName());
		shelterDetails.put(SHELTER_CAPACITY, shelter.getCapacity());

        shelterDetails.put("currentOccupancy", shelter.getCurrentOccupancy());

        JSONArray occupantsJSON = new JSONArray();
        for (String occupant : shelter.getOccupants()) {
            occupantsJSON.add(occupant);
        }
        shelterDetails.put("occupants", occupantsJSON);

        JSONArray petsJSON = new JSONArray();
        for (String pet : shelter.getAccompanyingPets()) {
            petsJSON.add(pet);
        }
        shelterDetails.put("accompanyingPets", petsJSON);

        JSONArray resourcesJSON = new JSONArray();
        for (Resource resource : shelter.getResources()) {
            JSONObject resourceJSON = new JSONObject();
            resourceJSON.put(RESOURCE_ID, resource.getResourceID());
            resourceJSON.put(RESOURCE_NAME, resource.getName());
            resourceJSON.put(RESOURCE_QUANTITY, resource.getQuantity());
            resourcesJSON.add(resourceJSON);
        }
        shelterDetails.put(SHELTER_RESOURCES, resourcesJSON);

        JSONArray accommodationsJSON = new JSONArray();
        for (AccommodationType type : shelter.getAccommodations()) {
            accommodationsJSON.add(type.toString());
        }
		shelterDetails.put(SHELTER_ACCOMMODATIONS, accommodationsJSON);

        if (shelter.getLocation() != null) {
            shelterDetails.put(SHELTER_LOCATION, getLocationJSON(shelter.getLocation()));
        }

        String hours = shelter.getHoursOfOperation();
        if (hours != null && hours.contains(" - ")) {
            String[] parts = hours.split(" - ", 2);
            JSONObject hoursJSON = new JSONObject();
            hoursJSON.put("open", parts[0]);
            hoursJSON.put("close", parts[1]);
            shelterDetails.put(SHELTER_HOURS_OF_OPERATION, hoursJSON);
        }
		return shelterDetails;
	}
    /**
     * Saves relief requests to the JSON file
     * @return true if request is successfully saved
     */
    public static boolean saveReliefRequests() {

		JSONArray jsonRequest = new JSONArray();
		
		//writing JSON file
        try (BufferedReader reader = DataLoader.getReaderFromFile(REQUEST_FILE_NAME, JUNIT_REQUEST_FILE_NAME)) {
            JSONParser parser = new JSONParser();
            Object data = parser.parse(reader);
            
            if (data instanceof JSONArray) {
                jsonRequest = (JSONArray) data;
            }
 
        } catch (FileNotFoundException e) {
            // File Does not exist yet
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return false;
        }

    // Add new requests that are not already in the JSON file.
    ArrayList<ReliefRequest> newRequests =
            ReliefRequestList.getInstance().getRequests();

    for (ReliefRequest request : newRequests) {
        if (request == null) {
            continue;
        }

        boolean alreadyExists = false;

        for (Object item : jsonRequest) {
            if (item instanceof JSONObject) {
                JSONObject existing = (JSONObject) item;
                Object existingId = existing.get(REQUEST_ID_KEY);

                if (existingId != null
                        && existingId.toString().equals(
                                request.getId().toString())) {
                    alreadyExists = true;

                    jsonRequest.set(jsonRequest.indexOf(item), getReliefRequestJSON(request));
                    
                    break;
                }
            }
        }

        if (!alreadyExists) {
            jsonRequest.add(getReliefRequestJSON(request));
        }
    }

    try (FileWriter file = new FileWriter(getFileWritingPath(REQUEST_FILE_NAME, JUNIT_REQUEST_FILE_NAME))) {
        file.write(jsonRequest.toJSONString());
        return true;
    } catch (IOException e) {
        e.printStackTrace();
        return false;
        }
    }

    //data constants
	public static JSONObject getReliefRequestJSON(ReliefRequest request) {
		JSONObject requestDetails = new JSONObject();
		requestDetails.put(REQUEST_ID_KEY, request.getId().toString());
		requestDetails.put(REQUEST_DESCRIPTION, request.getDescription());
        requestDetails.put(REQUEST_TOTAL_PEOPLE, request.getTotalPeople());
        requestDetails.put(REQUEST_TYPE, request.getType().toString());
        requestDetails.put(REQUEST_SEVERITY, request.getSeverity().toString());
        requestDetails.put(REQUEST_URGENCY, request.getUrgency().toString());
		requestDetails.put(REQUEST_STATUS, request.getStatus().toString());
        requestDetails.put(REQUEST_CONTACT_INFORMATION, request.getContactInformation());
        
        if (request.getLocation() != null) {
            requestDetails.put(REQUEST_LOCATION, getLocationJSON(request.getLocation()));
        }

        JSONArray petsJSON = new JSONArray();
        for(Pet pet : request.getPets()) {
            JSONObject petJSON = new JSONObject();
            petJSON.put("name", pet.getName());
            petJSON.put("type", pet.getType());
            petsJSON.add(petJSON);
        }
        requestDetails.put("pets", petsJSON);
        requestDetails.put("acceptedBy", request.getAcceptedBy());
        
        if (request.getAssignedShelterId() != null) {
            requestDetails.put("assignedShelterId",
            request.getAssignedShelterId().toString());
        }
        
		return requestDetails;
	}

    /**
     * Saves hurricane to the hurricane's JSON file
     * @return true if hurricane is successfully saved
     */
    public static boolean saveHurricanes() {
        Hurricane hurricane = Hurricane.getInstance();
		ArrayList<Hurricane> hurricaneList = hurricane.getHurricane();

		JSONArray jsonHurricane = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< hurricaneList.size(); i++) {
            jsonHurricane.add(getHurricaneJSON(hurricaneList.get(i)));
        }
		
		//writing JSON file
        try (FileWriter file = new FileWriter(getFileWritingPath(HURRICANE_FILE_NAME, JUNIT_HURRICANE_FILE_NAME))) {
 
            file.write(jsonHurricane.toJSONString());
            return true;
 
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
	}
    //data constants
	public static JSONObject getHurricaneJSON(Hurricane hurricane) {
		JSONObject hurricaneDetails = new JSONObject();
		hurricaneDetails.put(HURRICANE_ID_KEY, hurricane.getId().toString());
        hurricaneDetails.put(HURRICANE_NAME, hurricane.getName());
		hurricaneDetails.put(HURRICANE_ZIP_CODES, hurricane.getZipCodes());
        hurricaneDetails.put(HURRICANE_STATUS, hurricane.getStatus().toString());
        hurricaneDetails.put(HURRICANE_EVACUATION_STEPS, hurricane.getEvacuationSteps());

        JSONArray suppliesJSON = new JSONArray();
        for (Resource supply : hurricane.getEmergencySupplies()) {
            JSONObject supplyJSON = new JSONObject();
            supplyJSON.put(RESOURCE_ID, supply.getResourceID());
            supplyJSON.put(RESOURCE_NAME, supply.getName());
            supplyJSON.put(RESOURCE_QUANTITY, supply.getQuantity());
            suppliesJSON.add(supplyJSON);
        }
        hurricaneDetails.put(HURRICANE_EMERGENCY_SUPPLIES, suppliesJSON);
		return hurricaneDetails;
	}

    /**
     * Helper method to format location details into a JSON object
     * @param location location to convert to JSON object
     * @return JSON object with address, city, state, and zip
     */
    private static JSONObject getLocationJSON(Location location) {
        JSONObject json = new JSONObject();
        json.put("address", location.getAddress());
        json.put("city", location.getCity());
        json.put("state", location.getState());
        json.put("zip", location.getZip());
        return json;
    }

    /**
     * Helper method to distinguish between writing to normal JSON files or writing to test JSON files 
     * @param PATH_NAME path of the normal JSON file
     * @param JUNIT_PATH_NAME path of the test JSON file
     * @return the path to write to. Returns empty string if test file isn't found
     */
    private static String getFileWritingPath(String PATH_NAME, String JUNIT_PATH_NAME) {
		try {
			if(isJUnitTest()){
				URI url = DataWriter.class.getResource(JUNIT_PATH_NAME).toURI();
				return url.getPath();
			} else {
				return PATH_NAME;
			}
		} catch(Exception e){
			System.out.println("Difficulty getting resource path");
			return "";
		}
	}

    //main method that tests if the data writer saved all instances to JSON files
    public static void main(String[] args){
		System.out.println("Saving users: " + saveUsers());
        System.out.println("Saving shelters: " + saveShelters());
        System.out.println("Saving relief requests: " + saveReliefRequests());
        System.out.println("Saving hurricanes: " + saveHurricanes());
	}
}
