package seedu.address.model.hotel.bill.exceptions;

/**
 * Signals that the operation will result in duplicate Bills (Bills are considered duplicates if they have the same
 * personId & roomId).
 */
public class DuplicateBillException extends RuntimeException {
    public DuplicateBillException() {
        super("Operation would result in duplicate bills");
    }
}
