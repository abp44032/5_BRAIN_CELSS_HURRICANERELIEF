package com.model;

import java.util.UUID;

public class ReliefRequest {

    private UUID requestId;
    private DateTime timestamp;
    private RequestStatus status;
    private RequestType type;
    private SeverityLevel severity;
    private UrgencyLevel urgency;
    private String description;
    private int totalPeople;
    private ArrayList<Pets> pets;

    public ReliefRequest(UUID requestId, DateTime timestamp, RequestStatus status, RequestType type, SeverityLevel severity, UrgencyLevel urgency, String description, int totalPeople, ArrayList<Pets> pets) {
        this.requestId = requestId;
        this.timestamp = timestamp;
        this.status = status;
        this.type = type;
        this.severity = severity;
        this.urgency = urgency;
        this.description = description;
        this.totalPeople = totalPeople;
        this.pets = pets;
    }

    public int priority() {
        return 0;
    }

    public boolean delete() {
        return false;
    }

    public void updateStatus(RequestStatus newStatus) {
    }

    public void notifyUser(String message) {
    }

    public String getDetails() {
        return null;
    }

    public void assignVolunteer(Volunteer volunteer) {
    }

    public boolean isResolved() {
        return false;
    }
}
