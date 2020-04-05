package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROMDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TODATE;

import java.time.LocalDateTime;
import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hotel.booking.Booking;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.ids.PersonId;
import seedu.address.model.ids.RoomId;
import seedu.address.model.timeframe.TimeFrame;
import seedu.address.model.timeframe.exception.InvalidTimeFrameException;

/**
 * Reserves a room under an existing person in the address book.
 */
public class ReserveCommand extends Command {

    public static final String COMMAND_WORD = "reserve";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reserves a room for a guest.\n"
        + "Parameters: "
        + PREFIX_ID + "PERSONID "
        + PREFIX_ROOMNUMBER + "ROOMNUMBER "
        + PREFIX_FROMDATE + "FROMDATE "
        + PREFIX_TODATE + "TODATE\n"
        + "Example: "
        + COMMAND_WORD + " "
        + PREFIX_ID + "C0000000 "
        + PREFIX_ROOMNUMBER + "101 "
        + PREFIX_FROMDATE + "2020-12-12 "
        + PREFIX_TODATE + "2020-12-23 ";

    public static final String MESSAGE_ADD_RESERVE_SUCCESS = "Booked room %1$s for guest %2$s (ID: %3$s).";
    public static final String MESSAGE_GUEST_NONEXISTENT = "Guest (ID: %1$s) does not exist in the system.";
    public static final String MESSAGE_ROOM_NONEXISTENT = "Room %1$s does not exist in the system.";
    public static final String MESSAGE_ROOM_BOOKED = "Room %1$s is already booked at this time.";

    private final RoomId roomId;
    private final PersonId personId;
    private final TimeFrame reserveDuration;

    public ReserveCommand(PersonId personId, RoomId roomNum, LocalDateTime fromDate, LocalDateTime toDate)
        throws InvalidTimeFrameException {
        requireAllNonNull(personId, roomNum, fromDate, toDate);
        this.roomId = roomNum;
        LocalDateTime reserveFrom = fromDate;
        LocalDateTime reserveTo = toDate;

        ///this can throw InvalidTimeFrame
        this.reserveDuration = new TimeFrame(reserveFrom, reserveTo);
        this.personId = personId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Optional<Person> payee = model.findPersonWithId(personId);
        if (payee.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_GUEST_NONEXISTENT, personId));
        }
        Optional<Room> room = model.findRoom(roomId);
        if (room.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_ROOM_NONEXISTENT, roomId));
        }
        Booking toBook = new Booking(payee.get(), room.get(), reserveDuration);
        if (model.hasBooking(toBook)) {
            throw new CommandException(String.format(MESSAGE_ROOM_BOOKED, roomId));
        }
        model.bookRoom(toBook);
        return new CommandResult(generateSuccessMessage(payee.get()));

    }

    /**
     * Generates a command execution success message based on whether reservation added successfully
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person payee) {
        return String.format(MESSAGE_ADD_RESERVE_SUCCESS, roomId, payee.getName(), personId);
    }
}
