package seedu.address.model.hotel;

import java.time.LocalDate;

import seedu.address.model.hotel.person.Person;

/**
 * Store booking information.
 */
public class Booking {
    protected Person payee;
    protected LocalDate bookFrom;
    protected LocalDate bookTo;
    protected Room room;


    /**
     * Create an empty room with no detail.
     */
    public Booking(Person payee, LocalDate bookFrom, LocalDate bookTo, Room room) {
        this.payee = payee;
        this.bookFrom = bookFrom;
        this.bookTo = bookTo;
        this.room = room;
    }

    public Booking() {
    }
}
