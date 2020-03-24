package seedu.address.model.hotel.room;

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
     * Construct a room with just a name and a default tier
     * @param roomNum
     */
    public Room(String roomNum) {
        this(roomNum, new Tier());
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

    public RoomId getRoomId() {
        return this.roomId;
    }

    @Override
    public String toString() {
        return "Room{"
                + "roomTier=" + roomTier.toString()
                + ", roomNum='" + roomNum + '\''
                + ", roomId=" + roomId
                + '}';
    }
}
