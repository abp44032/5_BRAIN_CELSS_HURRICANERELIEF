package com.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.Test;

/**
 * TEST                                               | REASONING
 * ---------------------------------------------------|---------------------------
 * loginWithIncorrectPasswordReturnsFalse             |checks for failed login when incorrect password is entered
 * loginWithCorrectCredentialsReturnsTrue             |checks for successfull login with valid login credentials
 * loginWithNonexistentUsernameReturnsFalse           |checks for failed login when the username doesn't exist
 * logoutClearsCurrentUser                            |checks for cleared current user when logging out
 * getCurrentUserBeforeLoginReturnsNull               |checks for no current user before logging in a user
 * createAccountWithExistingUsernameReturnsNull       |checks for no duplicate usernames
 * acceptRequestWithNullRequestReturnsFalse           |checks for accept requests with no requests or volunteers
 * acceptRequestWithNullVolunteerReturnsFalse         |checks for volunteer when accepting a releif request
 * assignShelterWithNullArgumentsReturnsFalse         |checks for assign shelter with no request or shelter
 * acceptRequestWithAlreadyAcceptedStatusReturnsFalse |checks that a relief request already accepted cannot be accepted twice
 * assignShelterWithReceivedRequestReturnsFalse       |checks that a received reuest cannot be assigned a shelter without being accepted
 * assignShelterWithNullShelterReturnsFalse           |checks that no request can be assigned without a shelter
 * assignShelterWithNullRequestReturnsFalse           |checks for no assigned shelter when there is no requests
 * submitRequestWithNullRequestPrintsError            |checks for when submitting a null request outputs an error message
 * getSheltersReturnsShelterList                      |checks that the app returns a non-null shelter
 * acceptRequestWithNonVolunteerReturnsFalse          |checks that no other user role besides volunteer can accept a request
 * assignShelterWithCancelledRequestReturnsFalse      |checks canceled releif request is not assigned to a shelter
 * loginSetsCurrentUser                               |checks that current user is set when logged in
 */
public class EmergencyReliefApplicationTest {

    /**
     * Tests that login fails when an incorrect password is provided.
     */
    @Test
    public void loginWithIncorrectPasswordReturnsFalse() {
        EmergencyReliefApplication app = new EmergencyReliefApplication();

        boolean result = app.login("thomas.cooper", "wrongpassword");

        assertFalse(result);
    }
    

    /**
     * Tests that login succeeds with valid credentials.
     */
    @Test
    public void loginWithCorrectCredentialsReturnsTrue() {
        EmergencyReliefApplication app = new EmergencyReliefApplication();

        boolean result = app.login("thomas.cooper", "12345");

        assertTrue(result);
    }

    /**
     * Tests that login fails when the username does not exist.
     */
    @Test
    public void loginWithNonexistentUsernameReturnsFalse() {
        EmergencyReliefApplication app = new EmergencyReliefApplication();

        boolean result = app.login("nonexistentuser12345", "12345");

        assertFalse(result);
    }

    /**
     * Tests that logging out clears the currently logged-in user.
     */
    @Test
    public void logoutClearsCurrentUser() {
        EmergencyReliefApplication app = new EmergencyReliefApplication();

        app.login("thomas.cooper", "12345");
        app.logout();

        assertNull(app.getCurrentUser());
    }

    /**
     * Tests that there is no current user before logging in.
     */
    @Test
    public void getCurrentUserBeforeLoginReturnsNull() {
        EmergencyReliefApplication app = new EmergencyReliefApplication();

        assertNull(app.getCurrentUser());
    }

    /**
     * Tests that an account cannot be created with an existing username.
     */
    @Test
    public void createAccountWithExistingUsernameReturnsNull() {
        EmergencyReliefApplication app = new EmergencyReliefApplication();

        User result = app.createAccount(
            "Thomas",
            "Cooper",
            "thomas.cooper",
            "12345",
            "thomas.cooper@example.com"
        );

        assertNull(result);
    }

    /**
     * Tests that accepting a request fails when both arguments are null.
     */
    @Test
    public void acceptRequestWithNullRequestReturnsFalse() {
        EmergencyReliefApplication app = new EmergencyReliefApplication();

        boolean result = app.acceptRequest(null, null);

        assertFalse(result);
    }

    /**
     * Tests that a relief request cannot be accepted without a volunteer.
     */
    @Test
    public void acceptRequestWithNullVolunteerReturnsFalse() {
        EmergencyReliefApplication app = new EmergencyReliefApplication();

        ReliefRequest request = new ReliefRequest(
            UUID.randomUUID(),
            RequestStatus.RECEIVED,
            RequestType.TRANSPORTATION,
            SeverityLevel.HIGH,
            UrgencyLevel.HIGH,
            "Family needs transportation",
            3,
            new ArrayList<Pet>(),
            null,
            "test@example.com"
        );

        boolean result = app.acceptRequest(request, null);

        assertFalse(result);
    }

    /**
     * Tests that assigning a shelter fails when both arguments are null.
     */
    @Test
    public void assignShelterWithNullArgumentsReturnsFalse() {
        EmergencyReliefApplication app = new EmergencyReliefApplication();

        boolean result = app.assignShelter(null, null);

        assertFalse(result);
    }

    /**
     * Tests that an already accepted relief request cannot be accepted again.
     */
    @Test
    public void acceptRequestWithAlreadyAcceptedStatusReturnsFalse() {
        EmergencyReliefApplication app = new EmergencyReliefApplication();

        ReliefRequest request = new ReliefRequest(
            UUID.randomUUID(),
            RequestStatus.ACCEPTED,
            RequestType.TRANSPORTATION,
            SeverityLevel.HIGH,
            UrgencyLevel.HIGH,
            "Family needs transportation",
            3,
            new ArrayList<Pet>(),
            null,
            "test@example.com"
        );

        User volunteer = UserList.getInstance().getUser("rosa.delgado");

        assertFalse(app.acceptRequest(request, volunteer));
    }

    /**
     * Tests that a received request cannot be assigned to a shelter before acceptance.
     */
    @Test
    public void assignShelterWithReceivedRequestReturnsFalse() {
        EmergencyReliefApplication app = new EmergencyReliefApplication();

        ReliefRequest request = new ReliefRequest(
            UUID.randomUUID(),
            RequestStatus.RECEIVED,
            RequestType.TRANSPORTATION,
            SeverityLevel.HIGH,
            UrgencyLevel.HIGH,
            "Family needs transportation",
            3,
            new ArrayList<Pet>(),
            null,
            "test@example.com"
        );

        Shelter shelter = new Shelter(
            UUID.randomUUID(),
            "Test Shelter",
            10,
            new ArrayList<Resource>(),
            new ArrayList<AccommodationType>(),
            null,
            "8:00 AM - 8:00 PM"
        );

        assertFalse(app.assignShelter(request, shelter));
    }

    /**
     * Tests that a request cannot be assigned without a shelter.
     */
    @Test
    public void assignShelterWithNullShelterReturnsFalse() {
        EmergencyReliefApplication app = new EmergencyReliefApplication();

        ReliefRequest request = new ReliefRequest(
            UUID.randomUUID(),
            RequestStatus.ACCEPTED,
            RequestType.TRANSPORTATION,
            SeverityLevel.HIGH,
            UrgencyLevel.HIGH,
            "Family needs transportation",
            3,
            new ArrayList<Pet>(),
            null,
            "test@example.com"
        );

        assertFalse(app.assignShelter(request, null));
    }

    /**
     * Tests that a shelter cannot be assigned when no request is provided.
     */
    @Test
    public void assignShelterWithNullRequestReturnsFalse() {
        EmergencyReliefApplication app = new EmergencyReliefApplication();

        Shelter shelter = new Shelter(
            UUID.randomUUID(),
            "Test Shelter",
            10,
            new ArrayList<Resource>(),
            new ArrayList<AccommodationType>(),
            null,
            "8:00 AM - 8:00 PM"
        );

        assertFalse(app.assignShelter(null, shelter));
    }

    /**
     * Tests that submitting a null request displays an error message.
     */
    @Test
    public void submitRequestWithNullRequestPrintsError() {
        EmergencyReliefApplication app = new EmergencyReliefApplication();

        java.io.ByteArrayOutputStream output = new java.io.ByteArrayOutputStream();

        java.io.PrintStream originalOut = System.out;

        try {
            System.setOut(new java.io.PrintStream(output));

            app.submitRequest(null);

            assertTrue(output.toString().contains(
                "Cannot submit an empty relief request."
            ));
        } finally {
            System.setOut(originalOut);
        }

    }

    /**
     * Tests that the application returns a non-null shelter list.
     */
    @Test
    public void getSheltersReturnsShelterList() {
        EmergencyReliefApplication app = new EmergencyReliefApplication();

        ArrayList<Shelter> shelters = app.getShelters();

        assertNotNull(shelters);
    }

    /**
     * Tests that a user without the volunteer role cannot accept a request.
     */
    @Test
    public void acceptRequestWithNonVolunteerReturnsFalse() {
        EmergencyReliefApplication app = new EmergencyReliefApplication();

        ReliefRequest request = new ReliefRequest(
            UUID.randomUUID(),
            RequestStatus.RECEIVED,
            RequestType.TRANSPORTATION,
            SeverityLevel.HIGH,
            UrgencyLevel.HIGH,
            "Family needs transportation",
            3,
            new ArrayList<Pet>(),
            null,
            "test@example.com"
        );

        User resident = UserList.getInstance().getUser("thomas.cooper");

        assertNotNull(resident);
        assertFalse(app.acceptRequest(request, resident));
    }

    /**
     * Tests that a cancelled relief request cannot be assigned to a shelter.
     */
    @Test
    public void assignShelterWithCancelledRequestReturnsFalse() {
        EmergencyReliefApplication app = new EmergencyReliefApplication();

        ReliefRequest request = new ReliefRequest(
            UUID.randomUUID(),
            RequestStatus.CANCELLED,
            RequestType.TRANSPORTATION,
            SeverityLevel.HIGH,
            UrgencyLevel.HIGH,
            "Cancelled transportation request",
            3,
            new ArrayList<Pet>(),
            null,
            "test@example.com"
        );

        Shelter shelter = new Shelter(
            UUID.randomUUID(),
            "Test Shelter",
            10,
            new ArrayList<Resource>(),
            new ArrayList<AccommodationType>(),
            null,
            "8:00 AM - 8:00 PM"
        );

        assertFalse(app.assignShelter(request, shelter));
    }

    /**
     * Tests that a successful login sets the current user.
     */
    @Test
    public void loginSetsCurrentUser() {
        EmergencyReliefApplication app = new EmergencyReliefApplication();

        boolean loggedIn = app.login("thomas.cooper", "12345");

        assertTrue(loggedIn);
        assertNotNull(app.getCurrentUser());
        assertEquals("thomas.cooper", app.getCurrentUser().getUsername());
    }
}

