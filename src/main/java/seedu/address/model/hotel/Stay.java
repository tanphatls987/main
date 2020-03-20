package seedu.address.model.hotel;

import java.time.LocalDateTime;

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
    public Stay(Person payee, Room room, LocalDateTime from, LocalDateTime to, String description) {
        super(payee, room, from, to);
        this.description = description;
    }

}
