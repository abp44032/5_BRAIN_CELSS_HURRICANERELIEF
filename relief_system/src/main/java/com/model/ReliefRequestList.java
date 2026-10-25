package com.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;

public class ReliefRequestList {
    private static ReliefRequestList instance;
    private ArrayList<ReliefRequest> requests;

    public ReliefRequestList() {
        requests = new ArrayList<>();
    }

    public static ReliefRequestList getInstance() {
        if (instance == null) {
            instance = new ReliefRequestList();
        }

        return instance;
    }

    public ArrayList<ReliefRequest> getRequests() {
        return requests;
    }

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

    public ArrayList<ReliefRequest> getRequestsSortedByPriority() {
        ArrayList<ReliefRequest> sortedRequests = new ArrayList<>(requests);

        sortedRequests.sort(
                Comparator.comparingInt(ReliefRequest::priority).reversed());

        return sortedRequests;
    }

    public boolean addRequest(ReliefRequest request) {
        if (request == null || getRequest(request.getRequestId()) != null) {
            return false;
        }

        requests.add(request);
        return save();
    }

    public boolean removeRequest(ReliefRequest request) {
        if (requests.remove(request)) {
            return save();
        }

        return false;
    }

    public boolean save() {
        return DataWriter.saveReliefRequests();
    }
}
