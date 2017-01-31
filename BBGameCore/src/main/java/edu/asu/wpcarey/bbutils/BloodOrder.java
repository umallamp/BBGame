package edu.asu.wpcarey.bbutils;

import edu.asu.wpcarey.bloodtypes.BloodUnit;

public class BloodOrder {

    private final BloodUnit redBloodUnits;
    private final BloodUnit wholeBloodUnits;

    public BloodOrder(BloodUnit redBloodUnits, BloodUnit wholeBloodUnits) {
        this.redBloodUnits = redBloodUnits;
        this.wholeBloodUnits = wholeBloodUnits;
    }

    public BloodUnit getRedBloodUnits() {
        return redBloodUnits;
    }

    public BloodUnit getWholeBloodUnits() {
        return wholeBloodUnits;
    }
}
