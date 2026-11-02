package com.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** RELIEF REQUEST TESTS
 -------------------------------------------------------------------------------------
 * TEST                           | REASONING
 * null pets in constructor       | checks that a null pet list becomes an empty list
 * new request has no comments    | checks that a new request starts with no comments
 * details include fields         | checks that the main fields appear in the details
 * details list pets              | checks that each pet's name and type appear
 * details without pets           | checks that a request with no pets says "None"
 * adding a comment               | checks that a comment is added to the list
 * adding a null comment          | checks that a null comment is ignored
 * adding a blank comment         | checks that an empty or spaces-only comment is ignored
 * updating the status            | checks that the status changes to the new value
 * updating status to null        | checks that a null status leaves the status unchanged
 * deleting a request             | checks that delete sets CANCELLED and returns true
 * completed is resolved          | checks that a COMPLETED request is resolved
 * cancelled is resolved          | checks that a CANCELLED request is resolved
 * received is not resolved       | checks that a RECEIVED request is not resolved
 * lowest priority                | checks that LOW and LOW give a priority of 0
 * highest priority               | checks that CRITICAL and CRITICAL give a priority of 4
 * mixed priority                 | checks that severity and urgency are added together
 * priority with null severity    | checks that a missing severity throws an exception
 -------------------------------------------------------------------------------------
 */
class ReliefRequestTest {

    private ReliefRequest request;

    /*
    * builds a sample relief request before each test
    */
    @BeforeEach
    void setUp() {
        request = new ReliefRequest(
                UUID.fromString("33333333-3333-3333-3333-333333333333"),
                RequestStatus.RECEIVED, RequestType.MEDICAL,
                SeverityLevel.HIGH, UrgencyLevel.CRITICAL,
                "Need medical assistance.", 2, new ArrayList<Pet>(),
                new Location("789 Pine Street", "Columbia", "SC", "29201"),
                "555-0100");
    }

    /*
    * null pets in constructor
    */
    @Test
    void testNullPetsBecomesEmptyList() {
        ReliefRequest noPets = new ReliefRequest(
                UUID.randomUUID(), RequestStatus.RECEIVED, RequestType.MEDICAL,
                SeverityLevel.HIGH, UrgencyLevel.CRITICAL, "Need help.", 1,
                null, null, "555-0100");

        assertNotNull(noPets.getPets());
        assertTrue(noPets.getPets().isEmpty());
    }

    /*
    * new request has no comments
    */
    @Test
    void testNewRequestHasNoComments() {
        assertTrue(request.getComments().isEmpty());
    }

    /*
    * details include fields
    */
    @Test
    void testGetDetailsIncludesFields() {
        String details = request.getDetails();

        assertTrue(details.contains("requestId = 33333333-3333-3333-3333-333333333333"));
        assertTrue(details.contains("Status = RECEIVED"));
        assertTrue(details.contains("Type = MEDICAL"));
        assertTrue(details.contains("Severity = HIGH"));
        assertTrue(details.contains("Urgency = CRITICAL"));
        assertTrue(details.contains("Description = Need medical assistance."));
        assertTrue(details.contains("TotalPeople = 2"));
    }

    /*
    * details list pets
    */
    @Test
    void testGetDetailsListsPets() {
        request.getPets().add(new Pet("Buddy", "Dog"));
        request.getPets().add(new Pet("Whiskers", "Cat"));

        String details = request.getDetails();

        assertTrue(details.contains("Buddy (Dog)"));
        assertTrue(details.contains("Whiskers (Cat)"));
    }

    /*
    * details without pets
    */
    @Test
    void testGetDetailsWithoutPets() {
        assertTrue(request.getDetails().contains("Pets: None"));
    }

    /*
    * adding a comment
    */
    @Test
    void testAddingComment() {
        request.addComment("rdelgado: On my way.");

        assertEquals(1, request.getComments().size());
        assertEquals("rdelgado: On my way.", request.getComments().get(0));
    }

    /*
    * adding a null comment
    */
    @Test
    void testAddingNullCommentIsIgnored() {
        request.addComment(null);

        assertTrue(request.getComments().isEmpty());
    }

    /*
    * adding a blank comment
    */
    @Test
    void testAddingBlankCommentIsIgnored() {
        request.addComment("");
        request.addComment("   ");

        assertTrue(request.getComments().isEmpty());
    }

    /*
    * updating the status
    */
    @Test
    void testUpdatingStatus() {
        request.updateStatus(RequestStatus.ACCEPTED);

        assertEquals(RequestStatus.ACCEPTED, request.getStatus());
    }

    /*
    * updating status to null
    */
    @Test
    void testUpdatingStatusToNullKeepsStatus() {
        request.updateStatus(null);

        assertEquals(RequestStatus.RECEIVED, request.getStatus());
    }

    /*
    * deleting a request
    */
    @Test
    void testDeleteSetsCancelledAndReturnsTrue() {
        assertTrue(request.delete());
        assertEquals(RequestStatus.CANCELLED, request.getStatus());
    }

    /*
    * completed is resolved
    */
    @Test
    void testCompletedRequestIsResolved() {
        request.updateStatus(RequestStatus.COMPLETED);

        assertTrue(request.isResolved());
    }

    /*
    * cancelled is resolved
    */
    @Test
    void testCancelledRequestIsResolved() {
        request.updateStatus(RequestStatus.CANCELLED);

        assertTrue(request.isResolved());
    }

    /*
    * received is not resolved
    */
    @Test
    void testReceivedRequestIsNotResolved() {
        assertFalse(request.isResolved());
    }

    /*
    * lowest priority
    */
    @Test
    void testLowestPriority() {
        ReliefRequest low = new ReliefRequest(
                UUID.randomUUID(), RequestStatus.RECEIVED, RequestType.MEDICAL,
                SeverityLevel.LOW, UrgencyLevel.LOW, "Need help.", 1,
                null, null, "555-0100");

        assertEquals(0, low.priority());
    }

    /*
    * highest priority
    */
    @Test
    void testHighestPriority() {
        ReliefRequest critical = new ReliefRequest(
                UUID.randomUUID(), RequestStatus.RECEIVED, RequestType.MEDICAL,
                SeverityLevel.CRITICAL, UrgencyLevel.CRITICAL, "Need help.", 1,
                null, null, "555-0100");

        assertEquals(4, critical.priority());
    }

    /*
    * mixed priority
    */
    @Test
    void testPriorityAddsSeverityAndUrgency() {
        // setUp request: HIGH severity (1) + CRITICAL urgency (2)
        assertEquals(3, request.priority());
    }

    /*
    * priority with null severity
    */
    @Test
    void testPriorityWithNullSeverityThrowsException() {
        ReliefRequest noSeverity = new ReliefRequest(
                UUID.randomUUID(), RequestStatus.RECEIVED, RequestType.MEDICAL,
                null, UrgencyLevel.CRITICAL, "Need help.", 1,
                null, null, "555-0100");

        assertThrows(NullPointerException.class, () -> noSeverity.priority());
    }
}