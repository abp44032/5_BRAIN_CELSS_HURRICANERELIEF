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
    private int totalPeople;
    private ArrayList<Pet> pets;

    private static ReliefRequest instance;
    private ArrayList<ReliefRequest> requests = new ArrayList<>();

    public ReliefRequest(UUID requestId, RequestStatus status, RequestType type, SeverityLevel severity, UrgencyLevel urgency, String description, int totalPeople, ArrayList<Pet> pets) {
        this.requestId = requestId;
        this.status = status;
        this.type = type;
        this.severity = severity;
        this.urgency = urgency;
        this.description = description;
        this.totalPeople = totalPeople;
        this.pets = pets;
    }

    public static ReliefRequest getInstance() {
    if (instance == null) {
        instance = new ReliefRequest(UUID.randomUUID(),
            RequestStatus.ACCEPTED,
            RequestType.MEDICAL,
            SeverityLevel.HIGH,
            UrgencyLevel.CRITICAL,
            "",
            0,
            new ArrayList<Pet>());
    }
    return instance;
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

    public int getTotalPeople() {
        return totalPeople;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public ArrayList<ReliefRequest> getRequests() {
        return requests;
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
        return "ReliefRequest{\n" +
                "requestId=" + requestId + "\n" +
                "Status=" + status + "\n" +
                "Type=" + type + "\n" +
                "Severity=" + severity + "\n" +
                "Urgency=" + urgency + "\n" +
                "Description='" + description + "\n" +
                "TotalPeople=" + totalPeople + "\n" +
                "Pets=" + pets + "\n" +
                '}';
    }

    public boolean isResolved() {
        return false;
    }

    public static void main(String[] args) {
        ReliefRequest request = new ReliefRequest(
            UUID.randomUUID(),
            RequestStatus.ACCEPTED,   
            RequestType.MEDICAL,      
            SeverityLevel.HIGH,        
            UrgencyLevel.CRITICAL,     
            "Needs emergency bottled water and non-perishables",
            4,
            new ArrayList<Pet>()
        );

        System.out.println(request.getDetails());
    }
}
