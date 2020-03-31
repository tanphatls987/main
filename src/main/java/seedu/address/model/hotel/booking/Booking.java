package seedu.address.model.hotel.booking;

import java.time.LocalDateTime;

import seedu.address.model.hotel.person.Person;
import seedu.address.model.hotel.room.Room;
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
    public Booking(Person payee, Room room, LocalDateTime from, LocalDateTime to)
        throws InvalidTimeFrameException {
        this.payee = payee;
        this.room = room;
        this.bookDuration = new TimeFrame(from, to);
    }

    /**
     * Check if 2 booking clash each other.
     * @param oth other object
     * @return true if the object clash each other
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
     * @param room Room object to be checked
     * @param duration start date and end date
     * @return true if there exist clash with other
     */
    public boolean isClash(Room room, TimeFrame duration) {
        if (this.room != room) {
            return false;
        }
        return bookDuration.isClash(duration);
    }

    public boolean isCorrectRoom(Room room) {
        return this.room.isSameRoom(room);
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
