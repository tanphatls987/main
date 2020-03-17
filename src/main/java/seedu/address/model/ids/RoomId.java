package seedu.address.model.ids;

/**
 *
 */
public class RoomId extends Id {

    private RoomId(String serializedId) {
        super(serializedId);
    }

    /**
     * [generate description]
     * @param  roomName [description]
     * @return          [description]
     */
    public static RoomId generate(String roomName) {
        RoomId result = new RoomId(roomName);
        return result;
    }

    @Override
    public boolean equals(Object oth) {
        if (oth == this) {
            return true;
        }

        if (!(oth instanceof RoomId)) {
            return false;
        }

        RoomId othRoomId = (RoomId) oth;
        return othRoomId.getSerializedId().equals(getSerializedId());
    }
}
