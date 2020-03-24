package seedu.address.testutil;

import seedu.address.model.hotel.Room;
import seedu.address.model.hotel.Tier;
import seedu.address.model.hotel.bill.RoomCost;
import seedu.address.model.ids.RoomId;


/***
 * A utility class to help build rooms
 */
public class RoomBuilder {
    public static final Tier DEFAULT_TIER = new Tier();
    public static final String DEFAULT_ROOMNUM = "1";
    public static final RoomId DEFAULT_ROOMID = RoomId.generate(DEFAULT_ROOMNUM);
    public static final RoomCost DEFAULT_ROOMCOST = new RoomCost();

    private Tier roomTier;
    private String roomNum;
    private RoomId roomId;
    private RoomCost roomCost;

    /**
     * Create new room builder.
     */
    public RoomBuilder() {
        roomTier = DEFAULT_TIER;
        roomNum = DEFAULT_ROOMNUM;
        roomId = DEFAULT_ROOMID;
        roomCost = DEFAULT_ROOMCOST;
    }

    /**
     * Build a default room.
     */
    public Room build() {
        return new Room(roomNum, roomTier, roomCost);
    }

    /**
     * Build a room with name.
     */
    public Room withRoomNum(String roomNum) {
        return new Room(roomNum);
    }

}
