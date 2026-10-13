package com.model;

import java.util.ArrayList;

public class EmergencyReliefUI {

    private EmergencyReliefApplication app;

    public EmergencyReliefUI() {
        app = new EmergencyReliefApplication();
    }

    public void run() {
        System.out.println("===== Hurricane Relief System =====");

        scenario1();
        scenario2();
    }

    // Scenario 1: Log in and view available shelters
    public void scenario1() {
        System.out.println();
        System.out.println("===== Scenario 1: Login and View Shelters =====");

        if (!app.login("thomas.cooper", "12345")) {
            System.out.println("Sorry, we couldn't log in.");
            return;
        }

        System.out.println("Thomas Cooper is now logged in.");

        ArrayList<Shelter> shelters = app.getShelters();

        if (shelters == null || shelters.isEmpty()) {
            System.out.println("Sorry, there are no shelters available.");
            return;
        }

        System.out.println();
        System.out.println("Available shelters:");

        for (Shelter shelter : shelters) {
            System.out.println(shelter);
            System.out.println("------------------------------");
        }
    }

    // Scenario 2: Create a new user account
    public void scenario2() {
        System.out.println();
        System.out.println("===== Scenario 2: Create an Account =====");

        User user = app.createAccount(
            "julianparker",
            "12345",
            "julian@example.com"
        );

        if (user == null) {
            System.out.println(
                "Sorry, we couldn't create the account."
            );
            return;
        }

        System.out.println(
            "Julian Parker's account was successfully created."
        );
    }

    // Scenario 3: Placeholder for creating a relief request
    private void createReliefRequest() {
        // TODO: Implement relief request creation.
    }

    // Scenario 4: Placeholder for viewing relief requests
    private void viewReliefRequests() {
        // TODO: Implement viewing relief requests.
    }

    // Logout the current user
    private void logout() {
        // TODO: Implement logout through the application facade.
    }

    public static void main(String[] args) {
        EmergencyReliefUI emergencyReliefUI =
            new EmergencyReliefUI();

        emergencyReliefUI.run();
    }
}