package com.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;

/**
 * Singleton class that manages the list of relief requests.
 */
public class ReliefRequestList {
    private static ReliefRequestList instance;
    private ArrayList<ReliefRequest> requests;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    public ReliefRequestList() {
        requests = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the ReliefRequestList.
     * @return the singleton instance
     */
    public static ReliefRequestList getInstance() {
        if (instance == null) {
            instance = new ReliefRequestList();
        }

        return instance;
    }

    /**
     * Returns the list of all relief requests.
     * @return the list of relief requests
     */
    public ArrayList<ReliefRequest> getRequests() {
        return requests;
    }

    /**
     * Returns the relief request with the specified ID.
     * @param requestId the ID of the request to retrieve
     * @return the relief request with the specified ID, or null if not found
     */
    public ReliefRequest getRequest(UUID requestId) {
        if (requestId == null) {
            return null;
        }

        for (ReliefRequest request : requests) {
            if (request.getRequestId().equals(requestId)) {
                return request;
            }
        }

        return null;
    }

    /**
     * Returns the list of relief requests sorted by priority in descending order.
     * @return the sorted list of relief requests
     */
    public ArrayList<ReliefRequest> getRequestsSortedByPriority() {
        ArrayList<ReliefRequest> sortedRequests = new ArrayList<>(requests);

        sortedRequests.sort(
                Comparator.comparingInt(ReliefRequest::priority).reversed());

        return sortedRequests;
    }

    /**
     * Adds a new relief request to the list.
     * @param request the relief request to add
     * @return true if the request was added successfully, false otherwise
     */
    public boolean addRequest(ReliefRequest request) {
        if (request == null || getRequest(request.getRequestId()) != null) {
            return false;
        }

        requests.add(request);
        return save();
    }

    /**
     * Removes a relief request from the list.
     * @param request the relief request to remove
     * @return true if the request was removed successfully, false otherwise
     */
    public boolean removeRequest(ReliefRequest request) {
        if (requests.remove(request)) {
            return save();
        }

        return false;
    }

    /**
     * Saves the current list of relief requests.
     * @return true if the save operation was successful, false otherwise
     */
    public boolean save() {
        return DataWriter.saveReliefRequests();
    }
}
