package com.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class ReliefRequest<timestamp> {

    private UUID requestId;
    private static LocalDateTime timestamp;
    private RequestStatus status;
    private RequestType type;
    private SeverityLevel severity;
    private UrgencyLevel urgency;
    private String description;
    private int totalPeople;
    private ArrayList<Pet> pets;
    private ArrayList<Pet> pets2;

    public ReliefRequest(UUID requestId, LocalDateTime timestamp, RequestStatus status, RequestType type, SeverityLevel severity, UrgencyLevel urgency, String description, int totalPeople, ArrayList<Pet> pets) {
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

    public UUID getRequestId() {
        return requestId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
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
                ", timestamp=" + timestamp +
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
            LocalDateTime.now(),
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

