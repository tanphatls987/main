package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Hotel;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.hotel.room.Tier;

/**
 * A container of typical rooms for testing.
 */
public class TypicalRooms {
    public static final Room BEST_ROOM = new RoomBuilder().build("009", new Tier("bronze"));
    public static final Room WORST_ROOM = new RoomBuilder().build("010", new Tier("bronze"));

    /**
     * get list of typical rooms.
     */
    public static List<Room> getTypicalRooms() {
        return new ArrayList<>(Arrays.asList(BEST_ROOM, WORST_ROOM));
    }

    /**
     * Returns an {@code Hotel} with all the typical rooms.
     */
    public static Hotel getTypicalHotel() {
        Hotel h = new Hotel();
        for (Room room : getTypicalRooms()) {
            h.addRoom(room);
        }
        return h;
    }
}
