package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents the list of shelters in the system.
 */
public class ShelterList {
    private static ShelterList shelterList;
    private ArrayList<Shelter> shelters;

    /**
     * Private constructor to initialize the shelter list.
     * Loads shelters from the data source or creates an empty list if none are found.
     */
    private ShelterList() {
       this.shelters = DataLoader.getShelters();
       if (this.shelters == null) {
           this.shelters = new ArrayList<>();
       }
    }

    /**
     * returns the singleton instance of the ShelterList.
     * @return the singleton instance of the ShelterList.
     */
    public static ShelterList getInstance() {
        if (shelterList == null) {
            shelterList = new ShelterList();
        }
        return shelterList;
    }

    /**
     * returns the shelter with the specified name, or null if not found.
     * @param name the name of the shelter to retrieve
     * @return the shelter with the specified name, or null if not found
     */
    public Shelter getShelter(String name) {
        if (name == null) {
            return null;
        }
        for (Shelter shelter : shelters) {
            if (shelter.getName().equalsIgnoreCase(name)) {
                return shelter;
            }
        }
        return null;
    }

    /**
     * returns all known shelters
     * @return the list of all shelters in the system
     */
    public ArrayList<Shelter> getShelters() {
        return this.shelters;
    }

    /**
     * adds a shelter to the list if it is not already present
     * @param shelter the shelter to add
     */
    public void addShelter(Shelter shelter) {
        if (shelter != null) {
            this.shelters.add(shelter);
        }
        if (getShelter(shelter.getName()) == null) {
            this.shelters.add(shelter);
        }
    }

    /**
     * adds a shelter to the list with the specified details if it is not already present
     * @param id the unique identifier for the shelter
     * @param name the name of the shelter
     * @param capacity the capacity of the shelter
     * @param resources the list of resources available at the shelter
     * @param accommodations the list of accommodation types available at the shelter
     * @param location the location of the shelter
     * @param hoursOfOperation the hours of operation for the shelter
     * @return true if the shelter was added successfully, false if a shelter with the same name already exists
     */
    public boolean addShelter(UUID id, String name, int capacity, ArrayList<Resource> resources, ArrayList<AccommodationType> accommodations, Location location, String hoursOfOperation) {
        if (getShelter(name)!= null) {
            return false;
        }
        Shelter shelter = new Shelter(id, name, capacity, resources, accommodations, location, hoursOfOperation);
        shelter.setHoursOfOperation(hoursOfOperation);
        this.shelters.add(shelter);
        return true;
    }
    
    /**
     * saves all the shelter through the data writer
     * @return true if the shelters were saved successfully, false otherwise
     */
    public boolean save() {
        return DataWriter.saveShelters();
    }

    /**
     * runs a simple test to print all loaded shelters
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        ShelterList list = ShelterList.getInstance();
        
        System.out.println("Loaded shelters count: " + list.getShelters().size());
        
        for (Shelter s : list.getShelters()) {
            System.out.println("- " + s.getName());
        }
    }
}
