package seedu.address.model.ids;

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
}