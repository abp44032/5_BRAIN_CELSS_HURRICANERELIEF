package com.model;

import java.util.ArrayList;

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
            if (shelter.getName().equals(name)) {
                return shelter;
            
            }
        }
        return null;
    }

    public ArrayList<Shelter> getShelters() {
        return this.shelters;
    }

    public void addShelter(Shelter shelter) {
        if (shelter != null) {
            this.shelters.add(shelter);
        }
        if (getShelter(shelter.getName()) == null) {
            this.shelters.add(shelter);
        }
    }

    public boolean save() {
        return DataWriter.saveShelters();
    }

    public static void main(String[] args) {
        ShelterList list = ShelterList.getInstance();
        
        System.out.println("Loaded shelters count: " + list.getShelters().size());
        
        for (Shelter s : list.getShelters()) {
            System.out.println("- " + s.getName());
        }
    }
}
