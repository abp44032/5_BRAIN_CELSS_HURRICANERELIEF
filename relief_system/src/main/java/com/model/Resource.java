package com.model;

/**
 * represents a resource available in the relief system.
 */
public class Resource {
    private int resourceID;
    private String name;
    private int quantity;

    /**
     * Default constructor and parameterized constructor for the Resource class.
     */
    public Resource() {}
    /**
     * creates a new Resource instance with the specified ID, name, and quantity.
     * @param resourceID the unique identifier for the resource
     * @param name the name of the resource
     * @param quantity the quantity of the resource
     */
    public Resource(int resourceID, String name, int quantity) {
        this.resourceID = resourceID;
        this.name = name;
        this.quantity = quantity;
    }   

    /**
     * returns the unique identifier of the resource.
     * @return the resource ID
     */
    public int getResourceID() {
        return this.resourceID;
    }

    /**
     * sets the unique identifier of the resource.
     * @param resourceID the resource ID to set
     */
    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    /**
     * returns the name of the resource.
     * @return the resource name
     */
    public String getName() {
        return this.name;
    }

    /**
     * sets the name of the resource.
     * @param name the resource name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns the quantity of the resource.
     * @return the resource quantity
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * sets the quantity of the resource.
     * @param quantity the resource quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * updates the quantity of the resource if the new quantity is non-negative.
     * @param newQty the new quantity to set
     */
    public void updateQuantity(int newQty) {
        if (newQty >= 0) {
            this.quantity = newQty;
        }
    }

    /**
     * returns a readable string representation of the resource.
     * @return the string representation of the resource
     */
    @Override
    public String toString() { 
        return name + " x" + quantity; 
    }
}



