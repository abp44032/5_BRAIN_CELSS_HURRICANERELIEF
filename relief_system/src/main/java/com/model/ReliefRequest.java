package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a relief request in the system.
 */
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

    /**
     * Constructs a new ReliefRequest with the specified details.
     * @param requestId the unique ID of the request
     * @param status the status of the request
     * @param type the type of the request
     * @param severity the severity level of the request
     * @param urgency the urgency level of the request
     * @param description the description of the request
     * @param totalPeople the total number of people involved
     * @param pets the list of pets involved
     * @param location the location of the request
     * @param contactInformation the contact information for the request
     */
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

    /**
     * Returns the unique ID of the relief request.
     * @return the unique ID of the relief request
     */
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


    /**
     * Returns the detailed information of the relief request.
     * @return the detailed information of the relief request
     */
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

    /**
     * Returns the details of the pets associated with the relief request.
     * @return the details of the pets
     */
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

    /**
     * Sets the contact information for the relief request.
     * @param contactInformation the contact information to set
     */
    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    /**
     * Returns the list of comments associated with the relief request.
     * @return the list of comments
     */
    public ArrayList<String> getComments() {
        return comments;
    }

    /**
     * Adds a comment to the relief request.
     * @param comment the comment to add
     */
    public void addComment(String comment) {
        if (comment != null && !comment.isBlank()) {
            comments.add(comment);
        }
    }

    /**
     * Updates the status of the relief request.
     * @param newStatus the new status to set
     */
    public void updateStatus(RequestStatus newStatus) {
        if (newStatus != null) {
            status = newStatus;
        }
    }

    /**
     * Deletes the relief request by setting its status to CANCELLED.
     * @return true if the request was successfully deleted
     */
    public boolean delete() {
        status = RequestStatus.CANCELLED;
        return true;
    }

    /**
     * Checks if the relief request is resolved.
     * A request is considered resolved if its status is COMPLETED or CANCELLED.
     * @return true if the request is resolved, false otherwise
     */
    public boolean isResolved() {
        return status == RequestStatus.COMPLETED
                || status == RequestStatus.CANCELLED;
    }

    /**
     * Calculates the priority of the relief request based on its severity and urgency.
     * @return the priority value
     */
    public int priority() {
        return severity.ordinal() + urgency.ordinal();
    }
}
