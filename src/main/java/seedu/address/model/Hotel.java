package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.address.model.hotel.bill.AvailableService;
import seedu.address.model.hotel.bill.UniqueAvailableServiceList;
import seedu.address.model.hotel.booking.Booking;
import seedu.address.model.hotel.booking.exception.RoomBookedException;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.hotel.room.Tier;
import seedu.address.model.hotel.room.UniqueRoomList;
import seedu.address.model.ids.AvailableServiceId;
import seedu.address.model.ids.RoomId;

/**
 * Storing hotel's details: rooms, booking
 */
public class Hotel implements ReadOnlyHotel {
    private final UniqueRoomList roomList;
    private final ArrayList<Booking> bookingList;
    private final ArrayList<Tier> tierList;
    private final UniqueAvailableServiceList availableServiceList;

    /**
     * Create new empty hotel.
     */
    public Hotel() {

        tierList = new ArrayList<>();
        bookingList = new ArrayList<>();
        //non-static initialization block
        {
            roomList = new UniqueRoomList();
            availableServiceList = new UniqueAvailableServiceList();
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
        this.bookingList.addAll(toBeCopied.getBookingList());
        this.availableServiceList.setServices(toBeCopied.getAvailableServiceList());
    }

    //// room-level operations

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

    /**
     * Check is booking already exists in the hotel
     */
    public boolean hasBooking(Booking booking) {
        for (Booking b : bookingList) {
            if (b.isClash(booking)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if {@code person} has booked {@code room}.
     */
    public boolean hasGuestBooked(Person person, Room room) {
        for (Booking b : bookingList) {
            if (b.getPayee().equals(person) && b.getRoom().equals(room)) {
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
     *
     * @Return observable list of bookings */
    @Override
    public ObservableList<Booking> getBookingList() {
        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(bookingList));
    }

    /**
     *
     * @return observable list of available services.
     */
    @Override
    public ObservableList<AvailableService> getAvailableServiceList() {
        return availableServiceList.asUnmodifiableObservableList();
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
    public Optional<Room> getRoom(RoomId roomNum) {
        return roomList
            .asUnmodifiableObservableList()
            .stream()
            .filter(u -> u.getRoomNum().equals(roomNum.toString()))
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
     * adds an available service
     */
    public void addAvailableService(AvailableService service) {
        availableServiceList.add(service);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeRoom(Room key) {
        roomList.remove(key);
    }

    /**
     * Adds booking to booking list
     * @throws RoomBookedException if there is a clash between
     * @code booking and bookings in booking list
     */
    public void addBooking(Booking booking) {
        if (hasBooking(booking)) {
            throw new RoomBookedException();
        } else {
            bookingList.add(booking);
        }
    }

    /**
     * Deletes a room with room number
     */
    public void deleteRoom(String roomNum) {
        ///passed the checks for existence
        roomList.remove(this.findSureRoom(roomNum));
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

    /**
     * Return a service with matching serviceId
     * @return
     */
    public Optional<AvailableService> findServiceWithId(AvailableServiceId serviceId) {
        return availableServiceList.asUnmodifiableObservableList()
                .stream()
                .filter(u -> u.getId().equals(serviceId))
                .findFirst();
    }

    //// util methods
    @Override
    public String toString() {
        return roomList.asUnmodifiableObservableList().size() + " rooms";
        // TODO: refine later
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof Hotel) {
            return this.roomList.equals(((Hotel) other).roomList)
                    && this.bookingList.equals(((Hotel) other).bookingList);
        }


        return false;
    }

    @Override
    public int hashCode() {
        return roomList.hashCode();
    }
}
