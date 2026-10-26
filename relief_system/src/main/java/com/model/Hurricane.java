package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a hurricane and stores information about affected 
 * ZIP codes, hurricane status, evacuation steps, and emergency supplies.
 */
public class Hurricane {
    private UUID id;
    private String name;
    private ArrayList<String> hurricaneZipCodes;
    private HurricaneStatus hurricaneStatus;
    private ArrayList<String> evacuationSteps;
    private ArrayList<Resource> emergencySupplies;
    
    private static Hurricane instance;
    private ArrayList<Hurricane> hurricanes = new ArrayList<>();

    /**
     * Constructs a new Hurricane instance with the specified details.
     * @param id the unique identifier of the hurricane
     * @param name the name of the hurricane
     * @param hurricaneZipCodes the list of affected ZIP codes
     * @param hurricaneStatus the status of the hurricane
     * @param evacuationSteps the list of evacuation steps
     * @param emergencySupplies the list of emergency supplies
     */
    public Hurricane(UUID id, String name, ArrayList<String> hurricaneZipCodes, HurricaneStatus hurricaneStatus, ArrayList<String> evacuationSteps, ArrayList<Resource> emergencySupplies) {
        this.id = id;
        this.name = name;
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
                "",
                new ArrayList<String>(),
                null,
                new ArrayList<String>(),
                new ArrayList<Resource>()
            );
            instance.hurricanes.addAll(DataLoader.getHurricanes());
        }
        return instance;
    }

    /**
     * @returns list of hurricane objects
     */
    public ArrayList<Hurricane> getHurricane() {
        return hurricanes;
    }

    public String getName() {
        return name;
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

    /**
     * Returns a string containing detailed information about the hurricane, including its name, status,
     * affected ZIP codes, evacuation steps, and emergency supplies.
     * @return a string representation of the hurricane's information
     */
    public String viewHurricaneInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hurricane ").append(name).append(" (").append(hurricaneStatus).append(")\n");
        sb.append("Affected zip codes: ").append(String.join(", ", hurricaneZipCodes)).append("\n");
        sb.append("Evacuation steps:\n");
        int n = 1;
        for (String step : evacuationSteps) {
            sb.append("  ").append(n++).append(". ").append(step).append("\n");
        }
        sb.append("Emergency supplies:\n");
        for (Resource supply : emergencySupplies) {
            sb.append("  - ").append(supply.getName()).append("\n");
        }
        return sb.toString();
    }


    public void attachDamagePhoto(String photo) {
        //TODO
    }

    /**
     * Returns the list of emergency supplies for the hurricane.
     * @return the list of emergency supplies
     */
    public ArrayList<Resource> viewEmergencySupplies() {
        return emergencySupplies;
    }

    /**
     * Adds a hurricane to the list of hurricanes if it is not already present.
     * @param hurricane the hurricane to add
     */
    public void addHurricane(Hurricane hurricane) {
        if (hurricane != null && !hurricanes.contains(hurricane)) {
            hurricanes.add(hurricane);
        }
    }

}
