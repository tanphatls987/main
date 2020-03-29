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
     * Sets the {@code tier} of the {@code Room} that we are building.
     */
    public RoomBuilder withTier(String tier) {
        this.roomTier = new Tier(tier);
        return this;
    }

    /**
     * Sets the {@code roomNum} of the {@code Room} that we are building.
     */
    public RoomBuilder withRoomNum(String roomNum) {
        this.roomNum = roomNum;
        return this;
    }

    /**
     * Build a default room.
     */
    public Room build() {
        return new Room(roomNum, roomTier);
    }

}
