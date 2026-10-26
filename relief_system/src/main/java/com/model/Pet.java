package com.model;

/**
 * Represents a pet associated with a relief request.
 */
public class Pet {
    private String name;
    private String type;

    /**
     * Constructs a new Pet with the specified name and type.
     * @param name the name of the pet
     * @param type the type of the pet
     */
    public Pet(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Returns the name of the pet.
     * @return the name of the pet
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of the pet.
     * @return the type of the pet
     */
    public String getType() {
        return type;
    }
    
    /**
     * Returns a string representation of the pet.
     * @return a string containing the name and type of the pet
     */
    @Override
    public String toString() {
        return "Pet{name='" + name + "', type='" + type + "'}";
    }

    /**
     * Main method for testing the Pet class.
     * @param args command-line arguments
     */
    // -------- MAIN METHOD --------- 
    public static void main(String[] args) {
        Pet pet = new Pet("Buddy", "Dog");
        
        System.out.println("Pet Name: " + pet.getName());
        System.out.println("Pet Type: " + pet.getType());
    }
}
