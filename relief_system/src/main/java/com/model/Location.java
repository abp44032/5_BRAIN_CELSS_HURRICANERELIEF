package com.model;

import com.location.Address;
import com.location.Coordinates;

/** Stores an address and uses the provided map package to find coordinates. */
public class Location {
    private String address;
    private String city;
    private String state;
    private String zip;

    /**
     * Constructs an empty Location object.
     */
    public Location() { }

    /**
     * Constructs a Location object with the specified address, city, state, and zip code.
     * @param address the street address
     * @param city the city
     * @param state the state
     * @param zip the zip code
     */
    public Location(String address, String city, String state, String zip) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    /**
     * Returns the street address of the location.
     * @return the street address
     */
    public String getAddress() { 
        return address; 
    }

    /**
     * Sets the street address of the location.
     * @param address the street address
     */
    public void setAddress(String address) { 
        this.address = address; 
    }

    /**
     * Returns the city of the location.
     * @return the city
     */
    public String getCity() {
         return city;
    }

    /**
     * Sets the city of the location.
     * @param city the city
     */
    public void setCity(String city) { 
        this.city = city; 
    }

    /**
     * Returns the state of the location.
     * @return the state
     */
    public String getState() { 
        return state; 
    }

    /**
     * Sets the state of the location.
     * @param state the state
     */
    public void setState(String state) { 
        this.state = state; 
    }

    /**
     * Returns the zip code of the location.
     * @return the zip code
     */
    public String getZip() { 
        return zip; 
    }

    /**
     * Sets the zip code of the location.
     * @param zip the zip code
     */
    public void setZip(String zip) { 
        this.zip = zip; 
    }

    /**
     * Returns the coordinates of the location using the provided map package.
     * @return the coordinates of the location
     */
    @SuppressWarnings("exports")
    public Coordinates getCoordinates() {
        Address mapAddress = new Address(address, city, state, zip);
        return mapAddress.getCoordinates();
    }

    /**
     * Calculates the distance from this location to another location.
     * @param other the other location
     * @return the distance between the two locations, or -1 if the distance cannot be calculated
     */
    public double getDistanceFrom(Location other) {
        if (other == null) return -1;

        Coordinates thisCoordinates = getCoordinates();
        Coordinates otherCoordinates = other.getCoordinates();
        if (thisCoordinates == null || otherCoordinates == null) return -1;

        double latitudeDifference = thisCoordinates.getLatitude() - otherCoordinates.getLatitude();
        double longitudeDifference = thisCoordinates.getLongitude() - otherCoordinates.getLongitude();
        return Math.sqrt(latitudeDifference * latitudeDifference
                + longitudeDifference * longitudeDifference);
    }

    /**
     * Returns a string representation of the location, including the address and coordinates.
     * @return a string representation of the location
     */
    @Override
    public String toString() {
        Address mapAddress = new Address(address, city, state, zip);
        Coordinates coordinates = getCoordinates();
        return mapAddress + " | Coordinates: " + coordinates;
    }
}
