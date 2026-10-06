package com.model;

import java.util.ArrayList;

public class ShelterList {
    private static ShelterList shelterList;
    private ArrayList<Shelter> shelters;

    private ShelterList() {
        this.shelters = new ArrayList<>();
    }

    public static ShelterList getInstance() {
        return null;
    }

    public Shelter getShelter(String name) {
        return null;
    }

    public ArrayList<Shelter> getShelters() {
        return null;
    }

    public void addShelter(Shelter shelter) {
        return;
    }

    public boolean save() {
        return false;
    }

}
