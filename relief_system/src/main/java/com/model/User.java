package com.model;

import java.util.UUID;

public abstract class User {
    protected UUID id;
    protected String username;
    protected String password;
    protected String email;

    public User(UUID id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean checkPassword(String password) {
        return this.password != null && this.password.equals(password);
    }
}
