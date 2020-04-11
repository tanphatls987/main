package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyBookKeeper;
import seedu.address.model.ReadOnlyHotel;
import seedu.address.model.hotel.bill.Bill;
import seedu.address.model.hotel.booking.Booking;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.hotel.room.Room;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns the Hotel.
     *
     * @see seedu.address.model.Model#getHotel()
     */
    ReadOnlyHotel getHotel();

    /**
     * Returns the BookKeeper.
     *
     * @see seedu.address.model.Model#getBookKeeper()
     */
    ReadOnlyBookKeeper getBookKeeper();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns an unmodifiable view of the filtered list of bookings
     */
    ObservableList<Booking> getFilteredBookingList();

    /** Returns an unmodifiable view of the room list */
    ObservableList<Room> getRoomList();

    /** Returns an unmodifiable view of the booking list*/
    ObservableList<Booking> getBookingList();

    /**
     * Returns an unmodifiable view of the room list.
     */
    ObservableList<Room> getFilteredRoomList();

    /**
     * Returns an unmodifiable view of the bill list.
     */
    ObservableList<Bill> getFilteredBillList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' hotel file path
     */
    Path getHotelFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
