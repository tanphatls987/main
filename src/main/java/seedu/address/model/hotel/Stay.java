package seedu.address.model.hotel;

import java.time.LocalDate;

import seedu.address.model.hotel.booking.Booking;
import seedu.address.model.hotel.person.Person;

/**
 *
 */
public class Stay extends Booking {

    private String description;
    /**
     * [Stay description]
     * @return [description]
     */
    public Stay(Person payee, Room room, LocalDate from, LocalDate to, String description) {
        super(payee, room, from, to);
        this.description = description;
    }

}
