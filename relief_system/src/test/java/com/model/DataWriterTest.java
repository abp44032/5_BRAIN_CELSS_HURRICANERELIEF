package com.model;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 ----------------------------------------------------------------------------------------
 * TEST                       | REASONING
 * writing zero users         | Checks that saving an empty user list works.
 * writing one user           | Checks that one user is saved successfully.
 * writing multiple users     | Checks that multiple users are saved successfully.
 * writing user with role     | Checks that a user's role is included in the saved JSON.
 * writing user with location | Checks that a user's location is included in the saved JSON
 -----------------------------------------------------------------------------------------
 */

class DataWriterTest {

    private final UserList users = UserList.getInstance();
    private final ArrayList<User> userList = users.getUsers();

    /**
     * Clears the test JSON file so each test starts with no saved users.
     */
    private void resetTestUsersFile() throws Exception {
        Path testUsersPath = Path.of(
                DataWriter.class.getResource("/data/users.json").toURI());
        Files.writeString(testUsersPath, "[]");
    }

    @BeforeEach
    void setup() throws Exception {
        userList.clear();
        resetTestUsersFile();
    }

    @AfterEach
    void tearDown() throws Exception {
        userList.clear();
        resetTestUsersFile();
    }

    /**
     * Checks that saving zero users results in an empty saved user list.
     */
    @Test
    void testWritingZeroUsers() {
        DataWriter.saveUsers();

        assertEquals(0, DataLoader.getUsers().size());
    }

    /**
     * Checks that one user is saved successfully.
     */
    @Test
    void testWritingOneUser() {
        userList.add(new User(
                "Amy", "Smith", "asmith",
                "password123", "amy@email.com", null, true));

        DataWriter.saveUsers();

        ArrayList<User> savedUsers = DataLoader.getUsers();

        assertEquals(1, savedUsers.size());
        assertEquals("asmith", savedUsers.get(0).getUsername());
    }

    /**
     * Checks that multiple users are saved successfully.
     */
    @Test
    void testWritingMultipleUsers() {
        userList.add(new User(
                "Amy", "Smith", "asmith",
                "password123", "amy@email.com", null, true));
        userList.add(new User(
                "Ben", "Smith", "bsmith",
                "password123", "ben@email.com", null, true));
        userList.add(new User(
                "Chris", "Smith", "csmith",
                "password123", "chris@email.com", null, true));
        userList.add(new User(
                "Drew", "Smith", "dsmith",
                "password123", "drew@email.com", null, true));
        userList.add(new User(
                "Evan", "Smith", "esmith",
                "password123", "evan@email.com", null, true));

        DataWriter.saveUsers();

        ArrayList<User> savedUsers = DataLoader.getUsers();

        assertEquals(5, savedUsers.size());
        assertEquals("asmith", savedUsers.get(0).getUsername());
        assertEquals("bsmith", savedUsers.get(1).getUsername());
        assertEquals("csmith", savedUsers.get(2).getUsername());
        assertEquals("dsmith", savedUsers.get(3).getUsername());
        assertEquals("esmith", savedUsers.get(4).getUsername());
    }

    /**
     * Checks that a user's role is included in the saved JSON.
     */
    @Test
    void testWritingUserWithRole() {
        userList.add(new User(
                "Amy", "Smith", "asmith",
                "password123", "amy@email.com", null, true,
                User.VOLUNTEER));

        DataWriter.saveUsers();

        assertEquals(
                User.VOLUNTEER,
                DataWriter.getUserJSON(userList.get(0)).get("role"));
    }

    /**
     * Checks that a user's location is included in the saved JSON.
     */
    @Test
    void testWritingUserWithLocation() {
        Location location = new Location(
                "123 Main Street", "Columbia", "SC", "29201");

        userList.add(new User(
                "Amy", "Smith", "asmith",
                "password123", "amy@email.com", location, true));

        DataWriter.saveUsers();

        assertTrue(
                DataWriter.getUserJSON(userList.get(0)).containsKey("location"));
    }

/**
 ----------------------------------------------------------------------------------------
 * TEST                         | REASONING
 * writing zero shelters        | Checks that saving an empty shelter list works.
 * writing one shelter          | Checks that the shelter's details are saved.
 * writing multiple shelters    | Checks that all shelters in the list are saved.
 ----------------------------------------------------------------------------------------
 */
    @Test
    public void testSaveZeroShelters() {
        Shelter.getInstance().getShelters().clear();

        assertTrue(DataWriter.saveShelters());
    }

    @Test
    public void testSaveOneShelter() {
        Shelter.getInstance().getShelters().clear();

        Shelter shelter = new Shelter(
                UUID.randomUUID(),
                "Community Shelter",
                200,
                new ArrayList<Resource>(),
                new ArrayList<AccommodationType>(),
                new Location("456 Oak Street", "Columbia", "SC", "29201"),
                "8:00 AM - 8:00 PM"
        );

        Shelter.getInstance().getShelters().add(shelter);

        assertTrue(DataWriter.saveShelters());

        assertEquals("Community Shelter",
        DataWriter.getShelterJSON(shelter).get("name"));
    }

    @Test
    public void testSaveMultipleShelters() {
        Shelter.getInstance().getShelters().clear();

        Shelter firstShelter = new Shelter(
                UUID.randomUUID(), "North Shelter", 100,
                new ArrayList<Resource>(), new ArrayList<AccommodationType>(),
                new Location("456 Oak Street", "Columbia", "SC", "29201"),
                "8:00 AM - 8:00 PM"
        );

        Shelter secondShelter = new Shelter(
                UUID.randomUUID(), "South Shelter", 150,
                new ArrayList<Resource>(), new ArrayList<AccommodationType>(),
                new Location("789 Pine Street", "Columbia", "SC", "29201"),
                "8:00 AM - 8:00 PM"
        );

        Shelter.getInstance().getShelters().add(firstShelter);
        Shelter.getInstance().getShelters().add(secondShelter);

        assertTrue(DataWriter.saveShelters());
    }

/**
 ----------------------------------------------------------------------------------------
 * TEST                         | REASONING
 * writing zero hurricanes      | Checks that saving an empty hurricane list works.
 * writing one hurricane        | Checks that the hurricane's details are saved.
 * writing multiple hurricanes  | Checks that all hurricanes in the list are saved.
 ----------------------------------------------------------------------------------------
 */
    @Test
    public void testSaveZeroHurricanes() {
        Hurricane.getInstance().getHurricane().clear();

        assertTrue(DataWriter.saveHurricanes());
    }

    @Test
    public void testSaveOneHurricane() {
        Hurricane.getInstance().getHurricane().clear();

        Hurricane hurricane = new Hurricane(
                UUID.randomUUID(),
                "Hurricane Example",
                new ArrayList<String>(),
                HurricaneStatus.ACTIVE,
                new ArrayList<String>(),
                new ArrayList<Resource>()
        );

        Hurricane.getInstance().getHurricane().add(hurricane);

        assertTrue(DataWriter.saveHurricanes());

        assertEquals("Hurricane Example",
        DataWriter.getHurricaneJSON(hurricane).get("name"));

    }

    @Test
    public void testSaveMultipleHurricanes() {
        Hurricane.getInstance().getHurricane().clear();

        Hurricane firstHurricane = new Hurricane(
                UUID.randomUUID(), "Hurricane Alpha",
                new ArrayList<String>(), HurricaneStatus.ACTIVE,
                new ArrayList<String>(), new ArrayList<Resource>()
        );

        Hurricane secondHurricane = new Hurricane(
                UUID.randomUUID(), "Hurricane Beta",
                new ArrayList<String>(), HurricaneStatus.FORMING,
                new ArrayList<String>(), new ArrayList<Resource>()
        );

        Hurricane.getInstance().getHurricane().add(firstHurricane);
        Hurricane.getInstance().getHurricane().add(secondHurricane);

        assertTrue(DataWriter.saveHurricanes());

    }

/**
 ----------------------------------------------------------------------------------------
 * TEST                              | REASONING
 * writing zero relief requests      | Checks that saving an empty request list works.
 * writing one relief request        | Checks that the request's details are saved.
 * writing multiple relief requests  | Checks that all requests in the list are saved.
 ----------------------------------------------------------------------------------------
 */
    @Test
    public void testSaveZeroReliefRequests() {
        ReliefRequestList.getInstance().getRequests().clear();

        assertTrue(DataWriter.saveReliefRequests());
    }

    @Test
    public void testSaveOneReliefRequest() {
        ReliefRequestList.getInstance().getRequests().clear();

        ReliefRequest request = new ReliefRequest(
                UUID.randomUUID(),
                RequestStatus.RECEIVED,
                RequestType.MEDICAL,
                SeverityLevel.HIGH,
                UrgencyLevel.CRITICAL,
                "Need medical assistance.",
                2,
                new ArrayList<Pet>(),
                new Location("789 Pine Street", "Columbia", "SC", "29201"),
                "555-0100"
        );

        ReliefRequestList.getInstance().getRequests().add(request);

        assertTrue(DataWriter.saveReliefRequests());

        // testSaveOneReliefRequest
        assertEquals("Need medical assistance.",
        DataWriter.getReliefRequestJSON(request).get("description"));
    }

    @Test
    public void testSaveMultipleReliefRequests() {
        ReliefRequestList.getInstance().getRequests().clear();

        ReliefRequest firstRequest = new ReliefRequest(
                UUID.randomUUID(), RequestStatus.RECEIVED,
                RequestType.MEDICAL, SeverityLevel.HIGH, UrgencyLevel.CRITICAL,
                "Need medical assistance.", 2,
                new ArrayList<Pet>(),
                new Location("789 Pine Street", "Columbia", "SC", "29201"),
                "555-0100"
        );

        ReliefRequest secondRequest = new ReliefRequest(
                UUID.randomUUID(), RequestStatus.RECEIVED,
                RequestType.TRANSPORTATION, SeverityLevel.LOW, UrgencyLevel.HIGH,
                "Need transportation.", 1,
                new ArrayList<Pet>(),
                new Location("321 Elm Street", "Columbia", "SC", "29201"),
                "555-0101"
        );

        ReliefRequestList.getInstance().getRequests().add(firstRequest);
        ReliefRequestList.getInstance().getRequests().add(secondRequest);

        assertTrue(DataWriter.saveReliefRequests());
    }
}