package com.model;

import java.util.UUID;

public class ReliefRequest {
    public enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }

    private UUID requestId;
    private User requester;
    private String targetShelter;
    private String details;
    private Status status;

    public ReliefRequest(User requester, String targetShelter, String details) {
        this.requestId = UUID.randomUUID();
        this.requester = requester;
        this.targetShelter = targetShelter;
        this.details = details;
        this.status = Status.PENDING;
    }

    public UUID getrequestId() {
        return requestId;
    }

    public User getRequester() {
        return requester;
    }

    public String getTargetShelter() {
        return targetShelter;
    }

    public String getDetails() {
        return details;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
