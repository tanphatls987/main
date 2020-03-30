package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.model.hotel.room.Room;

/**
 * A container of typical rooms for testing.
 */
public class TypicalRooms {
    public static final Room BEST_ROOM = new RoomBuilder().withTier("GOLD").withRoomNum("100").build();
    public static final Room WORST_ROOM = new RoomBuilder().withTier("BRONZE").withRoomNum("013").build();

    /**
     * get list of typical rooms.
     */
    public static ArrayList<Room> getTypicalRooms() {
        return new ArrayList<>(Arrays.asList(BEST_ROOM, WORST_ROOM));
    }

}
