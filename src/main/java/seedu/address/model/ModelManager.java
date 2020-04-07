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
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
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
    public Path getHotelFilePath() {
        return userPrefs.getHotelFilePath();
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

    @Override
    public boolean hasPersonId(PersonId personId) {
        requireNonNull(personId);
        return addressBook.hasPersonId(personId);
    }

    /**
     * Return a person with matching personId
     * @param personId the personID
     * @return Optional of the person with that PersonID
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
    public ObservableList<Room> getRoomList() {
        return hotel.getRoomList();
    }

    @Override
    public ObservableList<Booking> getBookingList() {
        return hotel.getBookingList();
    }

    /**
     * Get the stay in the room in current time.
     * @param room the room object.
     * @return The booking if exist, empty otherwise.
     */
    @Override
    public Optional<Booking> getCurrentStay(Room room) {
        requireNonNull(room);

        return hotel.getBookingList()
            .stream()
            .filter(u -> u.isCorrectRoom(room))
            .filter(u -> u.isCurrentlyClash(room))
            .findFirst();
    }

    @Override
    public Optional<Room> findRoom(RoomId roomNum) {
        requireNonNull(roomNum);
        return hotel.findRoomWithRoomId(roomNum);
    }

    @Override
    public boolean isRoomFree(Room room, TimeFrame duration) {
        requireNonNull(room);
        requireNonNull(duration);
        ObservableList<Booking> bookingList = hotel.getBookingList();

        //timeframe create successfully mean no bogus duration
        return bookingList
            .stream()
            .noneMatch(u -> u.isClash(room, duration));
    }

    @Override
    public boolean isGuestCheckedIn(Person person, Room room) {
        requireAllNonNull(person, room);
        return hotel.isGuestCheckedIn(person, room);
    }

    @Override
    public void bookRoom(Booking booking) {
        requireNonNull(booking);
        hotel.addBooking(booking);
    }

    /**
     * Checks in a room with the given details from the booking.
     * @param booking The booking we want to store
     */
    @Override
    public void checkIn(Booking booking) {
        this.bookRoom(booking);
    }

    /**
     * Checks out anyone from the room.
     * @param room the room that wants to be checked out
     * @return 1 if checkout successful, 0 if room does not exist
     */
    @Override
    public boolean checkOut(Room room) {
        requireNonNull(room);

        Optional<Booking> booking = getCurrentStay(room);

        if (booking.isEmpty()) {
            return false;
        }

        // Need to add bill after it's created
        deleteBooking(booking.get());
        return true;
    }

    @Override
    public void deleteBooking(Booking booking) {
        hotel.getBookingList().remove(booking);
    }

    @Override
    public void addRoom(String roomNum) {
        requireNonNull(roomNum);

        hotel.addRoom(roomNum);
    }

    @Override
    public void addRoom(String roomNum, Tier tier, RoomCost cost) {
        requireNonNull(roomNum);
        requireNonNull(tier);
        requireNonNull(cost);

        hotel.addRoom(roomNum, tier, cost);
    }

    @Override
    public boolean hasRoom(String roomNum) {
        requireNonNull(roomNum);

        return this.hotel.hasRoom(roomNum);

    }

    @Override
    public boolean hasBooking(Booking booking) {
        requireNonNull(booking);
        return this.hotel.hasBooking(booking);
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

    //=========== Billing System =============================================================================

    @Override
    public void setRoomCost(Room room, RoomCost roomCost) {
        requireAllNonNull(room, roomCost);
        room.setCost(roomCost);
    }

    @Override
    public void addAvailableService(AvailableService service) {
        requireNonNull(service);
        hotel.addAvailableService(service);
    }

    @Override
    public Optional<AvailableService> findService(AvailableServiceId serviceId) {
        requireNonNull(serviceId);
        return hotel.findServiceWithId(serviceId);
    }

    @Override
    public void chargeService(PersonId personId, RoomId roomId, AvailableService service) {
        requireAllNonNull(personId, roomId, service);
        addressBook.findPersonWithId(personId).get().addToBill(roomId, service);
    }

    // to update accordingly when implementing billing system.
    @Override
    public void fetchBillList(Person person) {
        requireNonNull(person);
    }

    // to update accordingly when implementing billing system.
    @Override
    public void fetchBill(Person person, RoomId roomNum) {
        requireAllNonNull(person, roomNum);
    }

    @Override
    public void deleteRoom(String roomNum) {
        requireNonNull(roomNum);
        hotel.deleteRoom(roomNum);
    }

}
