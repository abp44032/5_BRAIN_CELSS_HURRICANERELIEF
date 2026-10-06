package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class Hurricane {
    private UUID id;
    private ArrayList<String> hurricaneZipCodes;
    private HurricaneStatus hurricaneStatus;
    private ArrayList<String> evacuationSteps;
    private ArrayList<Resource> emergencySupplies;

    public Hurricane(UUID id, ArrayList<String> hurricaneZipCodes, HurricaneStatus hurricaneStatus, ArrayList<String> evacuationSteps, ArrayList<Resource> emergencySupplies) {

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
}
