package com.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Test;

/**
-----------------------------------------------------------------------------------------------------------------------------
 TEST                                              | REASONING                                                              |
-----------------------------------------------------------------------------------------------------------------------------
 getInstanceReturnsSameShelterListInstance         | Checks that getInstance always returns the same ShelterList.           |
 getShelterReturnsNullWhenNameIsNull               | Checks that searching for a null name returns null instead of crashing.|
 addShelterWithDetailsAddsNewShelter               | Checks that a new shelter with a unique name is added and can be found.|
 addShelterWithDetailsReturnsFalseForDuplicateName | Checks that a second shelter with the same name is refused.            |
 addShelterObjectDoesNotAddDuplicateShelter        | Checks that adding the same shelter object twice only adds it once.    |
 addShelterObjectDoesNotThrowWhenShelterIsNull     | Checks that adding a null shelter does not crash.                      |
-----------------------------------------------------------------------------------------------------------------------------
*/
public class ShelterListTest {

    /** 
     * Tests that getInstance returns the same ShelterList instance.
     */
    @Test
    void getInstanceReturnsSameShelterListInstance() {
        ShelterList firstList = ShelterList.getInstance();
        ShelterList secondList = ShelterList.getInstance();

        assertSame(firstList, secondList);
    }

    /** 
     * Tests that getShelter returns null when the name is null.
     */
    @Test
    void getShelterReturnsNullWhenNameIsNull() {
        ShelterList shelterList = ShelterList.getInstance();

        assertNull(shelterList.getShelter(null));
    }

    /** 
     * Tests that a new shelter with unique details is added and can be found.
     */
    @Test
    void addShelterWithDetailsAddsNewShelter() {
        ShelterList shelterList = ShelterList.getInstance();
        String uniqueName = "Test Shelter " + UUID.randomUUID();

        boolean added = shelterList.addShelter(
                UUID.randomUUID(),
                uniqueName,
                20,
                new ArrayList<Resource>(),
                new ArrayList<AccommodationType>(),
                null,
                "8:00 AM - 8:00 PM"
        );

        assertTrue(added);
        assertNotNull(shelterList.getShelter(uniqueName));
    }

    /** 
     * Tests that adding a shelter with duplicate details returns false.
     */
    @Test
    void addShelterWithDetailsReturnsFalseForDuplicateName() {
        ShelterList shelterList = ShelterList.getInstance();
        String uniqueName = "Duplicate Shelter " + UUID.randomUUID();

        shelterList.addShelter(
                UUID.randomUUID(),
                uniqueName,
                20,
                new ArrayList<Resource>(),
                new ArrayList<AccommodationType>(),
                null,
                "8:00 AM - 8:00 PM"
        );

        boolean addedAgain = shelterList.addShelter(
                UUID.randomUUID(),
                uniqueName,
                40,
                new ArrayList<Resource>(),
                new ArrayList<AccommodationType>(),
                null,
                "9:00 AM - 5:00 PM"
        );

        assertFalse(addedAgain);
    }

    /** 
     * Tests that adding the same shelter object twice only adds it once.
     */
    @Test
    void addShelterObjectDoesNotAddDuplicateShelter() {
        ShelterList shelterList = ShelterList.getInstance();
        String uniqueName = "Object Shelter " + UUID.randomUUID();

        Shelter shelter = new Shelter(
                UUID.randomUUID(),
                uniqueName,
                15,
                new ArrayList<Resource>(),
                new ArrayList<AccommodationType>(),
                null,
                "8:00 AM - 8:00 PM"
        );

        int sizeBefore = shelterList.getShelters().size();

        shelterList.addShelter(shelter);
        shelterList.addShelter(shelter);

        assertEquals(sizeBefore + 1, shelterList.getShelters().size());
    }

    /** 
     * Tests that adding a null shelter does not crash.
     */
    @Test
    void addShelterObjectDoesNotThrowWhenShelterIsNull() {
        ShelterList shelterList = ShelterList.getInstance();

        assertDoesNotThrow(() -> shelterList.addShelter(null));
    }
}