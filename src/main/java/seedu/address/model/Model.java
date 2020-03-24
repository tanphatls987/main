package seedu.address.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.hotel.booking.Booking;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.ids.PersonId;
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
    ArrayList<Booking> getBookingList();

    Optional<Room> findRoom(String roomNum);

    /** Populates room list */
    void fillRoomList();


    /**
     * Check if a room is available during a period.
     * @param room
     * @param duration
     * @return
     */
    boolean isRoomFree(Room room, TimeFrame duration);

    /**
     * Book a room with booking details.
     * @param booking
     */
    void bookRoom(Booking booking);

    /** Returns list of bills for specified person*/
    void fetchBillList(Person person);

    /** Returns bill for specified room of person */
    void fetchBill(Person person, String roomNum);

    /**
     * Add a room with roomName
     * @param roomName
     *
     */
    void addRoom(String roomName);

    /**
     * Check if a room exist
     * @param roomName
     * @return a result
     */
    boolean hasRoom(String roomName);

}
