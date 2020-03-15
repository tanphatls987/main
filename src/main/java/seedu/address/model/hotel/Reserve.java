package seedu.address.model.hotel;

import java.time.LocalDate;

import seedu.address.model.hotel.person.Person;

/**
 * Store reservation for future.
 */
public class Reserve extends Booking {

    /**
     * Create an empty reservation.
     */
    public Reserve(Person payee, LocalDate bookFrom, LocalDate bookTo, Room room) {
        super(payee, bookFrom, bookTo, room);

    }
}
