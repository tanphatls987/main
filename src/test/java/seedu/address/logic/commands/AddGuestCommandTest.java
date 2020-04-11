package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyBookKeeper;
import seedu.address.model.ReadOnlyHotel;
import seedu.address.model.ReadOnlyUserPrefs;
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
import seedu.address.testutil.PersonBuilder;

public class AddGuestCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddGuestCommand(null));
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddGuestCommand addGuestCommand = new AddGuestCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class,
            AddGuestCommand.MESSAGE_DUPLICATE_PERSON, () -> addGuestCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddGuestCommand addAliceCommand = new AddGuestCommand(alice);
        AddGuestCommand addBobCommand = new AddGuestCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddGuestCommand addAliceCommandCopy = new AddGuestCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getHotelFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getBookKeeperFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyHotel getHotel() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyBookKeeper getBookKeeper() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPersonId(PersonId personId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Person> findPersonWithId(PersonId personId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasRoom(String roomNum) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasBooking(Booking booking) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRoom(String roomNum) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void addRoom(String roomName, Tier tier, RoomCost cost) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Booking> getFilteredBookingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Room> getFilteredRoomList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<AvailableService> getFilteredServiceList() {
            return null;
        }

        @Override
        public ObservableList<Bill> getFilteredBillList() {
            return null;
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBookingList(Predicate<Booking> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredRoomList(Predicate<Room> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredServiceList(Predicate<AvailableService> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBillList(Predicate<Bill> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Room> getRoomList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Booking> getBookingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Booking> getCurrentStay(Room room) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Room> findRoom(RoomId roomId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isRoomFree(Person person, Room room, TimeFrame duration) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isGuestCheckedIn(Person person, Room room) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void bookRoom(Booking booking) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void checkIn(Stay stay) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean checkOut(Room room) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTier(Tier tier, ArrayList<String> roomNums) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteBooking(Booking booking) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTier(Tier tier) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRoomCost(Room room, RoomCost roomCost) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAvailableService(AvailableService service) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<AvailableService> findService(AvailableServiceId service) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void chargeRoomCost(RoomId roomId, RoomCost roomCost, Stay stay) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void chargeService(RoomId roomId, AvailableService service) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRoom(String roomNum) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAvailableService(AvailableServiceId id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBill(Bill bill) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteBill(RoomId roomId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Bill> findBillList(PersonId personId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Bill> findBill(RoomId roomId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Cost getGuestBillsTotal(PersonId personId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Booking> findBookingById(String bookingId) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public boolean hasPersonId(PersonId personId) {
            requireNonNull(personId);
            return personsAdded.stream().anyMatch(personId::equals);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
