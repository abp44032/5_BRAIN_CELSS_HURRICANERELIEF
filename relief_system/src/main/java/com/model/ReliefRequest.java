package com.model;

import java.time.LocalDateTime;
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
    private ArrayList<Pet> pets2;

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

    public int priority() {
        return 0;
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
