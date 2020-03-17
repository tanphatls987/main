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
import seedu.address.model.hotel.Room;
import seedu.address.model.hotel.booking.Booking;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.ids.PersonId;
import seedu.address.model.timeframe.TimeFrame;
import seedu.address.model.timeframe.exception.InvalidTimeFrameException;

/**
 * Reserves a room under an existing person in the address book.
 */
public class ReserveCommand extends Command {

    public static final String COMMAND_WORD = "reserve";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reserves room of the person identified "
        + "by the index number used in the last person listing. "
        + "under room number\n"
        + PREFIX_ROOMNUMBER + "\n"
        + "Parameters: PERSONID, ROOMNUMBER, FROMDATE, TODATE\n"
        + "Example: "
        + COMMAND_WORD + " "
        + PREFIX_ID + "C0000000 "
        + PREFIX_ROOMNUMBER + "101 "
        + PREFIX_FROMDATE + "2020-12-12 "
        + PREFIX_TODATE + "2020-12-23 ";

    public static final String MESSAGE_ADD_RESERVE_SUCCESS = "Added booking to Person: %1$s";

    private final String roomNum;
    private final PersonId personId;
    private final TimeFrame reserveDuration;

    public ReserveCommand(PersonId personId, String roomNum, LocalDateTime fromDate, LocalDateTime toDate)
        throws InvalidTimeFrameException {
        requireAllNonNull(personId, roomNum, fromDate, toDate);
        this.roomNum = roomNum;
        LocalDateTime reserveFrom = LocalDateTime.from(fromDate).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime reserveTo = LocalDateTime.from(toDate).withHour(0).withMinute(0).withSecond(0);

        ///this can throw InvalidTimeFrame
        this.reserveDuration = new TimeFrame(reserveFrom, reserveTo);
        this.personId = personId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Optional<Person> payee = model.findPersonWithId(personId);
        if (payee.isEmpty()) {
            throw new CommandException("No guest with this id");
        }
        Optional<Room> room = model.findRoom(roomNum);
        if (room.isEmpty()) {
            throw new CommandException("No room with this number");
        }
        model.bookRoom(new Booking(payee.get(), room.get(), reserveDuration));
        return new CommandResult(generateSuccessMessage(payee.get()));

    }

    /**
     * Generates a command execution success message based on whether the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person payee) {
        String message = MESSAGE_ADD_RESERVE_SUCCESS;
        return String.format(message, payee);
    }
}
