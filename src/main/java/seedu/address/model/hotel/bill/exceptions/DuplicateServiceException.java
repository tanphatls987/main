package seedu.address.model.hotel.bill.exceptions;

/**
 * Signals that the operation will result in duplicate Services (Servics are considered duplicates if they have the same
 * serviceId).
 */
public class DuplicateServiceException extends RuntimeException {
    public DuplicateServiceException() {
        super("Operation would result in duplicate services");
    }
}
