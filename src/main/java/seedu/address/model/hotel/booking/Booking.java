package seedu.address.model.hotel.booking;

import java.time.LocalDateTime;
import java.util.UUID;

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
    protected UUID uuid;


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
        this.uuid = UUID.randomUUID();
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
        uuid = UUID.randomUUID();
    }

    /**
     * Create a booking with full detail.
     * @param payee
     * @param room
     * @param from
     * @param to
     * @param uuid
     * @throws InvalidTimeFrameException
     */
    public Booking(Person payee, Room room, LocalDateTime from, LocalDateTime to, String uuid)
        throws InvalidTimeFrameException {
        this.payee = payee;
        this.room = room;
        this.bookDuration = new TimeFrame(from, to);
        this.uuid = UUID.fromString(uuid);
    }

    /**
     * Check if 2 booking clash each other.
     * @param oth other object
     * @return true if the object clash each other
     */
    public boolean isClash(Booking oth) {
        ///different room
        if (!this.room.getRoomNum().equals(oth.room.getRoomNum())) {
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
        if (!this.room.getRoomNum().equals(room.getRoomNum())) {
            return false;
        }
        return bookDuration.isClash(duration);
    }

    /**
     * check if other booking is inside current booking timeframe.
     * @param oth other booking
     * @return true if other booking is within the current booking timeframe.
     */
    public boolean isInside(Booking oth) {
        if (!this.room.getRoomNum().equals(room.getRoomNum())) {
            return false;
        }
        return bookDuration.isInside(oth.bookDuration);
    }

    /**
     * Check if this booking clash with a room during a period of time.
     * @param room Room object to be checked
     * @return true if there exist clash with other
     */
    public boolean isCurrentlyClash(Room room) {
        if (!this.room.getRoomNum().equals(room.getRoomNum())) {
            return false;
        }
        return bookDuration.isInside(LocalDateTime.now());
    }

    public boolean isCorrectRoom(Room room) {
        return this.room.isSameRoom(room);
    }

    /***
     * Checks if booking is same as @code booking
     * @param booking
     */
    public boolean isSameBooking(Booking booking) {
        if (booking == this) {
            return true;
        }
        return booking != null
                && booking.getRoom().equals(getRoom())
                && booking.getBookDuration().equals(getBookDuration());
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

    public Room getRoom() {
        return room;
    }

    public Person getPayee() {
        return payee;
    }

    public LocalDateTime getTimeFrom() {
        return bookDuration.getStartTime();
    }

    public LocalDateTime getTimeTo() {
        return bookDuration.getEndTime();
    }

    public TimeFrame getBookDuration() {
        return bookDuration;
    }

    /**
     * return if the booking has the same ID.
     * Could be checked by the first 8 characters as well.
     * @param booking
     * @return
     */
    public boolean isMatchId(String booking) {
        int len = booking.length();
        return uuid.toString().substring(0, len).equals(booking);
    }

    public String getReadableBookingId() {
        return uuid.toString().substring(0, 8);
    }

    public String getBookingId() {
        return uuid.toString();
    }
}
