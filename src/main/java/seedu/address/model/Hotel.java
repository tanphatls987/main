package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.address.model.hotel.room.Room;
import seedu.address.model.hotel.room.Tier;
import seedu.address.model.hotel.room.UniqueRoomList;
import seedu.address.model.ids.RoomId;

/**
 * Storing hotel's details: rooms, booking
 */
public class Hotel implements ReadOnlyHotel {
    private final UniqueRoomList roomList;

    private final ArrayList<Tier> tierList;

    /**
     * Create new empty hotel.
     */
    public Hotel() {

        tierList = new ArrayList<>();
        //non-static initialization block
        {
            roomList = new UniqueRoomList();
        }
    }

    /**
     * Create new hotel from ReadOnlyHotel
     */
    public Hotel(ReadOnlyHotel toBeCopied) {
        this();
        requireNonNull(toBeCopied);
        this.roomList.setRooms(toBeCopied.getRoomList());
        this.tierList.addAll(toBeCopied.getTierList());
    }

    /**
     * Get the room list.
     * @return a room list.
     */
    public ObservableList<Room> getRoomList() {
        return roomList.asUnmodifiableObservableList();
    }


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
     */
    public boolean hasRoom(String roomNum) {
        for (Room room: roomList) {
            if (room.hasName(roomNum)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ObservableList<Tier> getTierList() {
        return FXCollections.observableArrayList(tierList);
    }

    /**
     * Return a room with matching room Id
     * @param roomId
     * @return Room
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
     * @return Room
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
     */
    public void addRoom(String roomNum) {
        Room newRoom = new Room(roomNum);
        roomList.add(newRoom);
    }

    /**
     * find a room
     */
    private Room findSureRoom(String roomNum) {
        for (Room room: roomList) {
            if (room.hasName(roomNum)) {
                return room;
            }
        }
        return null;
    }


    /**
     * check a if a tier exists
     */
    public boolean hasTier(Tier otherTier) {
        for (Tier tier: tierList) {
            if (tier.equals(otherTier)) {
                return true;
            }
        }
        return false;
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

    /**
     * adds a new tier.
     */
    public void addTier(Tier tier, ArrayList<String> roomNums) {
        tierList.add(tier);

        for (String roomNum: roomNums) {
            if (hasRoom(roomNum)) {
                Room current = findSureRoom(roomNum);
                assert current != null;
                current.setTier(tier);
            }
        }
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

    @Override
    public int hashCode() {
        return roomList.hashCode();
    }
}
