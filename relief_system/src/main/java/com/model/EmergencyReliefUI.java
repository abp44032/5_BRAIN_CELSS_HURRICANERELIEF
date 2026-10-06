package com.model;

import java.util.ArrayList;
import java.util.Scanner;

public class EmergencyReliefUI {

    private EmergencyReliefApplication app;
    private Scanner scanner;

    public EmergencyReliefUI(EmergencyReliefApplication app) {
        this.app = app;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;

        while (running) {
            running = displayMainMenu();
        }
    }

    private boolean displayMainMenu() {
        System.out.println();
        System.out.println("===== Emergency Relief System =====");
        System.out.println("1. Login");
        System.out.println("2. Create Account");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                login();
                break;

            case "2":
                createAccount();
                break;

            case "3":
                System.out.println("Goodbye!");
                return false;

            default:
                System.out.println("Invalid choice. Please try again.");
        }

        return true;
    }

    private void login() {
        System.out.println();
        System.out.println("===== Login =====");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = app.login(username, password);

        if (user != null) {
            System.out.println("Login successful!");
            displayUserMenu();
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private void createAccount() {
        System.out.println();
        System.out.println("===== Create Account =====");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        User user = app.createAccount(username, password, email);

        if (user != null) {
            System.out.println("Account created successfully!");
            System.out.println("Please log in to continue.");
        } else {
            System.out.println("Unable to create account.");
        }
    }

    private void displayUserMenu() {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println();
            System.out.println("===== Main View =====");
            System.out.println("1. View Shelters");
            System.out.println("2. Create Relief Request");
            System.out.println("3. View Relief Requests");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    displayShelters();
                    break;

                case "2":
                    createReliefRequest();
                    break;

                case "3":
                    viewReliefRequests();
                    break;

                case "4":
                    logout();
                    loggedIn = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayShelters() {
        System.out.println();
        System.out.println("===== Available Shelters =====");

        ArrayList<Shelter> shelters = app.getShelters();

        if (shelters == null || shelters.isEmpty()) {
            System.out.println("No shelters are currently available.");
            return;
        }

        for (Shelter shelter : shelters) {
            System.out.println(shelter);
            System.out.println("------------------------------");
        }
    }

    private void createReliefRequest() {
        // TODO
    }

    private void viewReliefRequests() {
        // TODO
    }

    private void logout() {
        // TODO
        System.out.println("You have been logged out.");
    }
}