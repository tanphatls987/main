package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.address.model.hotel.Room;

/**
 * Storing hotel's details: rooms, booking
 */
public class Hotel implements ReadOnlyHotel {
    private final ArrayList<Room> roomList;

    /**
     * Create new empty hotel.
     */
    public Hotel() {
        roomList = new ArrayList<>();
    }

    /**
     * Create new hotel from ReadOnlyHotel
     */
    public Hotel(ReadOnlyHotel toBeCopied) {
        this();
        requireNonNull(toBeCopied);

        this.roomList.addAll(toBeCopied.getImmutableRoomList());
    }

    /**
     * Get the room list.
     * @return a room list.
     */
    public ArrayList<Room> getRoomList() {
        return this.roomList;
    }

    public ObservableList<Room> getImmutableRoomList() {
        return FXCollections.observableArrayList(roomList);
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
     * Get the room object using the room number.
     * @param roomNum String of the room number.
     * @return Optional of the room object if exist. Optional of empty otherwise.
     */
    public Optional<Room> findRoom(String roomNum) {
        requireNonNull(roomNum);

        return roomList.stream().filter(u -> u.getRoomNum().equals(roomNum)).findFirst();
    }

    /**
     * add a new room
     * @param roomNum
     */
    public void addRoom(String roomNum) {
        Room newRoom = new Room(roomNum);
        roomList.add(newRoom);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof Hotel) {
            return this.roomList.equals(((Hotel) other).roomList);
        }

        return false;
    }
}
