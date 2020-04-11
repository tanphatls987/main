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
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Booking> filteredBookings;
    private final FilteredList<Room> filteredRooms;
    private final FilteredList<AvailableService> filteredServices;
    private final FilteredList<Bill> filteredBills;
    private final Hotel hotel;
    private final BookKeeper bookKeeper;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs,
                        ReadOnlyHotel hotel, ReadOnlyBookKeeper bookKeeper) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.hotel = new Hotel(hotel);
        this.bookKeeper = new BookKeeper(bookKeeper);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredBookings = new FilteredList<>(this.hotel.getBookingList());
        filteredRooms = new FilteredList<>(this.hotel.getRoomList());
        filteredServices = new FilteredList<>(this.hotel.getAvailableServiceList());
        filteredBills = new FilteredList<>(this.bookKeeper.getBillList());
    }

    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        this(addressBook, userPrefs, new Hotel(), new BookKeeper());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new Hotel(), new BookKeeper());
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
    public Path getBookKeeperFilePath() {
        return userPrefs.getBookKeeperFilePath();
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
     *      * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Booking> getFilteredBookingList() {
        return filteredBookings;
    }

    @Override
    public ObservableList<Room> getFilteredRoomList() {
        return filteredRooms;
    }

    @Override
    public ObservableList<AvailableService> getFilteredServiceList() {
        return filteredServices;
    }

    @Override
    public ObservableList<Bill> getFilteredBillList() {
        return filteredBills;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredBookingList(Predicate<Booking> predicate) {
        requireNonNull(predicate);
        filteredBookings.setPredicate(predicate);
    }

    @Override
    public void updateFilteredServiceList(Predicate<AvailableService> predicate) {
        requireNonNull(predicate);
        filteredServices.setPredicate(predicate);
    }

    @Override
    public void updateFilteredBillList(Predicate<Bill> predicate) {
        requireAllNonNull(predicate);
        filteredBills.setPredicate(predicate);
    }

    @Override
    public void updateFilteredRoomList(Predicate<Room> predicate) {
        requireNonNull(predicate);
        filteredRooms.setPredicate(predicate);
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

        hotel.getCurrentStay(room);

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
    public boolean isRoomFree(Person person, Room room, TimeFrame duration) {
        requireNonNull(person);
        requireNonNull(room);
        requireNonNull(duration);
        return hotel.isRoomFree(person, room, duration);
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
        updateFilteredBookingList(PREDICATE_SHOW_ALL_BOOKINGS);
    }

    /**
     * Checks in a room with the given details from the booking.
     * @param stay The booking we want to store
     */
    @Override
    public void checkIn(Stay stay) {
        hotel.checkIn(stay);
    }

    /**
     * Checks out anyone from the room.
     * @param room the room that wants to be checked out
     * @return 1 if checkout successful, 0 if room does not exist
     */
    @Override
    public boolean checkOut(Room room) {
        requireNonNull(room);
        return hotel.checkOut(room);
    }

    @Override
    public void deleteBooking(Booking booking) {
        hotel.getBookingList().remove(booking);
        updateFilteredBookingList(PREDICATE_SHOW_ALL_BOOKINGS);
    }

    @Override
    public void addRoom(String roomNum) {
        requireNonNull(roomNum);

        hotel.addRoom(roomNum);
        updateFilteredRoomList(PREDICATE_SHOW_ALL_ROOMS);
    }

    @Override
    public void addRoom(String roomNum, Tier tier, RoomCost cost) {
        requireNonNull(roomNum);
        requireNonNull(tier);
        requireNonNull(cost);

        hotel.addRoom(roomNum, tier, cost);
        updateFilteredRoomList(PREDICATE_SHOW_ALL_ROOMS);
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
    public Optional<Booking> findBookingById(String booking) {
        requireNonNull(booking);
        return hotel.findBookingById(booking);
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
    public ReadOnlyBookKeeper getBookKeeper() {
        return bookKeeper;
    }

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
    public void chargeRoomCost(RoomId roomId, RoomCost roomCost, Stay stay) {
        requireAllNonNull(roomId, roomCost, stay);
        bookKeeper.chargeRoomCostToBill(roomId, roomCost, stay);
    }

    @Override
    public void chargeService(RoomId roomId, AvailableService service) {
        requireAllNonNull(roomId, service);
        bookKeeper.chargeServiceToBill(roomId, service);
    }

    @Override
    public void addBill(Bill bill) {
        requireNonNull(bill);
        bookKeeper.addBill(bill);
    }

    @Override
    public void deleteBill(RoomId roomId) {
        requireNonNull(roomId);
        bookKeeper.deleteBill(roomId);
    }

    @Override
    public ObservableList<Bill> findBillList(PersonId personId) {
        requireNonNull(personId);
        return bookKeeper.getBills(personId);
    }

    @Override
    public Optional<Bill> findBill(RoomId roomId) {
        requireNonNull(roomId);
        return bookKeeper.getBill(roomId);
    }

    @Override
    public Cost getGuestBillsTotal(PersonId personId) {
        requireNonNull(personId);
        return bookKeeper.getGuestBillsTotal(personId);
    }

    @Override
    public void deleteRoom(String roomNum) {
        requireNonNull(roomNum);
        hotel.deleteRoom(roomNum);
        updateFilteredRoomList(PREDICATE_SHOW_ALL_ROOMS);
    }

    @Override
    public void deleteAvailableService(AvailableServiceId id) {
        requireNonNull(id);
        hotel.deleteAvailableService(id);
    }

}
