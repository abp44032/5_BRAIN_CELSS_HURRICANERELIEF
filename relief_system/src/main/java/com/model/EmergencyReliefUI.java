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
                    app.logout();
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
        User user = app.getCurrentUser();

        if (user == null) {
            return;
        }

        if (User.VOLUNTEER.equals(user.getRole())) {
            volunteerMenu();
        } else {
            residentMenu();
        }
    }

    // Volunteer menu
    private void volunteerMenu() {
        String choice;

        do {
            System.out.println("\n===== Volunteer Menu =====");
            System.out.println("1. View Available Relief Requests");
            System.out.println("2. View Available Shelters");
            System.out.println("3. View Volunteer Profile");
            System.out.println("4. Log Out");
            System.out.print("Choose an option: ");

            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewReliefRequests();
                    break;
                case "2":
                    acceptReliefRequest();
                    break;

                case "3":
                    viewShelters();
                    break;
                case "4":
                    viewVolunteerProfile();
                    break;
                case "5":
                    app.logout();
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (!choice.equals("4"));
    }

    // view volunteer profile
    private void viewVolunteerProfile() {
        User user = app.getCurrentUser();

        if (user == null) {
            System.out.println("No user is currently logged in.");
            return;
        }

        System.out.println("\n===== Volunteer Profile =====");
        System.out.println("Name: " + user.getFirstName()
            + " " + user.getLastName());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Specialty: " + user.getSpecialty());
        System.out.println("Vehicle: " + user.getVehicle());
    }

    // View available relief requests
    private void viewReliefRequests() {
        ArrayList<ReliefRequest> requests = DataLoader.getReliefRequests();

        requests.removeIf(request ->
            request.getStatus() == RequestStatus.COMPLETED ||
            request.getStatus() == RequestStatus.CANCELLED
        );

        if (requests.isEmpty()) {
            System.out.println("There are no relief requests available.");
            return;
        }

        User currentUser = app.getCurrentUser();

        if (currentUser != null
            && User.VOLUNTEER.equals(currentUser.getRole())
            && currentUser.getSpecialty() != null) {

            String specialty = currentUser.getSpecialty();

            for (int i = 0; i < requests.size(); i++) {
            ReliefRequest request = requests.get(i);

                if (request.getType() != null
                        && request.getType().name().equalsIgnoreCase(specialty)) {

                    // Move this matching request to the front.
                    requests.remove(i);
                    requests.add(0, request);
                    break;
                }
            }
        }

        System.out.println("\n===== Open Relief Requests =====");

            for (int i = 0; i < requests.size(); i++) {
                ReliefRequest request = requests.get(i);

                System.out.println("\nRequest " + (i + 1));

                if (currentUser != null
                        && User.VOLUNTEER.equals(currentUser.getRole())
                        && currentUser.getSpecialty() != null
                        && request.getType() != null
                        && request.getType().name().equalsIgnoreCase(
                            currentUser.getSpecialty())) {

                    System.out.println("*** RECOMMENDED FOR YOU ***");
                    System.out.println(
                        "Reason: This request matches your "
                        + currentUser.getSpecialty() + " specialty."
                    );
                }

                System.out.println(request.getDetails());
            }
        }


        private boolean isNearby(ReliefRequest request, User volunteer) {
            if (volunteer == null
                    || !volunteer.isShareLocation()
                    || volunteer.getLocation() == null
                    || request.getLocation() == null) {
                return false;
            }

            String volunteerCity = volunteer.getLocation().getCity();
            String volunteerState = volunteer.getLocation().getState();
            String requestCity = request.getLocation().getCity();
            String requestState = request.getLocation().getState();

            return volunteerCity != null
                    && volunteerState != null
                    && requestCity != null
                    && requestState != null
                    && volunteerCity.trim().equalsIgnoreCase(requestCity.trim())
                    && volunteerState.trim().equalsIgnoreCase(requestState.trim());
        }

    // Allows volunteers to accept available releif requests
    private void acceptReliefRequest(){
        ArrayList<ReliefRequest> requests = DataLoader.getReliefRequests();
        
        if (requests == null || requests.isEmpty()) {
            System.out.println("There are no relief requests available.");
            return;
        }

        // Display all availble releif requests
        User currentUser = app.getCurrentUser();

        for (int i = 0; i < requests.size(); i++) {
            ReliefRequest request = requests.get(i);

            System.out.println("\nRequest " + (i + 1));

            
        if (currentUser != null
                && User.VOLUNTEER.equals(currentUser.getRole())) {

            boolean specialtyMatch =
                    currentUser.getSpecialty() != null
                    && request.getType() != null
                    && request.getType().name().equalsIgnoreCase(
                            currentUser.getSpecialty());

            boolean locationMatch = isNearby(request, currentUser);

            if (specialtyMatch || locationMatch) {
                System.out.println("*** RECOMMENDED FOR YOU ***");

                if (locationMatch) {
                    System.out.println(
                        "Reason: This request is in your city and state."
                    );
                }

                if (specialtyMatch) {
                    System.out.println(
                        "Reason: This request matches your "
                        + currentUser.getSpecialty() + " specialty."
                    );
                }
            }
        }

    System.out.println(request.getDetails());
}

        System.out.println("Enter the request number you want to accept: ");
        int choice;

        
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid request number.");
            return;
        }
        if ( choice < 1 || choice > requests.size()) {
            System.out.println("invalid request number.");
            return;
        }
        ReliefRequest request = requests.get(choice - 1);

        ArrayList<ReliefRequest> savedRequests =
        ReliefRequestList.getInstance().getRequests();

        savedRequests.removeIf(existing ->
                existing.getRequestId().equals(request.getRequestId()));

        savedRequests.add(request);

        // Accept the request through the facade
        boolean accepted = app.acceptRequest(request, app.getCurrentUser());

        if (accepted) {
            System.out.println("Request accepted successfully!");
        } else {
            System.out.println("Unable to accept this request.");
        }
    }

    // Resident menu
    private void residentMenu() {
        String choice;

        do {
            System.out.println("\n===== Resident Menu =====");
            System.out.println("1. View Available Shelters");
            System.out.println("2. Create Relief Request");
            System.out.println("3. Log Out");
            System.out.print("Choose an option: ");

            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewShelters();
                    break;
                case "2":
                    implementReliefRequest();
                    break;
                case "3":
                    app.logout();
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (!choice.equals("3"));
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

       for (int i = 0; i < shelters.size(); i++) {
        Shelter shelter = shelters.get(i);
            if (i > 0) {
                System.out.println();
                System.out.println("--------------------------------");
                System.out.println();
            }
            System.out.println("Shelter: " + shelter.getName());
            System.out.println("Capacity: " + shelter.getCapacity());
            System.out.println("Hours: " + shelter.getHoursOfOperation());
            System.out.println("Available: " + shelter.getAvailability());
            System.out.println("Accommodations: " + shelter.getAccommodations());
            System.out.println("Resources: " + shelter.getResources());
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

        if (firstName.isBlank() || lastName.isBlank()|| username.isBlank() || password.isBlank()|| email.isBlank()) {
            System.out.println("All fields are required.");
            return;
        }

    System.out.println("\nWhat best describes you?");
    System.out.println("1. Resident (I need help)");
    System.out.println("2. Volunteer (I want to help others)");
    System.out.print("Choose an option: ");

    String roleChoice = scanner.nextLine();
    String role;
    String specialty = null;

    if (roleChoice.equals("1")) {
        role = User.RESIDENT;
    } else if (roleChoice.equals("2")) {
        role = User.VOLUNTEER;
    
        System.out.println("\nChoose your volunteer specialty:");
        System.out.println("1. Medical");
        System.out.println("2. Fire Protection");
        System.out.println("3. EMS");
        System.out.println("4. Transportation");
        System.out.print("Choose an option: ");

        String specialtyChoice = scanner.nextLine();

        if (specialtyChoice.equals("1")) {
            specialty = "MEDICAL";
        } else if (specialtyChoice.equals("2")) {
            specialty = "FIRE_PROTECTION";
        } else if (specialtyChoice.equals("3")) {
            specialty = "EMS";
        } else if (specialtyChoice.equals("4")) {
            specialty = "TRANSPORTATION";
        } else {
            System.out.println("Invalid specialty. Account not created.");
            return;
        }
    } else {
        System.out.println("Invalid choice. Account not created.");
        return;
    }

        System.out.println("\nEnter your location:");

        System.out.print("Street address: ");
        String address = scanner.nextLine().trim();

        System.out.print("City: ");
        String city = scanner.nextLine().trim();

        System.out.print("State: ");
        String state = scanner.nextLine().trim();

        System.out.print("ZIP code: ");
        String zip = scanner.nextLine().trim();

        if (address.isEmpty() || city.isEmpty()
                || state.isEmpty() || zip.isEmpty()) {
            System.out.println("All location fields are required.");
            return;
        }

        Location location = new Location(address, city, state, zip);

        boolean shareLocation = false;

        if (User.VOLUNTEER.equals(role)) {
            System.out.print(
                "Share your location for nearby recommendations? (yes/no): "
            );

            String shareChoice = scanner.nextLine().trim();

            if (!shareChoice.equalsIgnoreCase("yes")
                    && !shareChoice.equalsIgnoreCase("no")) {
                System.out.println("Please enter yes or no. Account not created.");
                return;
            }

            shareLocation = shareChoice.equalsIgnoreCase("yes");
        }

        User user = app.createAccount(
            firstName, lastName, username, password, email,
            role, specialty, location, shareLocation
        );

        if (user == null) {
            System.out.println("Sorry, we couldn't create the account.");
            return;
        }

        System.out.println("Account created successfully!");
        System.out.println("Welcome, " + user.getFirstName() + "!");

        if (User.VOLUNTEER.equals(role)) {
            System.out.println("Your specialty is: " + specialty);
            System.out.println("Location sharing: "
                    + (shareLocation ? "Enabled" : "Disabled"));
        }

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
        
        // Location 
        System.out.print("Enter street address: ");
        String address = scanner.nextLine().trim();

        System.out.print("Enter city: ");
        String city = scanner.nextLine().trim();

        System.out.print("Enter state: ");
        String state = scanner.nextLine().trim();

        System.out.print("Enter ZIP code: ");
        String zip = scanner.nextLine().trim();

        Location location = new Location(address, city, state, zip);

        // Contact Information
        System.out.print("Enter contact information (phone or email): ");
        String contactInformation = scanner.nextLine().trim();

        if (address.isEmpty() || city.isEmpty() || state.isEmpty()
                || zip.isEmpty() || contactInformation.isEmpty()) {
            System.out.println("Location and contact information are required.");
            return;
        }

        // Pets
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
            pets,
            location,
            contactInformation
        );

    app.submitRequest(request);

    }

    public static void main(String[] args) {
        EmergencyReliefUI emergencyReliefUI =
            new EmergencyReliefUI();

        emergencyReliefUI.run();
    }
}
