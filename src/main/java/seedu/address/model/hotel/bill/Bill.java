package seedu.address.model.hotel.bill;

import java.util.ArrayList;
/**
 * Contains a list of chargeable objects that a guest has requested for.
 */
public class Bill {
    private ArrayList<Chargeable> charges;

    /**
     * Creates an empty {@code bill}.
     */
    public Bill() {
        this.charges = new ArrayList<>();
    }

    /**
     * Returns true if there is no chargeable objects in the bill.
     */
    public boolean isEmpty() {
        return charges.isEmpty();
    }
}

