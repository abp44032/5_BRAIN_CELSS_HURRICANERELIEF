package com.model;

	
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {
	
	/**
     * Saves users to the user's JSON file
     * @return true if user is successfully saved
     */
	public static boolean saveUsers() {
		User user = User.getInstance();
		ArrayList<User> userList = user.getUsers();

		JSONArray jsonUser = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< userList.size(); i++) {
			jsonUser.add(getUserJSON(userList.get(i)));
		}
		
		//writing JSON file
        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {
 
            file.write(jsonUser.toJSONString());
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
        ReliefRequest request = ReliefRequest.getInstance();
		ArrayList<ReliefRequest> requestList = request.getRequests();

		JSONArray jsonRequest = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< requestList.size(); i++) {
			jsonRequest.add(getReliefRequestJSON(requestList.get(i)));
		}
		
		//writing JSON file
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
        requestDetails.put(REQUEST_SEVERITY, request.getSeverity());
        requestDetails.put(REQUEST_URGENCY, request.getUrgency());
		requestDetails.put(REQUEST_STATUS, request.getStatus());

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
    //main method that tests if the data writer saved all instances to JSON files
    public static void main(String[] args){
		System.out.println("Saving users: " + saveUsers());
        System.out.println("Saving shelters: " + saveShelters());
        System.out.println("Saving relief requests: " + saveReliefRequests());
        System.out.println("Saving hurricanes: " + saveHurricanes());
	}
}
