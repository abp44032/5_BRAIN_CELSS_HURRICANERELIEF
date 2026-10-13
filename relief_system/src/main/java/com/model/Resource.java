package com.model;

public class Resource {
    private int resourceID;
    private String name;
    private int quantity;

    public Resource(int resourceID, String name, int quantity) {
        this.resourceID = resourceID;
        this.name = name;
        this.quantity = quantity;
    }   

    public int getResourceID() {
        return this.resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void updateQuantity(int newQty) {
        if (newQty >= 0) {
            this.quantity = newQty;
        }
    }

    @Override 
    public String toString() {
        return name + " (id: " + resourceID + ", Quantity: " + quantity + ")";
    }

}
