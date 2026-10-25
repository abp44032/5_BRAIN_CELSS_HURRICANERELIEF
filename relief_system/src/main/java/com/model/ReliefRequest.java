package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class ReliefRequest {

    private UUID requestId;
    private RequestStatus status;
    private RequestType type;
    private SeverityLevel severity;
    private UrgencyLevel urgency;
    private String description;
    private Location location;
    private int totalPeople;
    private ArrayList<Pet> pets;
    private ArrayList<String> comments;

    public ReliefRequest(UUID requestId, RequestStatus status,
            RequestType type, SeverityLevel severity,
            UrgencyLevel urgency, Location location,
            String description, int totalPeople,
            ArrayList<Pet> pets) {

        this.requestId = requestId;
        this.status = status;
        this.type = type;
        this.severity = severity;
        this.urgency = urgency;
        this.location = location;
        this.description = description;
        this.totalPeople = totalPeople;
        this.pets = pets;
        this.comments = new ArrayList<>();
    }

    public UUID getId() {
        return requestId;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public RequestType getType() {
        return type;
    }

    public SeverityLevel getSeverity() {
        return severity;
    }

    public UrgencyLevel getUrgency() {
        return urgency;
    }

    public Location getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public int getTotalPeople() {
        return totalPeople;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public void updateStatus(RequestStatus newStatus) {
        status = newStatus;
    }

    public boolean delete() {
        status = RequestStatus.CANCELLED;
        return true;
    }

    public boolean isResolved() {
        return status == RequestStatus.COMPLETED
                || status == RequestStatus.CANCELLED;
    }

    public void notifyUser(String message) {
        System.out.println("Notification to user: " + message);
    }

    public String getDetails() {
        return "Relief Request\n"
                + "ID: " + requestId + "\n"
                + "Status: " + status + "\n"
                + "Type: " + type + "\n"
                + "Severity: " + severity + "\n"
                + "Urgency: " + urgency + "\n"
                + "Location: " + location + "\n"
                + "Description: " + description + "\n"
                + "People: " + totalPeople + "\n"
                + "Pets: " + pets + "\n"
                + "Comments: " + comments;
    }
}
