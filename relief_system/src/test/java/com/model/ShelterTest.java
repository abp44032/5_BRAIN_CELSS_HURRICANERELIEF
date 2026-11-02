package com.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
--------------------------------------------------------------------------------------------------------------------------------------
 TEST                                                           | REASONING                                                           |
--------------------------------------------------------------------------------------------------------------------------------------
 checkInFamilyAddsPeoplePetsAndUpdatesOccupancy                 | Checks that a family that fits is checked in and occupancy goes up. |
 checkInFamilyReturnsFalseWhenFamilyDoesNotFit                  | Checks that a family bigger than the open space is turned away.     |
 checkInFamilyReturnsFalseWhenFamilyListIsNull                  | Checks that a null family list returns false instead of crashing.   |
 updateCapacityDoesNotAcceptNegativeNumber                      | Checks that a negative capacity is ignored.                         |
 getDistanceFromUserReturnsNegativeOneWhenShelterLocationIsNull | Checks that the distance is -1 when the shelter has no location.    |
 addShelterDoesNotAddSameShelterTwice                           | Checks that the same shelter is not added twice.                    |
 getAvailabilityReturnsFalseWhenShelterIsFull                   | Checks that a full shelter is not available.                        |
--------------------------------------------------------------------------------------------------------------------------------------
*/

public class ShelterTest {
    private Shelter shelter;

    /**
     * Sets up a new Shelter instance before each test.
     */
    @BeforeEach
    public void setUp() {
         shelter = new Shelter(
                UUID.randomUUID(),
                "Test Shelter",
                5,
                new ArrayList<Resource>(),
                new ArrayList<AccommodationType>(),
                null,
                "8:00 AM - 8:00 PM"
        );
    }

    /**
     * Tests that checking in a family adds them to the shelter and updates occupancy.
     */
     @Test
    void checkInFamilyAddsPeoplePetsAndUpdatesOccupancy() {
        ArrayList<String> family = new ArrayList<>();
        family.add("Aisha");
        family.add("John");

        ArrayList<String> pets = new ArrayList<>();
        pets.add("Buddy");

        boolean checkedIn = shelter.checkInFamily(family, pets);

        assertTrue(checkedIn);
        assertEquals(2, shelter.getCurrentOccupancy());
        assertEquals(3, shelter.getAvailableSpaces());
        assertTrue(shelter.getOccupants().contains("Aisha"));
        assertTrue(shelter.getAccompanyingPets().contains("Buddy"));
    }

    /**
     * Tests that checking in a family returns false when the family size exceeds available spaces.
     */
    @Test
    void checkInFamilyReturnsFalseWhenFamilyDoesNotFit() {
        ArrayList<String> family = new ArrayList<>();
        family.add("Person 1");
        family.add("Person 2");
        family.add("Person 3");
        family.add("Person 4");
        family.add("Person 5");
        family.add("Person 6");

        ArrayList<String> pets = new ArrayList<>();

        boolean checkedIn = shelter.checkInFamily(family, pets);

        assertFalse(checkedIn);
        assertEquals(0, shelter.getCurrentOccupancy());
        assertEquals(0, shelter.getOccupants().size());
    }

    /**
     * Tests that checking in a family returns false when the family list is null.
     */
     @Test
    void checkInFamilyReturnsFalseWhenFamilyListIsNull() {
        ArrayList<String> pets = new ArrayList<>();

        boolean checkedIn = shelter.checkInFamily(null, pets);

        assertFalse(checkedIn);
        assertEquals(0, shelter.getCurrentOccupancy());
    }

    /**
     * Tests that updating the capacity does not accept negative numbers.
     */
     @Test
    void updateCapacityDoesNotAcceptNegativeNumber() {
        shelter.updateCapacity(-2);

        assertEquals(5, shelter.getCapacity());
    }

    /**
     * Tests that getting the distance from a user returns -1 when the shelter location is null.
     */
    @Test
    void getDistanceFromUserReturnsNegativeOneWhenShelterLocationIsNull() {
        Location userLocation = new Location(
                "123 Main St", "Columbia", "SC", "29201");

        assertEquals(-1.0, shelter.getDistanceFromUser(userLocation));
    }

    /**
     * Tests that adding a shelter does not add the same shelter twice.
     */
    @Test
    void addShelterDoesNotAddSameShelterTwice() {
        Shelter secondShelter = new Shelter(
                UUID.randomUUID(),
                "Second Shelter",
                10,
                new ArrayList<Resource>(),
                new ArrayList<AccommodationType>(),
                null,
                "9:00 AM - 5:00 PM"
        );

        shelter.addShelter(secondShelter);
        shelter.addShelter(secondShelter);

        assertEquals(1, shelter.getShelters().size());
    }

    /**
     * Tests that getting availability returns false when the shelter is full.
     */
    @Test
    void getAvailabilityReturnsFalseWhenShelterIsFull() {
        shelter.setCurrentOccupancy(5);

        assertFalse(shelter.getAvailability());
    }
}

