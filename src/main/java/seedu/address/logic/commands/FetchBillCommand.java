package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hotel.Room;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.ids.PersonId;
import seedu.address.model.ids.RoomId;

/**
 * Retrieves the bill of a guest
 */
public class FetchBillCommand extends Command {

    public static final String COMMAND_WORD = "bill";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Retrieves the bill of a guest.\n"
            + "Parameters:"
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_ROOMNUMBER + "ROOMNUMBER]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "J0000000 "
            + PREFIX_ROOMNUMBER + "101";

    public static final String MESSAGE_SUCCESS_NONSPECIFIC = "These are the bill/s for guest %1$s (%2$s).";
    public static final String MESSAGE_SUCCESS_SPECIFIC = "This is the bill for room %1$s of guest %2$s (%3$s).";
    public static final String MESSAGE_GUEST_NONEXISTENT = "Guest %1$s does not exist in the system.";
    public static final String MESSAGE_ROOM_NONEXISTENT = "Room %1$s does not exist in the system.";

    private final PersonId personId;
    private final boolean isSpecific;
    private final RoomId roomId;

    /**
     * @param personId to fetch the bills of
     */
    public FetchBillCommand(PersonId personId) {
        requireNonNull(personId);
        this.personId = personId;
        this.roomId = null;
        this.isSpecific = false;
    }

    /**
     * Specifically fetch the bill of room number {@code roomNum} for {@code personId}
     */
    public FetchBillCommand(PersonId personId, RoomId roomNum) {
        requireNonNull(personId);
        requireNonNull(roomNum);
        this.personId = personId;
        this.roomId = roomNum;
        this.isSpecific = true;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Person> person = model.findPersonWithId(personId);

        if (person.isEmpty()) {
            throw new CommandException(MESSAGE_GUEST_NONEXISTENT);
        }

        if (isSpecific) {
            Optional<Room> room = model.findRoom(personId.toString());

            if (room.isEmpty()) {
                throw new CommandException(String.format(MESSAGE_ROOM_NONEXISTENT, roomId));
            }

            model.fetchBill(person.get(), roomId);
            return new CommandResult(String.format(MESSAGE_SUCCESS_SPECIFIC, roomId, personId, person.get().getName()));
        } else {
            model.fetchBillList(person.get());
            return new CommandResult(String.format(MESSAGE_SUCCESS_NONSPECIFIC, personId, person.get().getName()));
        }
    }
}
