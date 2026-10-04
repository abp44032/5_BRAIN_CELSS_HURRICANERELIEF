package com.hurricane;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID:


public class Shelter {
    private UUID id:
    private String name;
    private int capacity;   
    private ArrayList<Resource> resources;
    private ArrayList<AccommodationType> accommodations;
    private Location location;
    private String hoursOfOperation;


    public Shelter(UUID id, String name, int capacity, ArrayList<Resource> resources, ArrayList<AccommodationType> accommodations) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.resources = resources;
        this.accommodations = accommodations;
}

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id =id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<Resource> getResources() {
        return this.resources;
    }

    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }

    public ArrayList<AccommodationType> getAccommodations() {
        return this.accommodations;
    }

    public void setAccommodations(ArrayList<AccommodationType> accommodations) {
        this.accommodations = accommodations;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getHoursOfOperation() {
        return this.hoursOfOperation;
    }

    public void setHoursOfOperation(String hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }


    
