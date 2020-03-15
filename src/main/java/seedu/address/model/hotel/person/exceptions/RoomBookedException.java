package seedu.address.model.hotel.person.exceptions;

public class RoomBookedException extends RuntimeException{
    public RoomBookedException() {
        super("Room is already booked");
    }
}
