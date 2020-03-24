package seedu.address.model.hotel;

import java.util.ArrayList;
import java.util.List;
/**
 * Contains a list of chargeable objects that a guest has requested for.
 */
public class Bill {
    private List<Chargeable> charges;

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

