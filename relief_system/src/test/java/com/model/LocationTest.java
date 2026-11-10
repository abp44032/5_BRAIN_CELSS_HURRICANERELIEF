package com.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * -----------------------------------------------------------------------------------------------------------------------------
 * TEST                                                     | REASONING
 * -----------------------------------------------------------------------------------------------------------------------------
 * getDistanceFromReturnsNegativeOneWhenOtherIsNull         | Checks that a null location returns -1.
 * getDistanceFromReturnsNegativeOneWhenCoordinatesMissing  | Checks that unavailable coordinates return -1.
 * toStringIncludesAddressDetails                           | Checks that the string representation includes the address.
 * -----------------------------------------------------------------------------------------------------------------------------
 */
public class LocationTest {

    /**
     * Tests that getDistanceFrom returns -1 when the other location is null.
     */
    @Test
    public void getDistanceFromReturnsNegativeOneWhenOtherIsNull() {
        Location location = new Location(
                "123 Main St", "Columbia", "SC", "29201");

        assertEquals(-1.0, location.getDistanceFrom(null), 0.0);
    }

    /**
     * Tests that getDistanceFrom returns -1 when coordinates cannot be retrieved.
     * This avoids requiring a MAP_API_KEY.
     */
    @Test
    public void getDistanceFromReturnsNegativeOneWhenCoordinatesMissing() {
        Location location = new Location(
                null, null, null, null);
        Location other = new Location(
                null, null, null, null);

        assertEquals(-1.0, location.getDistanceFrom(other), 0.0);
    }

    /**
     * Tests that toString includes the location's address details.
     * The map package may report missing coordinates when no MAP_API_KEY is set.
     */
    @Test
    public void toStringIncludesAddressDetails() {
        Location location = new Location(
                "123 Main St", "Columbia", "SC", "29201");

        String result = location.toString();

        assertTrue(result.contains("123 Main St"));
        assertTrue(result.contains("Columbia"));
        assertTrue(result.contains("SC"));
        assertTrue(result.contains("29201"));
    }
}
