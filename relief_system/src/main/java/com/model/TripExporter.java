package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TripExporter {

    public static boolean exportTrip(
            ReliefRequest request,
            User volunteer,
            Shelter shelter,
            String familyMembers,
            String medicalNote,
            String directionsToFamily,
            String directionsToShelter) {

        String fileName = "trip_" + request.getRequestId() + ".txt";

        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            out.println("==================================================");
            out.println("           HURRICANE RELIEF - TRIP PLAN");
            out.println("==================================================");
            out.println();

            out.println("FAMILY MEMBERS");
            out.println("--------------------------------------------------");
            out.println(familyMembers);
            out.println();

            out.println("PET");
            out.println("--------------------------------------------------");
            if (request.getPets() != null) {
                request.getPets().forEach(pet -> out.println(pet));
            }
            out.println();

            out.println("FAMILY CONTACT INFORMATION");
            out.println("--------------------------------------------------");
            out.println(request.getContactInformation());
            out.println();

            out.println("PICKUP ADDRESS");
            out.println("--------------------------------------------------");
            out.println(request.getLocation());
            out.println();

            out.println("MEDICAL INFORMATION");
            out.println("--------------------------------------------------");
            out.println(medicalNote);
            out.println();

            out.println("ASSIGNED VOLUNTEER");
            out.println("--------------------------------------------------");
            out.println(volunteer.getFirstName() + " " + volunteer.getLastName());
            out.println("Vehicle: " + volunteer.getVehicle());
            out.println();

            out.println("DIRECTIONS: VOLUNTEER TO FAMILY");
            out.println("--------------------------------------------------");
            out.println(directionsToFamily);
            out.println();

            out.println("SELECTED SHELTER");
            out.println("--------------------------------------------------");
            out.println("Shelter Name: " + shelter.getName());
            out.println("Shelter Address: " + shelter.getLocation());
            out.println();

            out.println("DIRECTIONS: FAMILY TO SHELTER");
            out.println("--------------------------------------------------");
            out.println(directionsToShelter);
            out.println();

            out.println("==================================================");
            out.println("                END OF TRIP PLAN");
            out.println("==================================================");

            return true;

        } catch (IOException e) {
            System.out.println("Could not export trip: " + e.getMessage());
            return false;
        }
    }
}
