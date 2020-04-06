package seedu.address.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.hotel.bill.AvailableService;
import seedu.address.model.hotel.bill.RoomCost;
import seedu.address.model.hotel.booking.Booking;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.hotel.room.Tier;
import seedu.address.model.ids.AvailableServiceId;
import seedu.address.model.ids.PersonId;
import seedu.address.model.ids.RoomId;
import seedu.address.model.timeframe.TimeFrame;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' hotel file path
     */
    Path getHotelFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /** Returns the Hotel*/
    ReadOnlyHotel getHotel();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Return true if a person with the same id exists in the address book.
     * @param personId
     * @return
     */
    boolean hasPersonId(PersonId personId);

    /**
     * Return a person with matching personId
     * @param personId
     * @return
     */
    Optional<Person> findPersonWithId(PersonId personId);
    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**Returns list of rooms
     * @return*/
    ObservableList<Room> getRoomList();

    /** Returns list of bookings */
    ObservableList<Booking> getBookingList();

    Optional<Booking> getCurrentStay(Room room);

    Optional<Room> findRoom(RoomId roomNum);

    /**
     * Check if a room is available during a period.
     * @param room
     * @param duration
     * @return
     */
    boolean isRoomFree(Room room, TimeFrame duration);

    /**
     * Checks if {@code room} is booked by {@code person}
     */
    boolean hasGuestBooked(Person person, Room room);

    /**
     * Book a room with booking details.
     * @param booking
     */
    void bookRoom(Booking booking);

    void checkIn(Booking booking);

    boolean checkOut(Room room);

    void deleteBooking(Booking booking);

    /** Returns list of bills for specified person*/
    void fetchBillList(Person person);

    /** Returns bill for specified room of person */
    void fetchBill(Person person, RoomId roomId);

    /**
     * Add a room with roomName
     * @param roomName
     *
     */
    void addRoom(String roomName);

    /**
     * Check if a room exist
     */
    boolean hasRoom(String roomName);

    /**
     * check if a booking exists
     */
    boolean hasBooking(Booking booking);

    /**
     * Check if a tier name exists
     */
    boolean hasTier(Tier tier);

    /**
     * Add a tier with a tiername
     */
    void addTier(Tier tier, ArrayList<String> roomNums);

    /**
     * Sets the the room cost for the given room.
     */
    void setRoomCost(Room room, RoomCost roomCost);

    /**
     * Adds a service to hotel.
     */
    void addAvailableService(AvailableService service);

    /**
     * Returns a service with matching serviceId.
     */
    Optional<AvailableService> findService(AvailableServiceId serviceId);

    /**
     * Charges a service to the bill of a guest.
     */
    void chargeService(PersonId personId, RoomId roomId, AvailableService service);

    /**
     * deletes a room from hotel
     */
    void deleteRoom(String roomNum);

}
