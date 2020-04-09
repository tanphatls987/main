package seedu.address.model.hotel.room;

import seedu.address.model.hotel.Stay;
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
    private Stay stay;

    /**
     * Create a room with name, tier & cost.
     * Create a room with name and tier.
     *
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
     * Construct a room with just a name and a default tier
     * @param roomNum
     */
    public Room(String roomNum) {
        this(roomNum, new Tier(), new RoomCost());
    }

    /**
     * Construct a room with attributes of @param other
     */
    public Room(Room room) {
        this.roomTier = room.getTier();
        this.roomNum = room.getRoomNum();
        this.roomId = room.getRoomId();
        this.roomCost = room.getRoomCost();
    }

    /**
     * RoomNum getter.
     *
     * @return
     */
    public String getRoomNum() {
        return this.roomNum;
    }


    /**
     * RoomId getter.
     * @return roomId
     */
    public RoomId getRoomId() {
        return this.roomId;
    }

    /**
     * Check if this room has some certain name.
     *
     * @param roomNum
     * @return comparison
     */
    public boolean hasName(String roomNum) {
        return this.roomNum.equals(roomNum);
    }

    /**
     * Check if this room has non default tier or not
     */
    public boolean hasNonDefaultTier() {
        return !this.roomTier.isDefaultTier();
    }

    /**
     * @return this room's name
     */
    public String getName() {
        return this.roomNum;
    }

    public Tier getTier() {
        return this.roomTier;
    }

    /***
     * Checks if room is same as @code room
     * @param room
     */
    public boolean isSameRoom(Room room) {
        if (room == this) {
            return true;
        }

        return room != null
                && room.getRoomNum().equals(getRoomNum());
    }

    @Override
    public String toString() {
        return "Room { "
                + "roomTier=" + roomTier.toString()
                + ", roomNum='" + roomNum + '\''
                + ", roomId=" + roomId
                + '}';
    }

    /**
     * set tier for this room
     */
    public void setTier(Tier tier) {
        this.roomTier = tier;
    }

    /**
     * Sets the stay for this room
     */
    public void setStay(Stay stay) {
        this.stay = stay;
    }

    /**
     * Gets the current stay of the room
     */
    public Stay getStay() {
        return stay;
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
