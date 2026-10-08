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

    public UUID getRequestId() {
        return requestId;
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
        return "ReliefRequest{" +
                "requestId=" + requestId +
                ", status=" + status +
                ", type=" + type +
                ", severity=" + severity +
                ", urgency=" + urgency +
                ", description='" + description + '\'' +
                ", totalPeople=" + totalPeople +
                ", pets=" + pets +
                '}';
    }

    public boolean isResolved() {
        return false;
    }

    public int getTotalPeople() {
        return totalPeople;
    }

    public ArrayList<Pet> getPets() {
        return pets;
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

        System.out.println(request.getDescription());
    }
}

