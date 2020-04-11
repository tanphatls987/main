package seedu.address.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.hotel.Stay;
import seedu.address.model.hotel.bill.AvailableService;
import seedu.address.model.hotel.bill.Bill;
import seedu.address.model.hotel.bill.Cost;
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
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Booking> PREDICATE_SHOW_ALL_BOOKINGS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Room> PREDICATE_SHOW_ALL_ROOMS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Bill> PREDICATE_SHOW_ALL_BILLS = unused -> true;

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
     * Returns the user prefs' book keeper file path
     */
    Path getBookKeeperFilePath();

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

    /** Returns the BookKeeper*/
    ReadOnlyBookKeeper getBookKeeper();

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
     * Returns an unmodifiable view of the filtered booking list.
     */
    ObservableList<Booking> getFilteredBookingList();

    /**
     * Returns an unmodifiable view of the filtered room list.
     */
    ObservableList<Room> getFilteredRoomList();

    /**
     * Returns an unmodifiable view of the filtered service list.
     */
    ObservableList<AvailableService> getFilteredServiceList();

    /**
     * Returns an unmodifiable view of the filtered bill list.
     */
    ObservableList<Bill> getFilteredBillList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered booking list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredBookingList(Predicate<Booking> predicate);

    /**
     * Updates the filter of the filtered service list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredServiceList(Predicate<AvailableService> predicate);

    /**
     * Updates the filter of the filtered service list to filter by the given {@code predicate}.
     */
    void updateFilteredBillList(Predicate<Bill> predicate);

    /**
     * Updates the filter of the filtered room list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRoomList(Predicate<Room> predicate);

    /**Returns list of rooms
     * @return*/
    ObservableList<Room> getRoomList();

    /** Returns list of bookings */
    ObservableList<Booking> getBookingList();

    Optional<Booking> getCurrentStay(Room room);

    Optional<Room> findRoom(RoomId roomNum);


    /**
     * Check if a room is available during a period.
     * @param person
     * @param room
     * @param duration
     * @return
     */
    boolean isRoomFree(Person person, Room room, TimeFrame duration);

    /**
     * Checks if {@code person} is checked into {@code room}
     */
    boolean isGuestCheckedIn(Person person, Room room);

    /**
     * Book a room with booking details.
     * @param booking
     */
    void bookRoom(Booking booking);

    void checkIn(Stay stay);

    boolean checkOut(Room room);

    void deleteBooking(Booking booking);

    /**
     * Adds {@code bill} to the bookkeeper.
     */
    void addBill(Bill bill);

    /**
     * Deletes {@code bill} from the bookkeeper.
     */
    void deleteBill(RoomId roomId);

    /**
     * Returns the list of bills for specified personId.
     */
    ObservableList<Bill> findBillList(PersonId personId);

    /**
     * Returns the bill for the specified roomId.
     */
    Optional<Bill> findBill(RoomId roomId);

    /**
     * Returns the total cost of all bills belonging to specified personId.
     */
    Cost getGuestBillsTotal(PersonId personId);

    /**
     * Add a room with roomName
     * @param roomName
     *
     */
    void addRoom(String roomName);

    /**
     * Add a room with roomName, tier, and cost
     * @param roomName
     * @param tier
     * @param cost
     */
    void addRoom(String roomName, Tier tier, RoomCost cost);

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
     * Charges the room cost to the bill of the corresponding room number.
     */
    void chargeRoomCost(RoomId roomId, RoomCost roomCost, Stay stay);

    /**
     * Charges a service to the bill of the corresponding room number.
     */
    void chargeService(RoomId roomId, AvailableService service);

    /**
     * deletes a room from hotel
     */
    void deleteRoom(String roomNum);

    /**
     * deletes an available service from hotel
     */
    void deleteAvailableService(AvailableServiceId id);

    /**
     * Returns a Booking with matching bookingId.
     */
    Optional<Booking> findBookingById(String bookingId);
}
