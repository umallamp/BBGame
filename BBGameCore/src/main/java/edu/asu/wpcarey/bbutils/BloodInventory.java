package edu.asu.wpcarey.bbutils;

import edu.asu.wpcarey.bloodtypes.BloodUnit;
import edu.asu.wpcarey.exceptions.BBCoreException;
import edu.asu.wpcarey.exceptions.InvalidInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This class represents the basic abstraction for the blood inventory of various blood types.
 */

public class BloodInventory {

    private static final Logger LOG = LogManager.getLogger(BloodInventory.class);
    private static final int INITIAL_INVENTORY_FOR_LIFE = 0;
    private Queue<Integer> bloodInventory;

    public BloodInventory(int initialInventory, int maxLifeInDays) throws BBCoreException {
        if (maxLifeInDays < 1) {
            throw new BBCoreException("Life of blood should be at least 1 day.");
        }
        initializeInventory(initialInventory, maxLifeInDays);
    }

    // Function to mark the inventory with initial quantity provided with life of a day
    // and initialize the other lives with zero.
    private void initializeInventory(int initialInventory, int maxLifeInDays) {
        bloodInventory = new LinkedList<>();
        bloodInventory.offer(initialInventory);
        for (int i = 1; i < maxLifeInDays; i++) {
            bloodInventory.offer(INITIAL_INVENTORY_FOR_LIFE);
        }
    }

    // This function adds the blood quantity to current inventory to the same life
    // of the current blood supply.
    public void addToInventory(BloodUnit bloodUnit) throws InvalidInputException {
        if (bloodUnit == null) {
            throw new InvalidInputException("Blood units to be stored cannot be empty.");
        }

        for (int ageIndex = 0; ageIndex < bloodInventory.size(); ageIndex++) {
            Integer inventory = bloodInventory.poll();
            if (ageIndex == bloodUnit.getLifeInDays() - 1) {
                inventory += bloodUnit.getQuantity();
            }
            bloodInventory.offer(inventory);
        }
    }

    // This function fulfill the demand from the available inventory and
    // returns the number of units unfulfilled.
    public int removeFromInventory(int quantity) {
        while (!bloodInventory.isEmpty() && quantity > 0) {
            quantity -= bloodInventory.poll();
        }
        return quantity;
    }

    // This function returns the total unexpired inventory for the blood bank.
    public int getTotalInventory() {
        return bloodInventory.stream().mapToInt(i -> i).sum();
    }

    // This function returns the total unexpired inventory with given life.
    public int getInventoryWithLife(int lifeInDays) {
        int inventoryWithLife = -1;
        for (int ageIndex = 0; ageIndex < bloodInventory.size(); ageIndex++) {
            Integer inventory = bloodInventory.poll();
            if (ageIndex == lifeInDays - 1) {
                inventoryWithLife = inventory;
            }
            bloodInventory.offer(inventory);
        }
        return inventoryWithLife;
    }
}
