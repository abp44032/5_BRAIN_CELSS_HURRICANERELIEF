package com.model;

public class ShelterFacade {
    private UserList userList;
    private User currentUser;

    public ShelterFacade() {
        this.userList = UserList.getInstance();
        this.currentUser = null;
    }

    public boolean login(String username, String password) {
        User user = userList.getUserByUsername(username);
        if (user != null && user.checkPassword(password)) {
            this.currentUser = user;
            return true;
        }
        return false;
    }

    public void logout() {
        this.currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public ReliefRequest submitRequest(String targetShelter, String details) {
        if (currentUser instanceof ClientUser) {
            return ((ClientUser) currentUser).makeRequest(targetShelter, details);
        }
        throw new IllegalStateException("Only logged-in clients can submit requests.");
    }
}
