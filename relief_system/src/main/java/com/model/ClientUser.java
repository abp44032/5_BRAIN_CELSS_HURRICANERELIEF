package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientUser extends User {
    private static UUID id;
    private List<ReliefRequest> reliefRequests;

    public ClientUser(String username, String password, String email) {
        super(id, username, password, email);
        this.reliefRequests = new ArrayList<>();
    }

    public ReliefRequest makeRequest(String targetShelter, String details) {
        ReliefRequest newRequest = new ReliefRequest(this, targetShelter, details);
        this.reliefRequests.add(newRequest);
        return newRequest;
        }

    public List<ReliefRequest> getReliefRequests() {
        return reliefRequests;
    }

}
