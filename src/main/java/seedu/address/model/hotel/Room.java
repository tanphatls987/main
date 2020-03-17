package seedu.address.model.hotel;

import seedu.address.model.ids.RoomId;

/**
 * Store room information.
 */
public class Room {
    private Tier roomTier;
    private String roomNum;
    private RoomId roomId;

    /**
     * Create a room with name and tier.
     * @param roomNum
     * @param roomTier
     */
    public Room(String roomNum, Tier roomTier) {
        this.roomTier = roomTier;
        this.roomNum = roomNum;
        this.roomId = RoomId.generate(roomNum);
    }

    /**
     * RoomNum getter.
     * @return
     */
    public String getRoomNum() {
        return this.roomNum;
    }


}
