package seedu.address.testutil;

import seedu.address.model.Hotel;
import seedu.address.model.hotel.room.Room;

/**
 * Container for typical hotel for testing
 * */
public class TypicalHotel {

    /**
     * Returns an {@code Hotel} with all the typical rooms.
     */
    public static Hotel getTypicalHotel() {
        Hotel hotel = new Hotel();
        for (Room room : TypicalRooms.getTypicalRooms()) {
            hotel.addRoom(room);
        }
        return hotel;
    }
}
