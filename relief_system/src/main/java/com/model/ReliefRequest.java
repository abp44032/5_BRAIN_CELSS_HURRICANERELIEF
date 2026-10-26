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
    private String contactInformation;
    private String acceptedBy;

    public ReliefRequest(UUID requestId, RequestStatus status,
            RequestType type, SeverityLevel severity,
            UrgencyLevel urgency, String description,
            int totalPeople, ArrayList<Pet> pets,
            Location location, String contactInformation) {

        this.requestId = requestId;
        this.status = status;
        this.type = type;
        this.severity = severity;
        this.urgency = urgency;
        this.description = description;
        this.totalPeople = totalPeople;
        this.pets = pets == null ? new ArrayList<>() : pets;
        this.location = location;
        this.contactInformation = contactInformation;
        this.comments = new ArrayList<>();
    }

    public String getAcceptedBy() {
    return acceptedBy;
    }

    public void setAcceptedBy(String acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    public UUID getRequestId() {
        return requestId;
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

    public String getDescription() {
        return description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getTotalPeople() {
        return totalPeople;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void addRequest(ReliefRequest request) {
        if (request != null && !requests.contains(request)) {
            requests.add(request);
        }
    }

    public boolean delete() {
        return false;
    }

    public void updateStatus(RequestStatus newStatus) {
        this.status = newStatus;
    }

    public void notifyUser(String message) {
        System.out.println("Notification to user: " + message);
    }

    public String getDetails() {
        return "ReliefRequest\n" +
                "requestId = " + requestId + "\n" +
                "Status = " + status + "\n" +
                "Type = " + type + "\n" +
                "Severity = " + severity + "\n" +
                "Urgency = " + urgency + "\n" +
                "Description = " + description + "\n" +
                "TotalPeople = " + totalPeople + "\n" +
                "Pets: " + getPetsDetails() + "\n";
                
    }

    private String getPetsDetails() {
        String petDetails = "";

        if (pets == null || pets.isEmpty()) {
            return "None\n";
        }

        for (Pet pet : pets) {
            petDetails += " " + pet.getName()
                + " (" + pet.getType() + ")\n";
        }

        return petDetails;

    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void addComment(String comment) {
        if (comment != null && !comment.isBlank()) {
            comments.add(comment);
        }
    }

    public void updateStatus(RequestStatus newStatus) {
        if (newStatus != null) {
            status = newStatus;
        }
    }

    public boolean delete() {
        status = RequestStatus.CANCELLED;
        return true;
    }

    public boolean isResolved() {
        return status == RequestStatus.COMPLETED
                || status == RequestStatus.CANCELLED;
    }

    public int priority() {
        return severity.ordinal() + urgency.ordinal();
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
                + "Contact Information: " + contactInformation + "\n"
                + "Comments: " + comments + "\n";
    }
}
