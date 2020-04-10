package seedu.address.model.hotel.bill.exceptions;

/**
 * Signals that the operation is unable to find the specified bill.
 */
public class BillNotFoundException extends RuntimeException {
    public BillNotFoundException() {
        super("Unable to find this bill");
    }
}
