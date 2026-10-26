package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * represents a shelter in the system, its capacity, resources, accommodations, location, and hours of operation.
 */
public class Shelter {
    private UUID id;
    private String name;
    private int capacity;
    private ArrayList<Resource> resources;
    private ArrayList<AccommodationType> accommodations;
    private Location location;
    private String hoursOfOperation;

    private static Shelter instance;
    private ArrayList<Shelter> shelters = new ArrayList<>();

    /**
     * creates a new shelter instance with the specified details.
     * @param id the unique identifier for the shelter
     * @param name the name of the shelter
     * @param capacity the capacity of the shelter
     * @param resources the list of resources available at the shelter
     * @param accommodations the list of accommodation types available at the shelter
     * @param location the location of the shelter
     * @param hoursOfOperation the hours of operation for the shelter
     */
    public Shelter(UUID id, String name, int capacity, ArrayList<Resource> resources, ArrayList<AccommodationType> accommodations, Location location, String hoursOfOperation) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.resources = resources;
        this.accommodations = accommodations;
        this.location = location;
        this.hoursOfOperation = hoursOfOperation;
    }

    /**
     * returns the shared singleton instance of the Shelter class.
     * @return the shared singleton instance of the Shelter class.
     */
    public static Shelter getInstance() {
        if (instance == null) {
            instance = new Shelter(
                UUID.randomUUID(),
                "",
                0,
                new ArrayList<Resource>(),
                new ArrayList<AccommodationType>(),
                null, 
                "");
                instance.shelters.addAll(DataLoader.getShelters());
        }
        return instance;
    }

    /**
     * returns shelters managed by this instance.
     * @return the list of shelters managed by this instance.
     */
    public ArrayList<Shelter> getShelters() {
        return shelters;
    }

    /**
     * returns the shelter's unique identifier.
     * @return the shelter's unique identifier.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * sets the shelter's unique identifier.
     * @param id the new unique identifier for the shelter
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * returns the shelter's name.
     * @return the shelter's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * sets the shelter's name.
     * @param name the new name for the shelter
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns the shelter's capacity.
     * @return the shelter's capacity.
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * sets the shelter's capacity.
     * @param capacity the new capacity for the shelter
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * returns the list of resources available at the shelter.
     * @return the list of resources available at the shelter.
     */
    public ArrayList<Resource> getResources() {
        return this.resources;
    }

    /**
     * sets the list of resources available at the shelter.
     * @param resources the new list of resources for the shelter
     */
    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }

    /**
     * returns the list of accommodation types available at the shelter.
     * @return the list of accommodation types available at the shelter.
     */
    public ArrayList<AccommodationType> getAccommodations() {
        return this.accommodations;
    }

    /**
     * sets the list of accommodation types available at the shelter.
     * @param accommodations the new list of accommodation types for the shelter
     */
    public void setAccommodations(ArrayList<AccommodationType> accommodations) {
        this.accommodations = accommodations;
    }

    /**
     * returns the location of the shelter.
     * @return the location of the shelter.
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * sets the location of the shelter.
     * @param location the new location for the shelter
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * returns the hours of operation for the shelter.
     * @return the hours of operation for the shelter.
     */
    public String getHoursOfOperation() {
        return this.hoursOfOperation;
    }

    /**
     * sets the hours of operation for the shelter.
     * @param hoursOfOperation the new hours of operation for the shelter
     */
    public void setHoursOfOperation(String hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }

    /**
     * updates the shelter's capacity if the new capacity is non-negative.
     * @param newCapacity the new capacity for the shelter
     */
    public void updateCapacity(int newCapacity) {
        if (newCapacity >= 0) {
            this.capacity = newCapacity;
        }
    }

    /**
     * updates the list of resources available at the shelter.
     * @param newResources the new list of resources for the shelter
     */
    public void updateResources(ArrayList<Resource> newResources) {
        this.resources = newResources;
    }

    /**
     * returns whether the shelter has available capacity.
     * @return true if the shelter has available capacity, false otherwise.
     */
    public boolean getAvailability() {
        return this.capacity > 0;
    }

    /**
     * returns a map representation of the shelter with its name and location.
     * @return a map containing the shelter's name and location.
     */
    public Map<String, String> getMap() {
        Map<String, String> map = new HashMap<>();
        map.put("name", this.name);
        if (location != null) {
            map.put("location", this.location.toString());
        }
        return map;
    }

    /**
     * calculates the distance from the shelter to the user's location.
     * @param userLocation the location of the user
     * @return the distance from the shelter to the user's location, or -1.0 if either location is null.
     */
    public double getDistanceFromUser(Location userLocation) {
        if (location == null || userLocation == null) {
            return -1.0;
        }
        return location.getDistanceFrom(userLocation);
    }

    /**
     * caches the current location of the shelter.
     */ 
    public void cacheLocation() {
    }

    /**
     * adds a shelter to the list of shelters if it is not already present.
     * @param shelter the shelter to be added
     */
    public void addShelter(Shelter shelter) {
        if (shelter != null && !shelters.contains(shelter)) {
            shelters.add(shelter);
        }
    }
    
    /**   
     * returns a readable string representation of the shelter.
     */
    @Override
    public String toString() {
        return "Shelter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", resources=" + resources +
                ", accommodations=" + accommodations +
                ", location=" + location +
                ", hoursOfOperation='" + hoursOfOperation + '\'' +
                '}';
    }
}