package seedu.address.model.hotel;

import seedu.address.model.hotel.bill.RoomCost;
import seedu.address.model.ids.RoomId;

/**
 * Store room information.
 */
public class Room {
    private Tier roomTier;
    private String roomNum;
    private RoomId roomId;
    private RoomCost roomCost;

    /**
     * Create a room with name, tier & cost.
     * @param roomNum
     * @param roomTier
     */
    public Room(String roomNum, Tier roomTier, RoomCost roomCost) {
        this.roomTier = roomTier;
        this.roomNum = roomNum;
        this.roomId = RoomId.generate(roomNum);
        this.roomCost = roomCost;
    }

    /**
     * Construct a room with just a name, using default tier and an unset cost.
     * @param roomNum
     */
    public Room(String roomNum) {
        this(roomNum, new Tier(), new RoomCost());
    }

    /**
     * RoomNum getter.
     * @return
     */
    public String getRoomNum() {
        return this.roomNum;
    }

    /**
     * Check if this room has some certain name.
     * @param roomNum
     * @return comparison
     */
    public boolean hasName(String roomNum) {
        return this.roomNum.equals(roomNum);
    }

    /**
     *
     * @return this room's name
     */
    public String getName() {
        return this.roomNum;
    }

    /**
     * Returns the {@code RoomCost} for the room.
     */
    public RoomCost getRoomCost() {
        return roomCost;
    }

    /**
     * Sets the cost for this room.
     */
    public void setCost(RoomCost roomCost) {
        this.roomCost = roomCost;
    }
}
