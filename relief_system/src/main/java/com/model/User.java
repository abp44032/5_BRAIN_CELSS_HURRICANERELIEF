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
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean checkPassword(String password) {
        return false;
    }
}
