package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalRooms.BEST_ROOM;
import static seedu.address.testutil.TypicalRooms.WORST_ROOM;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Hotel;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyHotel;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.hotel.Room;
import seedu.address.model.hotel.Tier;
import seedu.address.model.hotel.booking.Booking;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.ids.PersonId;
import seedu.address.model.ids.RoomId;
import seedu.address.model.timeframe.TimeFrame;

public class CheckInCommandTest {

    @Test
    public void constructor_nullPersonId_throwsNullPointerException() {
        RoomId roomId = new RoomId("123");
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(1);
        assertThrows(NullPointerException.class, () -> new CheckInCommand(null, roomId, localDateTime));
    }

    @Test
    public void constructor_nullRoomId_throwsNullPointerException() {
        PersonId personId = new PersonId("G1232123O");
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(1);
        assertThrows(NullPointerException.class, () -> new CheckInCommand(personId, null, localDateTime));
    }

    @Test
    public void constructor_nullLocalDateTime_throwsNullPointerException() {
        PersonId personId = new PersonId("G1232123O");
        RoomId roomId = new RoomId("123");
        assertThrows(NullPointerException.class, () -> new CheckInCommand(personId, roomId, null));
    }

    @Test
    public void constructor_personDoesNotExist_throwsNullPointerException() {
        PersonId personId = new PersonId("G1232123O");
        RoomId roomId = new RoomId("123");
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(1);
        CheckInCommand checkInCommand = new CheckInCommand(personId, roomId, localDateTime);
        ModelStubWithRoomsAndPerson modelStubWithRoomsAndPerson =
            new ModelStubWithRoomsAndPerson();

        assertThrows(CommandException.class,
            String.format(CheckInCommand.MESSAGE_PERSON_NOT_EXISTS, personId), () ->
            checkInCommand.execute(modelStubWithRoomsAndPerson));
    }

    @Test
    public void constructor_roomDoesNotExist_throwsNullPointerException() {
        PersonId personId = ALICE.getPersonId();
        RoomId roomId = new RoomId("123");
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(1);
        CheckInCommand checkInCommand = new CheckInCommand(personId, roomId, localDateTime);
        ModelStubWithRoomsAndPerson modelStubWithRoomsAndPerson =
            new ModelStubWithRoomsAndPerson();

        assertThrows(CommandException.class,
            String.format(CheckInCommand.MESSAGE_ROOM_NOT_EXISTS, roomId), () ->
            checkInCommand.execute(modelStubWithRoomsAndPerson));
    }

    @Test
    public void execute_expiredToDate_throwsCommandException() {
        PersonId personId = ALICE.getPersonId();
        RoomId roomId = BEST_ROOM.getRoomId();
        LocalDateTime toDate = LocalDateTime.now().plusDays(-1);
        CheckInCommand checkInCommand = new CheckInCommand(personId, roomId, toDate);
        ModelStubWithRoomsAndPerson modelStubWithRoomsAndPerson =
            new ModelStubWithRoomsAndPerson();

        assertThrows(CommandException.class,
            String.format(CheckInCommand.MESSAGE_DATE_PASSED, toDate), () ->
            checkInCommand.execute(modelStubWithRoomsAndPerson));
    }

    @Test
    public void execute_roomOccupied_throwsCommandException() {
        PersonId personId = ALICE.getPersonId();
        RoomId roomId = BEST_ROOM.getRoomId();
        LocalDateTime toDate = LocalDateTime.now().plusDays(2);
        CheckInCommand checkInCommand = new CheckInCommand(personId, roomId, toDate);
        ModelStubWithBooking modelStubWithBooking =
            new ModelStubWithBooking();

        assertThrows(CommandException.class,
            String.format(CheckInCommand.MESSAGE_ROOM_OCCUPIED, roomId), () ->
            checkInCommand.execute(modelStubWithBooking));
    }

    @Test
    public void execute_checkInClash_throwsCommandException() {
        PersonId personId = ALICE.getPersonId();
        RoomId roomId = BEST_ROOM.getRoomId();
        LocalDateTime toDate = LocalDateTime.now().plusDays(2);
        CheckInCommand checkInCommand = new CheckInCommand(personId, roomId, toDate);
        ModelStubWithBooking modelStubWithBooking =
            new ModelStubWithBooking();

        assertThrows(CommandException.class,
            String.format(CheckInCommand.MESSAGE_DATE_PASSED, toDate), () ->
            checkInCommand.execute(modelStubWithBooking));
    }

    @Test
    public void execute_successfulBooking() throws CommandException {
        PersonId personId = ALICE.getPersonId();
        RoomId roomId = WORST_ROOM.getRoomId();
        LocalDateTime toDate = LocalDateTime.now().plusDays(2);
        CheckInCommand checkInCommand = new CheckInCommand(personId, roomId, toDate);
        ModelStubWithBooking modelStubWithBooking =
            new ModelStubWithBooking();
        CommandResult commandResult = checkInCommand.execute(modelStubWithBooking);

        assertEquals(CheckInCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

    private class ModelStub implements Model {
        protected final ArrayList<Room> roomList;
        protected final ArrayList<Booking> bookingList;
        protected final Hotel hotel;

        public ModelStub() {
            roomList = new ArrayList<>();
            bookingList = new ArrayList<>();
            hotel = new Hotel();
        }

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
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook addressBook) {
            throw new AssertionError("This method should not be called.");
        }

        //=========== AddressBook ================================================================================

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyHotel getHotel() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
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
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        //=========== Filtered Person List Accessors =============================================================

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ArrayList<Room> getRoomList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ArrayList<Booking> getBookingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Room> findRoom(String roomNum) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void fillRoomList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isRoomFree(Room room, TimeFrame duration) {
            return !bookingList
                .stream()
                .anyMatch(u -> u.isClash(room, duration));
        }

        @Override
        public void bookRoom(Booking booking) {
            requireNonNull(booking);

            bookingList.add(booking);
        }

        @Override
        public void fetchBillList(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void fetchBill(Person person, String roomNum) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRoom(String roomName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasRoom(String roomNum) {
            requireNonNull(roomNum);

            return hotel.hasRoom(roomNum);
        }

        @Override
        public boolean hasTier(Tier tier) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTier(Tier tier, ArrayList<String> roomNums) {
            throw new AssertionError("This method should not be called.");
        }
    }

    private class ModelStubWithRoomsAndPerson extends ModelStub {
        protected Person person;

        ModelStubWithRoomsAndPerson() {
            super();
            this.roomList.add(BEST_ROOM);
            this.roomList.add(WORST_ROOM);
            this.person = ALICE;
        }


        /**
         * Return a person with matching personId
         *
         * @param personId
         * @return Optional of the person if exist. Optional of empty if it doesn't exist
         */
        @Override
        public Optional<Person> findPersonWithId(PersonId personId) {
            if (this.person.getPersonId() == personId) {
                return Optional.of(this.person);
            }
            return Optional.empty();
        }
    }

    private class ModelStubWithBooking extends ModelStubWithRoomsAndPerson {
        ModelStubWithBooking() {
            super();
            Booking booking = new Booking(ALICE, BEST_ROOM, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
            bookingList.add(booking);
        }

        @Override
        public void bookRoom(Booking booking) { }

    }

}
