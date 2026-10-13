package com.model;

import java.util.ArrayList;
import java.util.Scanner;

public class EmergencyReliefUI {

    private EmergencyReliefApplication app;
    private Scanner scanner;

    public EmergencyReliefUI() {
        app = new EmergencyReliefApplication();
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;

        System.out.println("===== Hurricane Relief System =====");

        while (running) {
            System.out.println();
            System.out.println("===== Main Menu =====");
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    scenario1();
                    break;

                case "2":
                    scenario2();
                    break;

                case "3":
                    System.out.println(
                        "Thank you for using the Hurricane Relief System."
                    );
                    running = false;
                    break;

                default:
                    System.out.println(
                        "Invalid option. Please try again."
                    );
            }
        }
    }

    // Scenario 1: Log in to the system
    public void scenario1() {
        System.out.println();
        System.out.println("===== Login =====");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (!app.login(username, password)) {
            System.out.println("Sorry, we couldn't log in.");
            return;
        }

        System.out.println("Login successful!");
        mainMenu();
    }

    // Main menu displayed after a successful login
    private void mainMenu() {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println();
            System.out.println("===== User Menu =====");
            System.out.println("1. View Available Shelters");
            System.out.println("2. Log Out");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewShelters();
                    break;

                case "2":
                    System.out.println("You have logged out.");
                    loggedIn = false;
                    break;

                default:
                    System.out.println(
                        "Invalid option. Please try again."
                    );
            }
        }
    }

    // Display available shelters
    private void viewShelters() {
        ArrayList<Shelter> shelters = app.getShelters();

        System.out.println();
        System.out.println("===== Available Shelters =====");

        if (shelters == null || shelters.isEmpty()) {
            System.out.println(
                "Sorry, there are no shelters available."
            );
            return;
        }

        for (Shelter shelter : shelters) {
            System.out.println(shelter);
            System.out.println("------------------------------");
        }
    }

    // Scenario 2: Create a new account
    public void scenario2() {
        System.out.println();
        System.out.println("===== Create Account =====");

        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Choose a username: ");
        String username = scanner.nextLine();

        System.out.print("Choose a password: ");
        String password = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        User user = app.createAccount(
            firstName, lastName, username, password, email
        );

        if (user == null) {
            System.out.println(
                "Sorry, we couldn't create the account. "
                + "The username may already exist, or some "
                + "information may be missing."
            );
            return;
        }

        System.out.println("Account created successfully!");
        System.out.println(
            "Welcome, " + user.getFirstName() + "!"
        );
        System.out.println(
            "You can now log in using your new account."
        );
    }

    public static void main(String[] args) {
        EmergencyReliefUI emergencyReliefUI =
            new EmergencyReliefUI();

        emergencyReliefUI.run();
    }
}
