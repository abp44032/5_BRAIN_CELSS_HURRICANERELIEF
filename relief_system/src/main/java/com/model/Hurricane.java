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

        this.id = id;
        this.hurricaneZipCodes = hurricaneZipCodes;
        this.hurricaneStatus = hurricaneStatus;
        this.evacuationSteps = evacuationSteps;
        this.emergencySupplies = emergencySupplies;
    }

    public UUID getId() {
        return id;
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

	public static Hurricane getInstance() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getInstance'");
	}

    public ArrayList<User> getUsers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsers'");
    }

    public Object getName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }

    public Object getCategory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCategory'");
    }

    public Object getLocation() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLocation'");
    }
}
