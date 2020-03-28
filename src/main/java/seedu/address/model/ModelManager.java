package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.hotel.Room;
import seedu.address.model.hotel.Tier;
import seedu.address.model.hotel.booking.Booking;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.ids.PersonId;
import seedu.address.model.timeframe.TimeFrame;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final ArrayList<Booking> bookingList;
    private final Hotel hotel;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, ReadOnlyHotel hotel) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.hotel = new Hotel(hotel);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.bookingList = new ArrayList<Booking>();
    }

    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        this(addressBook, userPrefs, new Hotel());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new Hotel());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public ReadOnlyHotel getHotel() {
        return hotel;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    /**
     * Return a person with matching personId
     *
     * @param personId
     * @return
     */
    @Override
    public Optional<Person> findPersonWithId(PersonId personId) {
        return addressBook.findPersonWithId(personId);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

    @Override
    public ArrayList<Room> getRoomList() {
        return hotel.getRoomList();
    }

    @Override
    public ArrayList<Booking> getBookingList() {
        return bookingList;
    }


    @Override
    public Optional<Room> findRoom(String roomNum) {
        requireNonNull(roomNum);

        return hotel.findRoom(roomNum);
    }

    @Override
    public void fillRoomList() {
        hotel.fillRoomList();
    }

    @Override
    public boolean isRoomFree(Room room, TimeFrame duration) {
        requireNonNull(room);
        requireNonNull(duration);

        ///timeframe create successfully mean no bogus duration
        return !(bookingList
            .stream()
            .anyMatch(u -> u.isClash(room, duration)));
    }

    @Override
    public void bookRoom(Booking booking) {
        requireNonNull(booking);

        bookingList.add(booking);
    }


    // to update accordingly when implementing billing system.
    @Override
    public void fetchBillList(Person person) {
    }

    // to update accordingly when implementing billing system.
    @Override
    public void fetchBill(Person person, String roomNum) {
    }

    @Override
    public void addRoom(String roomNum) {
        requireNonNull(roomNum);

        hotel.addRoom(roomNum);
    }

    @Override
    public boolean hasRoom(String roomNum) {
        requireNonNull(roomNum);

        return this.hotel.hasRoom(roomNum);

    }

    @Override
    public boolean hasTier(Tier tier) {
        requireNonNull(tier);

        return this.hotel.hasTier(tier);
    }

    @Override
    public void addTier(Tier tier, ArrayList<String> roomNums) {
        requireNonNull(tier);
        requireNonNull(roomNums);

        this.hotel.addTier(tier, roomNums);
    }
}
