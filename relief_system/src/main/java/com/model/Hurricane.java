package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a hurricane and stores information about affected 
 * ZIP codes, hurricane status, evacuation steps, and emergency supplies.
 */
public class Hurricane {
    private UUID id;
    private ArrayList<String> hurricaneZipCodes;
    private HurricaneStatus hurricaneStatus;
    private ArrayList<String> evacuationSteps;
    private ArrayList<Resource> emergencySupplies;

    private static Hurricane instance;
    private ArrayList<Hurricane> hurricanes = new ArrayList<>();

    public Hurricane(UUID id, ArrayList<String> hurricaneZipCodes, HurricaneStatus hurricaneStatus, ArrayList<String> evacuationSteps, ArrayList<Resource> emergencySupplies) {
        this.id = id;
        this.hurricaneZipCodes = hurricaneZipCodes;
        this.hurricaneStatus = hurricaneStatus;
        this.evacuationSteps = evacuationSteps;
        this.emergencySupplies = emergencySupplies;
        
    }

    /**
     * Returns the singleton Hurricane instance or creating it.
     * @return the singleton Hurricane instance
     */
    public static Hurricane getInstance() {
        if (instance == null) {
            instance = new Hurricane(
                UUID.randomUUID(),
                new ArrayList<String>(),
                null,
                new ArrayList<String>(),
                new ArrayList<Resource>()
            );
        }
        return instance;
    }

    /**
     * @returns list of hurricane objects
     */
    public ArrayList<Hurricane> getHurricane() {
        return hurricanes;
    }

    public UUID getId() {
        return id;
    }

    public ArrayList<String> getZipCodes() {
        return hurricaneZipCodes;
    }

    public HurricaneStatus getStatus() {
        return hurricaneStatus;
    }

    public ArrayList<String> getEvacuationSteps() {
        return evacuationSteps;
    }

    public ArrayList<Resource> getEmergencySupplies() {
        return emergencySupplies;
    }

    public void reportDamage() {
        //TODO
    }

    public String viewHurricaneInfo() {
        return null;
    }

    public void attachDamagePhoto(String photo) {
        //TODO
    }

    public ArrayList<Resource> viewEmergencySupplies() {
        return null;
    }

    public void addHurricane(Hurricane hurricane) {
        if (hurricane != null && !hurricanes.contains(hurricane)) {
            hurricanes.add(hurricane);
        }
    }
}
