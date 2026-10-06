package com.model;

import java.util.Scanner;

public class EmergencyReliefUI {

    private Scanner scanner;

    public EmergencyReliefUI() {
        scanner = new Scanner(System.in);
    }

    public void run() {
        displayMainMenu();
    }

    private void displayMainMenu() {
        System.out.println("===== Emergency Relief System =====");
        System.out.println("1. Login");
        System.out.println("2. Create Account");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private void login() {
        
    }

    private void createAccount() {
        
    }

    private void displayUserMenu() {
        
    }

    private void displayShelters() {
        
    }
}
