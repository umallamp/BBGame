package edu.asu.wpcarey.bankbanks;

import edu.asu.wpcarey.bbutils.BloodOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HospitalBloodBank extends BloodBank {

    private static final Logger LOG = LogManager.getLogger(HospitalBloodBank.class);

    @Override
    public boolean placeOrder(BloodOrder bloodOrder) {
        return false;
    }
}
