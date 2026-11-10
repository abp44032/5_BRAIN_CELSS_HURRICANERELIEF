package com.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** HURRICANE TESTS
 -------------------------------------------------------------------------------------
 * TEST                                | REASONING
 * constructor sets fields             | checks that the id, name, zip codes, status, steps and supplies are stored
 * singleton returns same instance     | checks that getInstance always returns the same hurricane
 * info shows name and status          | checks that the first line has the hurricane name and status
 * info shows zip codes                | checks that the affected zip codes are listed, separated by commas
 * info numbers evacuation steps       | checks that each step is listed with its number in order
 * info lists emergency supplies       | checks that each supply name is listed
 * info with no steps or supplies      | checks that empty lists still produce the section headings
 * info with null zip codes            | checks that a null zip code list throws an exception
 * viewing emergency supplies          | checks that the supplies list is returned
 * adding a hurricane                  | checks that a new hurricane is added to the list
 * adding a null hurricane             | checks that a null hurricane is ignored
 * adding a duplicate hurricane        | checks that the same hurricane is not added twice
 -------------------------------------------------------------------------------------
 */
class HurricaneTest {

    private Hurricane hurricane;

    /*
    * builds a sample hurricane to use for each test
    */
    @BeforeEach
    void setUp() {
        ArrayList<String> zipCodes = new ArrayList<>();
        zipCodes.add("29201");
        zipCodes.add("29203");

        ArrayList<String> steps = new ArrayList<>();
        steps.add("Pack an emergency bag");
        steps.add("Leave the area");

        ArrayList<Resource> supplies = new ArrayList<>();
        supplies.add(new Resource(1, "Water", 10));
        supplies.add(new Resource(2, "Flashlight", 2));

        hurricane = new Hurricane(
                UUID.fromString("55555555-5555-5555-5555-555555555555"),
                "Helene", zipCodes, HurricaneStatus.ACTIVE, steps, supplies);
    }

    /*
    * constructor sets fields
    */
    @Test
    void testConstructorSetsFields() {
        assertEquals(UUID.fromString("55555555-5555-5555-5555-555555555555"), hurricane.getId());
        assertEquals("Helene", hurricane.getName());
        assertEquals(2, hurricane.getZipCodes().size());
        assertEquals(HurricaneStatus.ACTIVE, hurricane.getStatus());
        assertEquals(2, hurricane.getEvacuationSteps().size());
        assertEquals(2, hurricane.getEmergencySupplies().size());
    }

    /*
    * singleton returns same instance
    */
    @Test
    void testSingletonReturnsSameInstance() {
        assertSame(Hurricane.getInstance(), Hurricane.getInstance());
    }

    /*
    * info shows name and status
    */
    @Test
    void testInfoShowsNameAndStatus() {
        assertTrue(hurricane.viewHurricaneInfo().startsWith("Hurricane Helene (ACTIVE)\n"));
    }

    /*
    * info shows zip codes
    */
    @Test
    void testInfoShowsZipCodes() {
        assertTrue(hurricane.viewHurricaneInfo().contains("Affected zip codes: 29201, 29203\n"));
    }

    /*
    * info numbers evacuation steps
    */
    @Test
    void testInfoNumbersEvacuationSteps() {
        String info = hurricane.viewHurricaneInfo();

        assertTrue(info.contains("  1. Pack an emergency bag\n"));
        assertTrue(info.contains("  2. Leave the area\n"));
    }

    /*
    * info lists emergency supplies
    */
    @Test
    void testInfoListsEmergencySupplies() {
        String info = hurricane.viewHurricaneInfo();

        assertTrue(info.contains("  - Water\n"));
        assertTrue(info.contains("  - Flashlight\n"));
    }

    /*
    * info with no steps or supplies
    */
    @Test
    void testInfoWithNoStepsOrSupplies() {
        Hurricane empty = new Hurricane(
                UUID.randomUUID(), "Ian", new ArrayList<String>(),
                HurricaneStatus.FORMING, new ArrayList<String>(),
                new ArrayList<Resource>());

        String info = empty.viewHurricaneInfo();

        assertTrue(info.contains("Evacuation steps:\n"));
        assertTrue(info.contains("Emergency supplies:\n"));
        assertFalse(info.contains("1."));
        assertFalse(info.contains("  - "));
    }

    /*
    * info with null zip codes
    */
    @Test
    void testInfoWithNullZipCodesThrowsException() {
        Hurricane noZips = new Hurricane(
                UUID.randomUUID(), "Ian", null, HurricaneStatus.FORMING,
                new ArrayList<String>(), new ArrayList<Resource>());

        assertThrows(NullPointerException.class, () -> noZips.viewHurricaneInfo());
    }

    /*
    * viewing emergency supplies
    */
    @Test
    void testViewingEmergencySupplies() {
        ArrayList<Resource> supplies = hurricane.viewEmergencySupplies();

        assertEquals(2, supplies.size());
        assertEquals("Water", supplies.get(0).getName());
    }

    /*
    * adding a hurricane
    */
    @Test
    void testAddingHurricane() {
        Hurricane other = new Hurricane(
                UUID.randomUUID(), "Ian", new ArrayList<String>(),
                HurricaneStatus.FORMING, new ArrayList<String>(),
                new ArrayList<Resource>());

        hurricane.addHurricane(other);

        assertEquals(1, hurricane.getHurricane().size());
        assertSame(other, hurricane.getHurricane().get(0));
    }

    /*
    * adding a null hurricane
    */
    @Test
    void testAddingNullHurricane() {
        hurricane.addHurricane(null);

        assertTrue(hurricane.getHurricane().isEmpty());
    }

    /*
    * adding a duplicate hurricane
    */
    @Test
    void testAddingDuplicateHurricane() {
        Hurricane other = new Hurricane(
                UUID.randomUUID(), "Ian", new ArrayList<String>(),
                HurricaneStatus.FORMING, new ArrayList<String>(),
                new ArrayList<Resource>());

        hurricane.addHurricane(other);
        hurricane.addHurricane(other);

        assertEquals(1, hurricane.getHurricane().size());
    }
}