package edu.asu.wpcarey.bbutils;

import edu.asu.wpcarey.bloodtypes.BloodUnit;
import edu.asu.wpcarey.bloodtypes.RedBloodUnit;
import edu.asu.wpcarey.exceptions.BBCoreException;
import edu.asu.wpcarey.exceptions.InvalidInputException;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BloodInventoryTest {

    private static BloodInventory inventory;

    @BeforeClass
    public static void setupBloodInventory() throws BBCoreException {
        inventory = new BloodInventory(10, 3);
    }

    @Test(expected = BBCoreException.class)
    public void testInvalidBloodInventory() throws BBCoreException {
        BloodInventory inventory = new BloodInventory(0, 0);
    }

    @Test(expected = InvalidInputException.class)
    public void testAddEmptyInventory() throws InvalidInputException {
        inventory.addToInventory(null);
    }

    @Test
    public void testAddToInventory() throws InvalidInputException {
        BloodUnit bloodUnit = new RedBloodUnit(10, 1);
        inventory.addToInventory(bloodUnit);
        assertEquals(inventory.getTotalInventory(), bloodUnit.getQuantity());
        assertEquals(bloodUnit.getQuantity(), inventory.getInventoryWithLife(1));
    }

    @Test
    public void testAddBloodWithDifferentLife() throws BBCoreException, InvalidInputException {
        BloodInventory inventory = new BloodInventory(10, 3);
        BloodUnit bloodUnitOne = new RedBloodUnit(5, 1);
        BloodUnit bloodUnitTwo = new RedBloodUnit(5, 2);
        inventory.addToInventory(bloodUnitOne);
        inventory.addToInventory(bloodUnitTwo);
        assertEquals(15, inventory.getInventoryWithLife(1));
        assertEquals(bloodUnitTwo.getQuantity(), inventory.getInventoryWithLife(2));
        assertEquals(20, inventory.getTotalInventory());
    }

    @Test
    public void testRemoveFromInventory() {
        inventory.removeFromInventory(10);
        assertEquals(inventory.getTotalInventory(), 0);
    }
}
