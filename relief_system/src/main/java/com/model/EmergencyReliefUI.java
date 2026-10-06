package com.model;

import java.util.ArrayList;

public class EmergencyReliefUI {

    private EmergencyReliefApplication app;

    public EmergencyReliefUI() {
        app = new EmergencyReliefApplication();
    }

    public void run() {
        scenario1();
        scenario2();
    }

    // Scenario 1
    public void scenario1() {
        System.out.println();

        if (!app.login("jcarter", "12345")) {
            System.out.println("Sorry, we couldn't log in.");
            return;
        }

        System.out.println("Jasmine Carter is now logged in.");

        ArrayList<Shelter> shelters = app.getShelters();

        if (shelters == null || shelters.isEmpty()) {
            System.out.println("Sorry, there are no shelters available.");
            return;
        }

        System.out.println("Available shelters:");

        for (Shelter shelter : shelters) {
            System.out.println(shelter);
            System.out.println("------------------------------");
        }
    }

    // Scenario 2
    public void scenario2() {
        System.out.println();

        User user = app.createAccount(
                "julianparker",
                "12345",
                "julian@example.com"
        );

        if (user == null) {
            System.out.println("Sorry, we couldn't create the account.");
            return;
        }

        System.out.println("Julian Parker's account was successfully created.");
    }

    private void createReliefRequest() {
        // TODO
    }

    private void viewReliefRequests() {
        // TODO
    }
   
    private void logout() {
        // TODO
    }

    public static void main(String[] args) {
        EmergencyReliefUI emergencyReliefUI = new EmergencyReliefUI();
        emergencyReliefUI.run();
    }
}