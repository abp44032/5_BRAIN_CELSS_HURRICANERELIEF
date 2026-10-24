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
                    System.out.println("Thank you for using the Hurricane Relief System.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
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

    // Main menu shown after a successful login
    private void mainMenu() {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println();
            System.out.println("===== User Menu =====");
            System.out.println("1. View Available Shelters");
            System.out.println("2. Create Relief Request");
            System.out.println("3. Log Out");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewShelters();
                    break;

                case "2":
                    implementReliefRequest();
                    break;

                case "3":
                    System.out.println("You have logged out.");
                    loggedIn = false;
                    break;
            }
        }
    }

    // Display available shelters
    private void viewShelters() {
        ArrayList<Shelter> shelters = app.getShelters();

        System.out.println();
        System.out.println("===== Available Shelters =====");

        if (shelters == null || shelters.isEmpty()) {
            System.out.println("Sorry, there are no shelters available.");
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
            System.out.println("Sorry, we couldn't create the account.");
            return;
        }

        System.out.println("Account created successfully!");
        System.out.println("Welcome, " + user.getFirstName() + "!"); 
        System.out.println("You can now log in using your new account.");
    }

    // implement relief request scenario
    private void implementReliefRequest() {
        System.out.println("\n===== Create Relief Request =====");

        System.out.println("Choose request type:");
        RequestType[] types = RequestType.values();

        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i]);
        }

        System.out.print("Choose an option: ");
        int typeChoice;

        try {
            typeChoice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            return;
        }

        if (typeChoice < 1 || typeChoice > types.length) {
            System.out.println("Invalid choice.");
            return;
        }

        RequestType type = types[typeChoice - 1];

        System.out.println("\nChoose severity:");
        SeverityLevel[] severities = SeverityLevel.values();

        for (int i = 0; i < severities.length; i++) {
            System.out.println((i + 1) + ". " + severities[i]);
        }

        System.out.print("Choose an option: ");
        int severityChoice;

        try {
            severityChoice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            return;
        }

        if (severityChoice < 1 || severityChoice > severities.length) {
            System.out.println("Invalid choice.");
            return;
        }

        SeverityLevel severity = severities[severityChoice - 1];

        System.out.println("\nChoose urgency:");
        UrgencyLevel[] urgencies = UrgencyLevel.values();

        for (int i = 0; i < urgencies.length; i++) {
            System.out.println((i + 1) + ". " + urgencies[i]);
        }

        System.out.print("Choose an option: ");
        int urgencyChoice;

        try {
            urgencyChoice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            return;
        }

        if (urgencyChoice < 1 || urgencyChoice > urgencies.length) {
            System.out.println("Invalid choice.");
            return;
        }

        UrgencyLevel urgency = urgencies[urgencyChoice - 1];

        System.out.print("\nDescribe the situation: ");
        String description = scanner.nextLine().trim();

        if (description.isEmpty()) {
            System.out.println("Description cannot be empty.");
            return;
        }

        System.out.print("How many people need assistance? ");

        int totalPeople;

        try {
            totalPeople = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return;
        }

        if (totalPeople < 1) {
            System.out.println("At least one person must be entered.");
            return;
        }

        ArrayList<Pet> pets = new ArrayList<>();

        System.out.print("How many pets need assistance? ");

        int petCount;

        try {
            petCount = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return;
        }

        if (petCount < 0) {
            System.out.println("The number of pets cannot be negative.");
            return;
        }

        for (int i = 0; i < petCount; i++) {
            System.out.print("Enter pet " + (i + 1) + "'s name: ");
            String petName = scanner.nextLine().trim();

            System.out.print("Enter pet " + (i + 1) + "'s type: ");
            String petType = scanner.nextLine().trim();

            if (petName.isEmpty() || petType.isEmpty()) {
                System.out.println("Pet name and type cannot be empty.");
                return;
            }

            pets.add(new Pet(petName, petType));
        }

        ReliefRequest request = new ReliefRequest(
            java.util.UUID.randomUUID(),
            RequestStatus.RECEIVED,
            type,
            severity,
            urgency,
            description,
            totalPeople,
            pets
    );

    app.submitRequest(request);

    }

    public static void main(String[] args) {
        EmergencyReliefUI emergencyReliefUI =
            new EmergencyReliefUI();

        emergencyReliefUI.run();
    }
}
