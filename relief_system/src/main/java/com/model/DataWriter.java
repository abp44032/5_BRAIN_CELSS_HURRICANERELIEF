package com.model;

	
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.cert.X509CRL;
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
        try (FileReader reader = new FileReader(USER_FILE_NAME)) {
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
                        break;
                    }
                }
            }

            if (!alreadyExists) {
                jsonUsers.add(getUserJSON(user));
            }
        }

        // Write the updated JSON array.
        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {
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
        if (user.getLocation() != null) {
            userDetails.put("location", getLocationJSON(user.getLocation()));
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
        try (FileWriter file = new FileWriter(SHELTER_FILE_NAME)) {
 
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
		shelterDetails.put(SHELTER_RESOURCES, shelter.getResources());
		shelterDetails.put(SHELTER_CAPACITY, shelter.getCapacity());
		shelterDetails.put(SHELTER_ACCOMMODATIONS, shelter.getAccommodations());

		return shelterDetails;
	}
    /**
     * Saves relief requests to the JSON file
     * @return true if request is successfully saved
     */
    public static boolean saveReliefRequests() {

		JSONArray jsonRequest = new JSONArray();
		
		//writing JSON file
        try (FileReader reader = new FileReader(REQUEST_FILE_NAME)) {
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
            ReliefRequest.getInstance().getRequests();

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
                    break;
                }
            }
        }

        if (!alreadyExists) {
            jsonRequest.add(getReliefRequestJSON(request));
        }
    }

    try (FileWriter file = new FileWriter(REQUEST_FILE_NAME)) {
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
        try (FileWriter file = new FileWriter(HURRICANE_FILE_NAME)) {
 
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
		hurricaneDetails.put(HURRICANE_ZIP_CODES, hurricane.getZipCodes());
        hurricaneDetails.put(HURRICANE_STATUS, hurricane.getStatus());
        hurricaneDetails.put(HURRICANE_EVACUATION_STEPS, hurricane.getEvacuationSteps());
        hurricaneDetails.put(HURRICANE_EMERGENCY_SUPPLIES, hurricane.getEmergencySupplies());
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

    //main method that tests if the data writer saved all instances to JSON files
    public static void main(String[] args){
		System.out.println("Saving users: " + saveUsers());
        System.out.println("Saving shelters: " + saveShelters());
        System.out.println("Saving relief requests: " + saveReliefRequests());
        System.out.println("Saving hurricanes: " + saveHurricanes());
	}
}
