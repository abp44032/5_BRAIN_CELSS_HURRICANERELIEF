package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientUser extends User {
    private static UUID id;
    private List<ReliefRequest> reliefRequests;

    public ClientUser(String username, String password, String email) {
        super(id, username, password, email);
    }

    public ReliefRequest makeRequest(String targetShelter, String details) {
        return null;
    }

    public List<ReliefRequest> getReliefRequests() {
        return null;
    }

}
