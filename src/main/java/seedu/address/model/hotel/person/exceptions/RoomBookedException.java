package seedu.address.model.hotel.person.exceptions;

/**
 * Exception for if room has already been booked
 * */
public class RoomBookedException extends RuntimeException {
    public RoomBookedException() {
        super("Room is already booked");
    }
}
