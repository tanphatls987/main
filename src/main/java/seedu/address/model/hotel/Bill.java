package seedu.address.model.hotel;

import java.util.ArrayList;
import java.util.List;
/**
 * Contains information about bills.
 */
public class Bill {
    private List<Chargeable> charges;

    /**
     * [Bill description]
     * @return [description]
     */
    public Bill() {
        this.charges = new ArrayList<>();
    }
}

