package seedu.address.model.hotel;

import java.time.LocalDateTime;

import seedu.address.model.hotel.booking.Booking;
import seedu.address.model.hotel.person.Person;

/**
 * Store reservation for future.
 */
public class Reserve extends Booking {

    /**
     * Create an empty reservation.
     */
    public Reserve(Person payee, Room room, LocalDateTime bookFrom, LocalDateTime bookTo) {
        super(payee, room, bookFrom, bookTo);
    }
}
