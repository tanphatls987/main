package seedu.address.model.hotel.booking;

import java.time.LocalDate;

import seedu.address.model.hotel.Room;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.timeframe.TimeFrame;
import seedu.address.model.timeframe.exception.InvalidTimeFrameException;

/**
 * Store booking information.
 */
public class Booking {
    protected Person payee;
    protected Room room;
    protected TimeFrame bookDuration;


    /**
     * Create a booking with full detail.
     * @param payee
     * @param room
     * @param bookDuration
     */
    public Booking(Person payee, Room room, TimeFrame bookDuration) {
        this.payee = payee;
        this.room = room;
        this.bookDuration = bookDuration;
    }

    /**
     * Create a booking with full detail.
     * @param payee
     * @param room
     * @param from
     * @param to
     * @throws InvalidTimeFrameException
     */
    public Booking(Person payee, Room room, LocalDate from, LocalDate to)
        throws InvalidTimeFrameException {
        this.payee = payee;
        this.room = room;
        this.bookDuration = new TimeFrame(from, to);
    }

    /**
     * Check if 2 booking clash each other
     * @param oth
     * @return
     */
    public boolean isClash(Booking oth) {
        ///different room
        if (this.room != oth.room) {
            return false;
        }
        return bookDuration.isClash(oth.bookDuration);
    }

    /**
     * Check if this booking clash with a room during a period of time.
     * @param room
     * @param duration
     * @return
     */
    public boolean isClash(Room room, TimeFrame duration) {
        if (this.room != room) {
            return false;
        }
        return bookDuration.isClash(duration);
    }

    @Override
    public boolean equals(Object oth) {
        if (oth == this) {
            return true;
        }
        if (!(oth instanceof Booking)) {
            return false;
        }
        Booking othBooking = (Booking) oth;
        return othBooking.payee.equals(payee)
            && othBooking.bookDuration.equals(bookDuration)
            && othBooking.room.equals(room);
    }

}
