package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.address.model.hotel.Room;
import seedu.address.model.hotel.Tier;

/**
 * Storing hotel's details: rooms, booking
 */
public class Hotel implements ReadOnlyHotel {
    private final ArrayList<Room> roomList;
    private final ArrayList<Tier> tierList;

    /**
     * Create new empty hotel.
     */
    public Hotel() {
        roomList = new ArrayList<>();
        tierList = new ArrayList<>();
    }

    /**
     * Create new hotel from ReadOnlyHotel
     */
    public Hotel(ReadOnlyHotel toBeCopied) {
        this();
        requireNonNull(toBeCopied);

        this.roomList.addAll(toBeCopied.getImmutableRoomList());
        this.tierList.addAll(toBeCopied.getImmutableTierList());
    }

    /**
     * Get the room list.
     * @return a room list.
     */
    public ArrayList<Room> getRoomList() {
        return this.roomList;
    }

    @Override
    public ObservableList<Room> getImmutableRoomList() {
        return FXCollections.observableArrayList(roomList);
    }

    @Override
    public ObservableList<Tier> getImmutableTierList() {
        return FXCollections.observableArrayList(tierList);
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
     * Check if tier exists
     * @param otherTier tier
     * @return result
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
     * add a new room
     */
    public void addRoom(String roomNum) {
        Room newRoom = new Room(roomNum);
        roomList.add(newRoom);
    }

    /**
     * populates room list.
     */
    public void fillRoomList() {
        for (int i = 0; i < 10; i++) {
            roomList.add(new Room(Integer.toString(i), new Tier()));
        }
    }
    /**
     * adds a new tier.
     */
    public void addTier(Tier tier) {
        tierList.add(tier);
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
