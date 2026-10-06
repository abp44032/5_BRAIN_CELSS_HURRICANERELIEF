package com.model;

public class AdminUser extends User {
    private String organization;
    public AdminUser(String username, String password, String email, String organization) {
        super(username, password, email);
        this.organization = organization;
    }

    public String getOrganization() {
        return organization;
    }

    public void processRequest(ReliefRequest request, ReliefRequest.Status newStatus) {
        request.setStatus(newStatus);
    }
    
}
