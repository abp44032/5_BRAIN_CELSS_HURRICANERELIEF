package com.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** RELIEF REQUEST LIST TESTS
 -------------------------------------------------------------------------------------
 * TEST                              | REASONING
 * singleton returns same instance   | checks that getInstance always returns the same list
 * adding a request                  | checks that a valid request is added and true is returned
 * adding a null request             | checks that a null request is rejected
 * adding a duplicate request        | checks that a request with an existing ID is rejected
 * adding saves to file              | checks that an added request can be loaded back from the file
 * getting a request by ID           | checks that the matching request is returned
 * getting a missing request         | checks that an unknown ID returns null
 * getting a request with null ID    | checks that a null ID returns null instead of crashing
 * sorted by priority                | checks that the highest priority request comes first
 * sorting an empty list             | checks that sorting an empty list returns an empty list
 * sorting keeps original order      | checks that sorting returns a copy and does not reorder the list
 * removing a request                | checks that an existing request is removed and true is returned
 * removing a missing request        | checks that removing a request not in the list returns false
 * removing a null request           | checks that removing null returns false
 * removing saves to file            | checks that a removed request is also gone from the file
 * new list loads saved requests     | checks that a new list starts with the requests in the file
 -------------------------------------------------------------------------------------
 */
class ReliefRequestListTest {

    private static final String REQUESTS_FILE = DataConstants.JUNIT_REQUEST_FILE_NAME;

    private String originalFileContents;
    private ReliefRequestList list;
    private ReliefRequest request;

    /*
    * clears the singleton list and resets the JSON. Builds the same sample request as ReliefRequestTest
    */
    @BeforeEach
    void setUp() throws Exception {
        Path file = Path.of(DataLoader.class.getResource(REQUESTS_FILE).toURI());
        originalFileContents = Files.readString(file);
        Files.writeString(file, "[]");

        list = ReliefRequestList.getInstance();
        list.getRequests().clear();

        request = new ReliefRequest(
                UUID.fromString("33333333-3333-3333-3333-333333333333"),
                RequestStatus.RECEIVED, RequestType.MEDICAL,
                SeverityLevel.HIGH, UrgencyLevel.CRITICAL,
                "Need medical assistance.", 2, new ArrayList<Pet>(),
                new Location("789 Pine Street", "Columbia", "SC", "29201"),
                "555-0100");
    }

    /*
    * clears the list and restores the original JSON file contents
    */
    @AfterEach
    void tearDown() throws Exception {
        list.getRequests().clear();
        Path file = Path.of(DataLoader.class.getResource(REQUESTS_FILE).toURI());
        Files.writeString(file, originalFileContents);
    }

    /*
    * singleton returns same instance
    */
    @Test
    void testSingletonReturnsSameInstance() {
        assertSame(ReliefRequestList.getInstance(), ReliefRequestList.getInstance());
    }

    /*
    * adding a request
    */
    @Test
    void testAddingRequest() {
        assertTrue(list.addRequest(request));
        assertEquals(1, list.getRequests().size());
    }

    /*
    * adding a null request
    */
    @Test
    void testAddingNullRequest() {
        assertFalse(list.addRequest(null));
        assertEquals(0, list.getRequests().size());
    }

    /*
    * adding a duplicate request
    */
    @Test
    void testAddingDuplicateRequest() {
        list.addRequest(request);

        assertFalse(list.addRequest(request));
        assertEquals(1, list.getRequests().size());
    }

    /*
    * adding saves to file
    */
    @Test
    void testAddingSavesToFile() {
        list.addRequest(request);

        ArrayList<ReliefRequest> loaded = DataLoader.getReliefRequests();

        assertEquals(1, loaded.size());
        assertEquals(request.getRequestId(), loaded.get(0).getRequestId());
    }

    /*
    * getting a request by ID
    */
    @Test
    void testGettingRequestById() {
        list.addRequest(request);

        assertSame(request, list.getRequest(
                UUID.fromString("33333333-3333-3333-3333-333333333333")));
    }

    /*
    * getting a missing request
    */
    @Test
    void testGettingMissingRequest() {
        list.addRequest(request);

        assertNull(list.getRequest(UUID.randomUUID()));
    }

    /*
    * getting a request with null ID
    */
    @Test
    void testGettingRequestWithNullId() {
        list.addRequest(request);

        assertNull(list.getRequest(null));
    }

    /*
    * sorted by priority
    */
    @Test
    void testSortedByPriority() {
        ReliefRequest low = new ReliefRequest(
                UUID.randomUUID(), RequestStatus.RECEIVED, RequestType.MEDICAL,
                SeverityLevel.LOW, UrgencyLevel.LOW, "Low.", 1,
                null, null, "555-0101");
        ReliefRequest critical = new ReliefRequest(
                UUID.randomUUID(), RequestStatus.RECEIVED, RequestType.MEDICAL,
                SeverityLevel.CRITICAL, UrgencyLevel.CRITICAL, "Critical.", 1,
                null, null, "555-0102");
        list.addRequest(low);
        list.addRequest(request);
        list.addRequest(critical);

        ArrayList<ReliefRequest> sorted = list.getRequestsSortedByPriority();

        // priorities: critical = 4, request = 3, low = 0
        assertSame(critical, sorted.get(0));
        assertSame(request, sorted.get(1));
        assertSame(low, sorted.get(2));
    }

    /*
    * sorting an empty list
    */
    @Test
    void testSortingEmptyList() {
        assertTrue(list.getRequestsSortedByPriority().isEmpty());
    }

    /*
    * sorting keeps original order
    */
    @Test
    void testSortingKeepsOriginalOrder() {
        ReliefRequest low = new ReliefRequest(
                UUID.randomUUID(), RequestStatus.RECEIVED, RequestType.MEDICAL,
                SeverityLevel.LOW, UrgencyLevel.LOW, "Low.", 1,
                null, null, "555-0101");
        list.addRequest(low);
        list.addRequest(request);

        list.getRequestsSortedByPriority();

        assertSame(low, list.getRequests().get(0));
        assertSame(request, list.getRequests().get(1));
    }

    /*
    * removing a request
    */
    @Test
    void testRemovingRequest() {
        list.addRequest(request);

        assertTrue(list.removeRequest(request));
        assertEquals(0, list.getRequests().size());
    }

    /*
    * removing a missing request
    */
    @Test
    void testRemovingMissingRequest() {
        assertFalse(list.removeRequest(request));
    }

    /*
    * removing a null request
    */
    @Test
    void testRemovingNullRequest() {
        assertFalse(list.removeRequest(null));
    }

    /*
    * removing saves to file
    */
    @Test
    void testRemovingSavesToFile() {
        list.addRequest(request);

        list.removeRequest(request);

        assertEquals(0, DataLoader.getReliefRequests().size());
    }

    /*
    * new list loads saved requests
    */
    @Test
    void testNewListLoadsSavedRequests() throws Exception {
        Path file = Path.of(DataLoader.class.getResource(REQUESTS_FILE).toURI());
        Files.writeString(file, ("[{'requestID':'44444444-4444-4444-4444-444444444444',"
                + "'status':'RECEIVED','requestType':'MEDICAL','severity':'HIGH',"
                + "'urgency':'HIGH','description':'Saved request.','totalPeople':1,"
                + "'contactInformation':'555-0103'}]").replace('\'', '"'));

        ReliefRequestList newList = new ReliefRequestList();

        assertEquals(1, newList.getRequests().size());
    }
}