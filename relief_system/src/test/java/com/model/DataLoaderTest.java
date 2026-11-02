package com.model;
 
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataLoaderTest {
    
    private static final String USERS_FILE = DataConstants.JUNIT_USER_FILE_NAME;
    private static final String SHELTERS_FILE = DataConstants.JUNIT_SHELTER_FILE_NAME;
    private static final String REQUESTS_FILE = DataConstants.JUNIT_REQUEST_FILE_NAME;
    private static final String HURRICANES_FILE = DataConstants.JUNIT_HURRICANE_FILE_NAME;
    private final Map<String, String> originalContents = new HashMap<>();

    /**
     * Saves the current contents of each test JSON file so it can be restored
     */
    @BeforeEach 
    void saveOriginalTestFiles() throws Exception {
        for (String file : List.of(USERS_FILE, SHELTERS_FILE, REQUESTS_FILE, HURRICANES_FILE)) {
            originalContents.put(file, Files.readString(Path.of(DataLoader.class.getResource(file).toURI())));
        }
    }

    /**
     * Restores each test JSON file to what it contained before the test
     */
    @AfterEach 
    void restoreOriginalTestFiles() throws Exception {
        for (Map.Entry<String, String> entry : originalContents.entrySet()) {
            Files.writeString(Path.of(DataLoader.class.getResource(entry.getKey()).toURI()), entry.getValue());
        }
    }

    /** USER TESTS
     -------------------------------------------------------------------------------------------------------------------------------------------------------
     * TEST                                     | REASONING
     * loading zero users                       | checks that an empty JSON array loads as no users
     * loading one user's details               | checks that every user field is read from the JSON
     * loading a user's location                | checks that the nested location object is loaded
     * loading user without optional fields     | chekcs that missing role, specialty, vehicle, and location load as null and shareLocation as false
     * loading multiple users                   | checks that all users load in file order
     * loading bad JSON file                    | checks that bad JSON loads as an empty list
     * loading user with invalid ID             | checks that a bad UUID doesn't load the user
     * loading skips invalid user               | checks that one bad entry doesn't stop the valid users from loading
     -------------------------------------------------------------------------------------------------------------------------------------------------------
     */

    /*
    * loading zero users 
    */
    @Test 
    void testLoadingZeroUsers() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(USERS_FILE).toURI()), "[]");
        assertEquals(0, DataLoader.getUsers().size());
    }

    /*
    * loading one user's details
    */
    @Test
    void testLoadingOneUserDetails() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(USERS_FILE).toURI()),
                ("[{'uuid':'11111111-1111-1111-1111-111111111111','userName':'rdelgado',"
                + "'firstName':'Rosa','lastName':'Delgado','email':'rosa@example.com',"
                + "'password':'pass123','shareLocation':true,'role':'VOLUNTEER',"
                + "'specialty':'TRANSPORTATION','vehicle':'Ford F-350'}]").replace('\'', '"'));
 
        ArrayList<User> users = DataLoader.getUsers();
 
        assertEquals(1, users.size());
        User user = users.get(0);
        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), user.getId());
        assertEquals("rdelgado", user.getUsername());
        assertEquals("Rosa", user.getFirstName());
        assertEquals("Delgado", user.getLastName());
        assertEquals("rosa@example.com", user.getEmail());
        assertEquals("pass123", user.getPassword());
        assertEquals(User.VOLUNTEER, user.getRole());
        assertEquals("TRANSPORTATION", user.getSpecialty());
        assertEquals("Ford F-350", user.getVehicle());
        assertTrue(user.isShareLocation());
    }

    /*
    * loading a user's location
    */
    @Test
    void testLoadingUserLocation() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(USERS_FILE).toURI()),
                ("[{'uuid':'11111111-1111-1111-1111-111111111111','userName':'rdelgado',"
                + "'location':{'address':'300 Forest Ave','city':'Charleston',"
                + "'state':'SC','zip':'29401'}}]").replace('\'', '"'));
 
        Location location = DataLoader.getUsers().get(0).getLocation();
 
        assertEquals("300 Forest Ave", location.getAddress());
        assertEquals("Charleston", location.getCity());
        assertEquals("SC", location.getState());
        assertEquals("29401", location.getZip());
    }

    /*
    * loading user without optional fields
    */
    @Test
    void testLoadingUserWithoutOptionalFields() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(USERS_FILE).toURI()),
                "[{'uuid':'11111111-1111-1111-1111-111111111111','userName':'tcarter'}]"
                .replace('\'', '"'));
 
        User user = DataLoader.getUsers().get(0);
 
        assertNull(user.getRole());
        assertNull(user.getSpecialty());
        assertNull(user.getVehicle());
        assertNull(user.getLocation());
        assertFalse(user.isShareLocation());
    }

    /*
    * loading multiple users
    */
    @Test
    void testLoadingMultipleUsers() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(USERS_FILE).toURI()),
                ("[{'uuid':'11111111-1111-1111-1111-111111111111','userName':'rdelgado'},"
                + "{'uuid':'55555555-5555-5555-5555-555555555555','userName':'mjohnson'}]")
                .replace('\'', '"'));
 
        ArrayList<User> users = DataLoader.getUsers();
 
        assertEquals(2, users.size());
        assertEquals("rdelgado", users.get(0).getUsername());
        assertEquals("mjohnson", users.get(1).getUsername());
    }

    /*
    * loading bad JSON file
    */
    @Test
    void testLoadingUsersFromBadJson() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(USERS_FILE).toURI()),
                "this is not json");
 
        assertEquals(0, DataLoader.getUsers().size());
    }

    /*
    * loading user with invalid ID
    */
    @Test
    void testLoadingUserWithInvalidId() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(USERS_FILE).toURI()),
                "[{'uuid':'not-a-uuid','userName':'tcarter'}]".replace('\'', '"'));
 
        assertEquals(0, DataLoader.getUsers().size());
    }

    /*
    * loading skips invalid user
    */
    @Test
    void testLoadingUsersSkipsInvalidUser() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(USERS_FILE).toURI()),
                ("[{'uuid':'11111111-1111-1111-1111-111111111111','userName':'rdelgado'},"
                + "{'uuid':'not-a-uuid','userName':'tcarter'},"
                + "{'uuid':'77777777-7777-7777-7777-777777777777','userName':'mjohnson'}]")
                .replace('\'', '"'));
 
        assertEquals(2, DataLoader.getUsers().size());
    }

    /** SHELTER TESTS
     -------------------------------------------------------------------------------------------------------------------------------------------------------
     *  TEST                            | REASONING
     * loading zero shelters            | checks that an empty JSON array loads as no shelters
     * loading one shelter's details    | checks that the shelter's ID, name, capacity, and location are read from the JSON
     * loading shelter resources        | checks that each resource's ID, name, and quantity are loaded
     * loading shelter accommodations   | checks that accommodation names become enum values
     * loading hours from open/close    | checks that open and close are joined as "open - close"
     * loading hours from a string      | checks that hours stored as plain text are kept
     * loading shelter occupancy        | checks that saved occupancy, occupants, and pets are restored
     * loading multiple shelters        | checks that all shelters load in file order
     * loading invalid accommodation    | checks that an unknown accommodation doesn't load
     * loading bad JSON                 | checks that bad JSON loads as an empty list
     -------------------------------------------------------------------------------------------------------------------------------------------------------
     */
    
    /*
    * loading zero shelters
    */
    @Test
    void testLoadingZeroShelters() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(SHELTERS_FILE).toURI()), "[]");
 
        assertEquals(0, DataLoader.getShelters().size());
    }
 
    /*
    * loading one shelter's details
    */
    @Test
    void testLoadingOneShelterDetails() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(SHELTERS_FILE).toURI()),
                ("[{'uuid':'22222222-2222-2222-2222-222222222222',"
                + "'name':'Charleston Family Relief Shelter','capacity':120,"
                + "'resources':[],'accommodations':[],"
                + "'location':{'address':'100 Harbor View Dr','city':'Charleston',"
                + "'state':'SC','zip':'29401'}}]").replace('\'', '"'));
 
        ArrayList<Shelter> shelters = DataLoader.getShelters();
 
        assertEquals(1, shelters.size());
        Shelter shelter = shelters.get(0);
        assertEquals(UUID.fromString("22222222-2222-2222-2222-222222222222"), shelter.getId());
        assertEquals("Charleston Family Relief Shelter", shelter.getName());
        assertEquals(120, shelter.getCapacity());
        assertEquals("100 Harbor View Dr", shelter.getLocation().getAddress());
        assertEquals("Charleston", shelter.getLocation().getCity());
    }
 
    /*
    * loading shelter resources
    */
    @Test
    void testLoadingShelterResources() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(SHELTERS_FILE).toURI()),
                ("[{'uuid':'22222222-2222-2222-2222-222222222222','name':'Shelter',"
                + "'capacity':120,'accommodations':[],"
                + "'resources':[{'resourceID':321,'name':'Bottled Water','quantity':150},"
                + "{'resourceID':322,'name':'First Aid Kits','quantity':50}]}]")
                .replace('\'', '"'));
 
        ArrayList<Resource> resources = DataLoader.getShelters().get(0).getResources();
 
        assertEquals(2, resources.size());
        assertEquals(321, resources.get(0).getResourceID());
        assertEquals("Bottled Water", resources.get(0).getName());
        assertEquals(150, resources.get(0).getQuantity());
    }
 
    /*
    * loading shelter accommodations
    */
    @Test
    void testLoadingShelterAccommodations() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(SHELTERS_FILE).toURI()),
                ("[{'uuid':'22222222-2222-2222-2222-222222222222','name':'Shelter',"
                + "'capacity':120,'resources':[],"
                + "'accommodations':['PET_FRIENDLY','MEDICAL_NEEDS']}]").replace('\'', '"'));
 
        assertEquals(
                List.of(AccommodationType.PET_FRIENDLY, AccommodationType.MEDICAL_NEEDS),
                DataLoader.getShelters().get(0).getAccommodations());
    }
 
    /*
    * loading shelter hours from open/close
    */
    @Test
    void testLoadingShelterHoursFromOpenAndClose() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(SHELTERS_FILE).toURI()),
                ("[{'uuid':'22222222-2222-2222-2222-222222222222','name':'Shelter',"
                + "'capacity':120,'resources':[],'accommodations':[],"
                + "'hours':{'open':'08:00','close':'20:00'}}]").replace('\'', '"'));
 
        assertEquals("08:00 - 20:00", DataLoader.getShelters().get(0).getHoursOfOperation());
    }
 
    /*
    * loading shelter hours from a string
    */
    @Test
    void testLoadingShelterHoursFromString() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(SHELTERS_FILE).toURI()),
                ("[{'uuid':'22222222-2222-2222-2222-222222222222','name':'Shelter',"
                + "'capacity':120,'resources':[],'accommodations':[],"
                + "'hours':'8:00 AM - 8:00 PM'}]").replace('\'', '"'));
 
        assertEquals("8:00 AM - 8:00 PM", DataLoader.getShelters().get(0).getHoursOfOperation());
    }
 
    /*
    * loading shelter occupancy
    */
    @Test
    void testLoadingShelterOccupancy() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(SHELTERS_FILE).toURI()),
                ("[{'uuid':'22222222-2222-2222-2222-222222222222','name':'Shelter',"
                + "'capacity':120,'resources':[],'accommodations':[],"
                + "'currentOccupancy':5,'occupants':['Tom Carter','Anna Carter'],"
                + "'accompanyingPets':['Buddy (Dog)']}]").replace('\'', '"'));
 
        Shelter shelter = DataLoader.getShelters().get(0);
 
        assertEquals(5, shelter.getCurrentOccupancy());
        assertEquals(List.of("Tom Carter", "Anna Carter"), shelter.getOccupants());
        assertEquals(List.of("Buddy (Dog)"), shelter.getAccompanyingPets());
    }
 
    /*
    * loading multiple shelters
    */
    @Test
    void testLoadingMultipleShelters() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(SHELTERS_FILE).toURI()),
                ("[{'uuid':'22222222-2222-2222-2222-222222222222',"
                + "'name':'Charleston Family Relief Shelter','capacity':120,"
                + "'resources':[],'accommodations':[]},"
                + "{'uuid':'88888888-8888-8888-8888-888888888888',"
                + "'name':'Columbia Community Shelter','capacity':100,"
                + "'resources':[],'accommodations':[]}]").replace('\'', '"'));
 
        ArrayList<Shelter> shelters = DataLoader.getShelters();
 
        assertEquals(2, shelters.size());
        assertEquals("Charleston Family Relief Shelter", shelters.get(0).getName());
        assertEquals("Columbia Community Shelter", shelters.get(1).getName());
    }
 
    /*
    * loading invalid accommodation
    */
    @Test
    void testLoadingShelterWithInvalidAccommodation() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(SHELTERS_FILE).toURI()),
                ("[{'uuid':'22222222-2222-2222-2222-222222222222','name':'Shelter',"
                + "'capacity':120,'resources':[],"
                + "'accommodations':['NOT_A_REAL_TYPE']}]").replace('\'', '"'));
 
        assertEquals(0, DataLoader.getShelters().size());
    }
 
    /*
    * loading bad JSON
    */
    @Test
    void testLoadingSheltersFromBadJson() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(SHELTERS_FILE).toURI()),
                "this is not json");
 
        assertEquals(0, DataLoader.getShelters().size());
    }

    /** RELIEF REQUEST TESTS
     -------------------------------------------------------------------------------------------------------------------------------------------------------
     * TEST                             | REASONING
     * loading zero relief requests     | checks that an empty JSON array loads as no requests
     * loading one request's details    | checks that the ID, status, type, severity, urgency, description, people, and contact info are loaded
     * loading request location         | checks that the nested location object is loaded
     * loading request pets             | checks that each pet's name and type are loaded
     * loading request assignment       | checks that acceptedBy and the assigned shelter ID are loaded
     * loading without optional fields  | checks that missing contact, pets, location, and assignment load as defaults (empty or null)
     * loading multiple requests        | checks that all requests load in file order
     * loading invalid status           | checks that an unknown status doesn't load
     * loading bad JSON                 | checks that bad JSON loads as an empty list
     -------------------------------------------------------------------------------------------------------------------------------------------------------
     */

    /*
    * loading zero relief requests
    */
    @Test
    void testLoadingZeroReliefRequests() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(REQUESTS_FILE).toURI()), "[]");
 
        assertEquals(0, DataLoader.getReliefRequests().size());
    }
 
    /*
    * loading one request's details
    */
    @Test
    void testLoadingOneReliefRequestDetails() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(REQUESTS_FILE).toURI()),
                ("[{'requestID':'33333333-3333-3333-3333-333333333333','status':'RECEIVED',"
                + "'requestType':'MEDICAL','severity':'HIGH','urgency':'CRITICAL',"
                + "'description':'Daughter has asthma','totalPeople':3,"
                + "'contactInformation':'843-555-0100'}]").replace('\'', '"'));
 
        ArrayList<ReliefRequest> requests = DataLoader.getReliefRequests();
 
        assertEquals(1, requests.size());
        ReliefRequest request = requests.get(0);
        assertEquals(UUID.fromString("33333333-3333-3333-3333-333333333333"), request.getId());
        assertEquals(RequestStatus.RECEIVED, request.getStatus());
        assertEquals(RequestType.MEDICAL, request.getType());
        assertEquals(SeverityLevel.HIGH, request.getSeverity());
        assertEquals(UrgencyLevel.CRITICAL, request.getUrgency());
        assertEquals("Daughter has asthma", request.getDescription());
        assertEquals(3, request.getTotalPeople());
        assertEquals("843-555-0100", request.getContactInformation());
    }
 
    /*
    * loading request locaiton
    */
    @Test
    void testLoadingReliefRequestLocation() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(REQUESTS_FILE).toURI()),
                ("[{'requestID':'33333333-3333-3333-3333-333333333333','status':'RECEIVED',"
                + "'requestType':'MEDICAL','severity':'HIGH','urgency':'CRITICAL',"
                + "'totalPeople':3,'location':{'address':'12 Meeting St',"
                + "'city':'Charleston','state':'SC','zip':'29401'}}]").replace('\'', '"'));
 
        Location location = DataLoader.getReliefRequests().get(0).getLocation();
 
        assertEquals("12 Meeting St", location.getAddress());
        assertEquals("Charleston", location.getCity());
        assertEquals("SC", location.getState());
        assertEquals("29401", location.getZip());
    }
 
    /*
    * loading request pets
    */
    @Test
    void testLoadingReliefRequestPets() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(REQUESTS_FILE).toURI()),
                ("[{'requestID':'33333333-3333-3333-3333-333333333333','status':'RECEIVED',"
                + "'requestType':'MEDICAL','severity':'HIGH','urgency':'CRITICAL',"
                + "'totalPeople':3,'pets':[{'name':'Buddy','type':'Dog'}]}]")
                .replace('\'', '"'));
 
        ArrayList<Pet> pets = DataLoader.getReliefRequests().get(0).getPets();
 
        assertEquals(1, pets.size());
        assertEquals("Buddy", pets.get(0).getName());
        assertEquals("Dog", pets.get(0).getType());
    }
 
    /*
    * loading request assignment
    */
    @Test
    void testLoadingReliefRequestAssignment() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(REQUESTS_FILE).toURI()),
                ("[{'requestID':'33333333-3333-3333-3333-333333333333','status':'ASSIGNED',"
                + "'requestType':'MEDICAL','severity':'HIGH','urgency':'CRITICAL',"
                + "'totalPeople':3,'acceptedBy':'Rosa Delgado',"
                + "'assignedShelterId':'22222222-2222-2222-2222-222222222222'}]")
                .replace('\'', '"'));
 
        ReliefRequest request = DataLoader.getReliefRequests().get(0);
 
        assertEquals("Rosa Delgado", request.getAcceptedBy());
        assertEquals(UUID.fromString("22222222-2222-2222-2222-222222222222"),
                request.getAssignedShelterId());
    }
 
    /*
    * loading without optional fields
    */
    @Test
    void testLoadingReliefRequestWithoutOptionalFields() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(REQUESTS_FILE).toURI()),
                ("[{'requestID':'33333333-3333-3333-3333-333333333333','status':'RECEIVED',"
                + "'requestType':'MEDICAL','severity':'HIGH','urgency':'CRITICAL',"
                + "'totalPeople':1}]").replace('\'', '"'));
 
        ReliefRequest request = DataLoader.getReliefRequests().get(0);
 
        assertEquals("", request.getContactInformation());
        assertTrue(request.getPets().isEmpty());
        assertNull(request.getLocation());
        assertNull(request.getAcceptedBy());
        assertNull(request.getAssignedShelterId());
    }
 
    /*
    * loading multiple requests
    */
    @Test
    void testLoadingMultipleReliefRequests() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(REQUESTS_FILE).toURI()),
                ("[{'requestID':'33333333-3333-3333-3333-333333333333','status':'RECEIVED',"
                + "'requestType':'MEDICAL','severity':'HIGH','urgency':'CRITICAL',"
                + "'description':'Daughter has asthma','totalPeople':3},"
                + "{'requestID':'99999999-9999-9999-9999-999999999999','status':'RECEIVED',"
                + "'requestType':'TRANSPORTATION','severity':'LOW','urgency':'HIGH',"
                + "'description':'Need a ride','totalPeople':1}]").replace('\'', '"'));
 
        ArrayList<ReliefRequest> requests = DataLoader.getReliefRequests();
 
        assertEquals(2, requests.size());
        assertEquals("Daughter has asthma", requests.get(0).getDescription());
        assertEquals("Need a ride", requests.get(1).getDescription());
    }
 
    /*
    * loading invalid status
    */
    @Test
    void testLoadingReliefRequestWithInvalidStatus() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(REQUESTS_FILE).toURI()),
                ("[{'requestID':'33333333-3333-3333-3333-333333333333','status':'NOT_A_STATUS',"
                + "'requestType':'MEDICAL','severity':'HIGH','urgency':'CRITICAL',"
                + "'totalPeople':3}]").replace('\'', '"'));
 
        assertEquals(0, DataLoader.getReliefRequests().size());
    }
 
    /*
    * loading bad JSON
    */
    @Test
    void testLoadingReliefRequestsFromBadJson() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(REQUESTS_FILE).toURI()),
                "this is not json");
 
        assertEquals(0, DataLoader.getReliefRequests().size());
    }
   
    /** HURRICANE TESTS
     -------------------------------------------------------------------------------------------------------------------------------------------------------
     * TEST                             | REASONING
     * loading zero hurricanes          | checks that an empty JSON array loads as no hurricanes
     * loading one hurricane's details  | checks that the ID, name, status, zip codes, and evacuation steps are loaded
     * loading hurricane supplies       | checks that each supply's ID, name, and quantity are loaded
     * loading without optional arrays  | checks that missing zip codes, steps, and supplies load as empty lists
     * loading multiple hurricanes      | checks that all hurricanes load in file order
     * loading invalid status           | checks that an unknown status doesn't load
     * loading bad JSON                 | checks that bad JSON loads as an empty list
     -------------------------------------------------------------------------------------------------------------------------------------------------------
     */

    /*
    * loading zero hurricanes
    */
    @Test
    void testLoadingZeroHurricanes() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(HURRICANES_FILE).toURI()), "[]");
 
        assertEquals(0, DataLoader.getHurricanes().size());
    }
 
    /*
    * loading one hurricane's details
    */
    @Test
    void testLoadingOneHurricaneDetails() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(HURRICANES_FILE).toURI()),
                ("[{'uuid':'44444444-4444-4444-4444-444444444444','name':'Scarlett',"
                + "'hurricaneStatus':'ACTIVE','hurricaneZipCodes':['29401','29403'],"
                + "'evacuationSteps':['Pack supplies','Leave early']}]").replace('\'', '"'));
 
        ArrayList<Hurricane> hurricanes = DataLoader.getHurricanes();
 
        assertEquals(1, hurricanes.size());
        Hurricane hurricane = hurricanes.get(0);
        assertEquals(UUID.fromString("44444444-4444-4444-4444-444444444444"), hurricane.getId());
        assertEquals("Scarlett", hurricane.getName());
        assertEquals(HurricaneStatus.ACTIVE, hurricane.getStatus());
        assertEquals(List.of("29401", "29403"), hurricane.getZipCodes());
        assertEquals(List.of("Pack supplies", "Leave early"), hurricane.getEvacuationSteps());
    }
 
    /*
    * loading hurricane supplies
    */
    @Test
    void testLoadingHurricaneSupplies() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(HURRICANES_FILE).toURI()),
                ("[{'uuid':'44444444-4444-4444-4444-444444444444','hurricaneStatus':'ACTIVE',"
                + "'emergencySupplies':[{'resourceID':301,'name':'Bottled Water',"
                + "'quantity':100}]}]").replace('\'', '"'));
 
        ArrayList<Resource> supplies = DataLoader.getHurricanes().get(0).getEmergencySupplies();
 
        assertEquals(1, supplies.size());
        assertEquals(301, supplies.get(0).getResourceID());
        assertEquals("Bottled Water", supplies.get(0).getName());
        assertEquals(100, supplies.get(0).getQuantity());
    }
    
    /*
    * loading without optional arrays
    */
    @Test
    void testLoadingHurricaneWithoutOptionalArrays() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(HURRICANES_FILE).toURI()),
                "[{'uuid':'44444444-4444-4444-4444-444444444444','hurricaneStatus':'WEAKENING'}]"
                .replace('\'', '"'));
 
        Hurricane hurricane = DataLoader.getHurricanes().get(0);
 
        assertTrue(hurricane.getZipCodes().isEmpty());
        assertTrue(hurricane.getEvacuationSteps().isEmpty());
        assertTrue(hurricane.getEmergencySupplies().isEmpty());
    }
 
    /*
    * loading multiple hurricanes
    */
    @Test
    void testLoadingMultipleHurricanes() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(HURRICANES_FILE).toURI()),
                ("[{'uuid':'44444444-4444-4444-4444-444444444444','name':'Scarlett',"
                + "'hurricaneStatus':'ACTIVE'},"
                + "{'uuid':'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa','name':'Tracy',"
                + "'hurricaneStatus':'FORMING'}]").replace('\'', '"'));
 
        ArrayList<Hurricane> hurricanes = DataLoader.getHurricanes();
 
        assertEquals(2, hurricanes.size());
        assertEquals("Scarlett", hurricanes.get(0).getName());
        assertEquals("Tracy", hurricanes.get(1).getName());
    }
 
    /*
    * loading invalid status
    */
    @Test
    void testLoadingHurricaneWithInvalidStatus() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(HURRICANES_FILE).toURI()),
                "[{'uuid':'44444444-4444-4444-4444-444444444444','hurricaneStatus':'NOT_A_STATUS'}]"
                .replace('\'', '"'));
 
        assertEquals(0, DataLoader.getHurricanes().size());
    }
 
    /*
    * loading bad JSON
    */
    @Test
    void testLoadingHurricanesFromBadJson() throws Exception {
        Files.writeString(Path.of(DataLoader.class.getResource(HURRICANES_FILE).toURI()),
                "this is not json");
 
        assertEquals(0, DataLoader.getHurricanes().size());
    }
}
