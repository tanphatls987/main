package seedu.address.model.hotel;

import java.time.LocalDateTime;

import seedu.address.model.hotel.booking.Booking;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.hotel.room.Room;

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

    public String getDescription() {
        return description;
    }
}
