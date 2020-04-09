package seedu.address.model.hotel.booking.exception;

/**
 * Signals that the operation is unable to find the specified booking.
 */
public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException() {
        super("Unable to find this booking");
    }
}
