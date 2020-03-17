package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.hotel.Room;

/**
 * A container of typical rooms for testing.
 */
public class TypicalRooms {
    public static final Room BEST_ROOM = new RoomBuilder().withRoomNum("12F");
    public static final Room WORST_ROOM = new RoomBuilder().withRoomNum("13F");

    /**
     * get list of typical rooms.
     */
    public static List<Room> getTypicalRooms() {
        return new ArrayList<>(Arrays.asList(BEST_ROOM, WORST_ROOM));
    }
}
