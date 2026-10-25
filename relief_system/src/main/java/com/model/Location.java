package com.model;

import com.location.Address;
import com.location.Coordinates;

/** Stores an address and uses the provided map package to find coordinates. */
public class Location {
    private String address;
    private String city;
    private String state;
    private String zip;
    public Location() { }

    public Location(String address, String city, String state, String zip) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    public String getAddress() { 
        return address; 
    }

    public void setAddress(String address) { 
        this.address = address; 
    }

    public String getCity() {
         return city;
    }

    public void setCity(String city) { 
        this.city = city; 
    }

    public String getState() { 
        return state; 
    }

    public void setState(String state) { 
        this.state = state; 
    }

    public String getZip() { 
        return zip; 
    }

    public void setZip(String zip) { 
        this.zip = zip; 
    }

   
    @SuppressWarnings("exports")
    public Coordinates getCoordinates() {
        Address mapAddress = new Address(address, city, state, zip);
        return mapAddress.getCoordinates();
    }

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

    @Override
    public String toString() {
        Address mapAddress = new Address(address, city, state, zip);
        Coordinates coordinates = getCoordinates();
        return mapAddress + " | Coordinates: " + coordinates;
    }
}
