package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.hotel.bill.Bill;

/**
 * Unmodifiable view of a bookkeeper
 */
public interface ReadOnlyBookKeeper {

    /**
     * Returns an unmodifiable view of the bill list.
     * This list will not contain any duplicate bills.
     */
    ObservableList<Bill> getBillList();
}
