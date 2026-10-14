package com.model;

public class Pet extends ReliefRequest{
    private String name;
    private String type;

    public Pet(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
    
    public static void main(String[] args) {
        Pet pet = new Pet("Buddy", "Dog");
        System.out.println("Pet Name: " + pet.getName());
        System.out.println("Pet Type: " + pet.getType());
    }
}
