package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class ShelterList {
    private static ShelterList shelterList;
    private ArrayList<Shelter> shelters;

    private ShelterList() {
       this.shelters = DataLoader.getShelters();
       if (this.shelters == null) {
           this.shelters = new ArrayList<>();
       }
    }

    public static ShelterList getInstance() {
        if (shelterList == null) {
            shelterList = new ShelterList();
        }
        return shelterList;
    }

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

    public ArrayList<Shelter> getShelters() {
        return this.shelters;
    }

    public boolean addShelter(UUID id, String name, int capacity, String hoursOfOperation, ArrayList<Resource> resources, ArrayList<AccommodationType> accommodations) {
        if (getShelter(name)!= null) {
            return false;
        }
        Shelter shelter = new Shelter(id, name, capacity, resources, accommodations);
        shelter.setHoursOfOperation(hoursOfOperation);
        this.shelters.add(shelter);
        return true;
        }
    }

    public boolean save() {
        return DataWriter.saveShelters();
    }

}
