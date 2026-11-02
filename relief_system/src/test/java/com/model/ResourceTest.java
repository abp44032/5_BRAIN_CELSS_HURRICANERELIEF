package com.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * -----------------------------------------------------------------------------------------------------------------------------
 * TEST                                         | REASONING                                                                    |                                 
 * -----------------------------------------------------------------------------------------------------------------------------
 * updateQuantityChangesQuantity                | Checks that a non-negative quantity updates the resource.                    |
 * updateQuantityDoesNotAcceptNegativeQuantity  | Checks that a negative quantity is ignored.                                  |
 * toStringReturnsNameAndQuantity               | Checks that the resource string contains its name and quantity.              |
 * -----------------------------------------------------------------------------------------------------------------------------
 */
public class ResourceTest {

    /**
     * Tests that updateQuantity changes the quantity when the new value is non-negative.
     */
    @Test
    public void updateQuantityChangesQuantity() {
        Resource resource = new Resource(1, "Water", 5);

        resource.updateQuantity(12);

        assertEquals(12, resource.getQuantity());
    }

    /**
     * Tests that updateQuantity does not change the quantity when the new value is negative.
     */
    @Test
    public void updateQuantityDoesNotAcceptNegativeQuantity() {
        Resource resource = new Resource(1, "Water", 5);

        resource.updateQuantity(-3);

        assertEquals(5, resource.getQuantity());
    }

    /**
     * Tests that toString returns the resource name and quantity in the expected format.
     */
    @Test
    public void toStringReturnsNameAndQuantity() {
        Resource resource = new Resource(1, "Water", 5);

        assertEquals("Water x5", resource.toString());
    }
}
