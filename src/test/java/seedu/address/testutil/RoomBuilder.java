package seedu.address.testutil;

import seedu.address.model.hotel.room.Room;
import seedu.address.model.hotel.room.Tier;
import seedu.address.model.ids.RoomId;


/***
 * A utility class to help build rooms
 */
public class RoomBuilder {
    public static final Tier DEFAULT_TIER = new Tier();
    public static final String DEFAULT_ROOMNUM = "001";
    public static final RoomId DEFAULT_ROOMID = RoomId.generate(DEFAULT_ROOMNUM);

    private Tier roomTier;
    private String roomNum;
    private RoomId roomId;

    /**
     * Create new room builder.
     */
    public RoomBuilder() {
        roomTier = DEFAULT_TIER;
        roomNum = DEFAULT_ROOMNUM;
        roomId = DEFAULT_ROOMID;
    }

    /**
     * Build a default room.
     */
    public Room build() {
        return new Room(roomNum, roomTier);
    }

    /**
     * Build a room with name and tier.
     */
    public Room build(String roomNum, Tier tier) {
        return new Room(roomNum, tier);
    }

}
