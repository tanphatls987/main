package seedu.address.model.hotel;

import java.time.LocalDateTime;

import seedu.address.model.hotel.person.Person;

/**
 * Store booking information.
 */
public class Booking {
    private Person payee;
    private LocalDateTime bookFrom;
    private LocalDateTime bookTo;
    private Room room;


    /**
     * Create an empty room with no detail.
     */
    public Booking() {
    }
}
