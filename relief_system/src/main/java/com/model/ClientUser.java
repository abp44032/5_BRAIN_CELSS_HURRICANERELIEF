package com.model;

import java.util.ArrayList;
import java.util.List;

public class ClientUser extends User {
    private List<ReliefRequest> reliefRequests;

    public ClientUser(String username, String password, String email) {
        super(username, password, email);
        this.reliefRequests = new ArrayList<>();
    }

    public ReliefRequest makeRequest(String targetShelter, String details) {
        ReliefRequest newRequest = new ReliefRequest(this, targetShelter, details);
        this.requests.add(newRequest);
        return newRequest;
        }

    public List<ReliefRequest> getReliefRequests() {
        return requests;
    }

}
