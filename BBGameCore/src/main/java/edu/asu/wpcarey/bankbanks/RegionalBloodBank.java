package edu.asu.wpcarey.bankbanks;

import edu.asu.wpcarey.bbutils.BloodInventory;
import edu.asu.wpcarey.bbutils.BloodOrder;
import edu.asu.wpcarey.exceptions.BBCoreException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegionalBloodBank extends BloodBank {

    private static final Logger LOG = LogManager.getLogger(RegionalBloodBank.class);
    private BloodInventory inventory;

    public RegionalBloodBank (int initialInventory, int maxLifeInDays) throws BBCoreException {
        inventory = new BloodInventory(initialInventory, maxLifeInDays);
    }

    @Override
    public boolean placeOrder(BloodOrder bloodOrder) {
        return false;
    }
}
