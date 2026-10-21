package com.model;

public class DataConstants {
    /**
     * File Names
     */
    public static final String USER_FILE_NAME = "JSON/users.json";
    public static final String SHELTER_FILE_NAME = "JSON/shelters.json";
    public static final String REQUEST_FILE_NAME = "JSON/reliefRequests.json";
    public static final String HURRICANE_FILE_NAME = "JSON/hurricanes.json";

    /**
     * User constants
     */
    public static final String USER_ID_KEY = "uuid";
    public static final String USER_FIRSTNAME = "firstName";
    public static final String USER_LASTNAME = "lastName";
    public static final String USER_EMAIL = "email";
    public static final String USER_USERNAME = "userName";
    public static final String USER_PASSWORD_KEY = "password";
    public static final String USER_SHARE_LOCATION = "shareLocation";

    /**
     * Shelter Constants
    */
    public static final String SHELTER_ID_KEY = "uuid";
    public static final String SHELTER_NAME = "name";
    public static final String SHELTER_CAPACITY = "capacity";
    public static final String SHELTER_RESOURCES = "resources";
    public static final String SHELTER_ACCOMMODATIONS = "accommodations";
    public static final String SHELTER_LOCATION = "location";
    public static final String SHELTER_HOURS_OF_OPERATION = "hours of operation";

    
    /**
     * Resource constants
     */
    public static final String RESOURCE_ID = "resourceID";
    public static final String RESOURCE_NAME = "name";
    public static final String RESOURCE_QUANTITY = "quantity";


    /**
     * Relief Request constants
     */
    public static final String REQUEST_ID_KEY = "requestID";
    public static final String REQUEST_DESCRIPTION = "description";
    public static final String REQUEST_STATUS = "status";
    public static final String REQUEST_TYPE = "requestType";
    public static final String REQUEST_SEVERITY = "severity";
    public static final String REQUEST_URGENCY = "urgency";
    public static final String REQUEST_TOTAL_PEOPLE = "totalPeople";

    /**
     * Hurricane constants
     */
    public static final String HURRICANE_ID_KEY = "uuid";
    public static final String HURRICANE_NAME = "name";
    public static final String HURRICANE_ZIP_CODES = "hurricaneZipCodes";
    public static final String HURRICANE_STATUS = "hurricaneStatus";
    public static final String HURRICANE_EVACUATION_STEPS = "evacuationSteps";
    public static final String HURRICANE_EMERGENCY_SUPPLIES = "emergencySupplies";

}