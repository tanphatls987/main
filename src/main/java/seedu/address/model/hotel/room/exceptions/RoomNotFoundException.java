package seedu.address.model.hotel.room.exceptions;

/**
 * Signals that the operation is unable to find the specified room.
 */
public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException() {
        super("Unable to find this room");
    }
}

