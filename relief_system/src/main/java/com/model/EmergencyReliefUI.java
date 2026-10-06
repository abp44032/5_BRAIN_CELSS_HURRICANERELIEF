package com.model;

import java.util.ArrayList;
import java.util.Scanner;

public class EmergencyReliefUI {

    private Scanner scanner;
    private ArrayList<Shelter> shelters;

    public EmergencyReliefUI(ArrayList<Shelter> shelters) {
        scanner = new Scanner(System.in);
        this.shelters = shelters;
    }

    public void run() {
        displayMainMenu();
    }

    private void displayMainMenu() {
        System.out.println("===== Emergency Relief System =====");
        System.out.println("1. View Shelters");
        System.out.println("2. Login");
        System.out.println("3. Create Account");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();

        if (choice == 1) {
            displayShelters();
        } else if (choice == 2) {
            login();
        } else if (choice == 3) {
            createAccount();
        } else if (choice == 4) {
            System.out.println("Goodbye!");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void displayShelters() {
        System.out.println();
        System.out.println("===== Available Shelters =====");

        for (Shelter shelter : shelters) {
            System.out.println("Shelter: " + shelter.getName());
            System.out.println("ID: " + shelter.getId());
            System.out.println("Capacity: " + shelter.getCapacity());
            System.out.println("Hours: " + shelter.getHoursOfOperation());
            System.out.println("------------------------------");
        }
    }

    private void login() {
        
    }

    private void createAccount() {
       
    }

    private void displayVictimMenu() {
       
    }

    private void displayVolunteerMenu() {
        
    }

    private void displayAdminMenu() {
        
    }
}