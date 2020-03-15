package seedu.address.model;

import java.util.ArrayList;

import seedu.address.model.hotel.Room;

/**
 * Storing hotel's details: rooms, booking
 */
public class Hotel {
    private final ArrayList<Room> roomList;

    public Hotel() {
        roomList = new ArrayList<>();
    }

    /**
     * Get the room list.
     * @return a room list.
     */
    public ArrayList<Room> getRoomList() {
        return this.roomList;
    }

    /**
     * check room num exists.
     * @param roomNum
     * @return
     */
    public boolean hasRoom(String roomNum) {
        for (Room room: roomList) {
            if (room.hasName(roomNum)) {
                return true;
            }
        }
        return false;
    }

    /**
     * add a new room
     * @param roomNum
     */
    public void addRoom(String roomNum) {
        Room newRoom = new Room(roomNum);
        roomList.add(newRoom);
    }
}
