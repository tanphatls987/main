package seedu.address.model.hotel.bill.exceptions;

/**
 * Signals that the operation is unable to find the specified service.
 */
public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException() {
        super("Unable to find this service");
    }
}
