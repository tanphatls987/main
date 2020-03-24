package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;

import seedu.address.model.hotel.room.Room;
import seedu.address.model.hotel.room.UniqueRoomList;
import seedu.address.model.ids.RoomId;

/**
 * Storing hotel's details: rooms, booking
 */
public class Hotel implements ReadOnlyHotel {
    private final UniqueRoomList roomList;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        roomList = new UniqueRoomList();
    }

    /**
     * Create new empty hotel.
     */
    public Hotel() {
    }

    /**
     * Create new hotel from ReadOnlyHotel
     */
    public Hotel(ReadOnlyHotel toBeCopied) {
        this();
        requireNonNull(toBeCopied);
        resetData(toBeCopied);
    }

    @Override
    public ObservableList<Room> getImmutableRoomList() {
        return null;
    }

    /**
     * Get the room list.
     * @return a room list.
     */
    public ObservableList<Room> getRoomList() {
        return roomList.asUnmodifiableObservableList();
    }

    /*public ObservableList<Room> getImmutableRoomList() {
        return FXCollections.observableArrayList(roomList);
    }*/

    //// list overwrite operations

    /**
     * Replaces the contents of the room list with {@code rooms}.
     * {@code rooms} must not contain duplicate rooms.
     */
    public void setRooms(List<Room> rooms) {
        this.roomList.setRooms(rooms);
    }

    /**
     * Replaces the given room {@code target} in the list with {@code editedRoom}.
     * {@code target} must exist in the address book.
     * The room identity of {@code editedRoom} must not be the same as another existing room in the hotel.
     */
    public void setRooms(Room target, Room editedRoom) {
        requireNonNull(editedRoom);

        roomList.setRoom(target, editedRoom);
    }

    /**
     * Resets the existing data of this {@code Hotel} with {@code newData}.
     */
    public void resetData(ReadOnlyHotel newData) {
        requireNonNull(newData);

        setRooms(newData.getRoomList());
    }

    //// person-level operations

    /**
     * Returns true if a room with the same identity as {@code room} exists in the hotel.
     */
    public boolean hasRoom(Room room) {
        requireNonNull(room);
        return roomList.contains(room);
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
     * Return a room with matching room Id
     * @param roomId
     * @return
     */
    public Optional<Room> findRoomWithRoomId(RoomId roomId) {
        return roomList.asUnmodifiableObservableList()
                .stream()
                .filter(u -> u.getRoomId().equals(roomId))
                .findFirst();
    }

    /**
     * Return a room with matching room number
     * @param roomNum
     * @return
     */
    public Optional<Room> getRoom(String roomNum) {
        return roomList.asUnmodifiableObservableList()
                .stream()
                .filter(u -> u.getRoomNum().equals(roomNum))
                .findFirst();
    }

    /**
     * Adds a room to the hotel.
     * The room must not already exist in the address book.
     */
    public void addRoom(Room r) {
        roomList.add(r);
    }

    /**
     * add a new room
     * @param roomNum
     */
    public void addRoom(String roomNum) {
        Room newRoom = new Room(roomNum);
        roomList.add(newRoom);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeRoom(Room key) {
        roomList.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return roomList.asUnmodifiableObservableList().size() + " rooms";
        // TODO: refine later
    }

    @Override
    public int hashCode() {
        return roomList.hashCode();
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
