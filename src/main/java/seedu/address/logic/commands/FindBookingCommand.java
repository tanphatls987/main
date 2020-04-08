package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.HashSet;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hotel.person.Name;
import seedu.address.model.ids.PersonId;
import seedu.address.model.ids.RoomId;

/**
 * Finds and lists all bookings in hotel whose matches specified bookers of rooms.
 */
public class FindBookingCommand extends Command {

    public static final String COMMAND_WORD = "findbooking";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": returns all bookings with matching personID"
            + " or matching name or matching rooms.\n"
            + "Parameters: [i/ID] ... [i/ID] [n/NAME] ... [n/NAME] [rn/ROOM_ID] ... [rn/ROOM_ID]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "A000000 "
            + PREFIX_ROOMNUMBER + "001";

    private final HashSet<Name> names;
    private final HashSet<PersonId> personIds;
    private final HashSet<RoomId> roomIds;


    /**
     * Constructs a FindBookingCommand with list of predicates.
     * @param names: list of names.
     * @param personIds: list of personIds.
     * @param roomIds: list of roomIds.
     */
    public FindBookingCommand(HashSet<Name> names, HashSet<PersonId> personIds, HashSet<RoomId> roomIds) {
        requireNonNull(names);
        requireNonNull(personIds);
        requireNonNull(roomIds);

        this.names = names;
        this.personIds = personIds;
        this.roomIds = roomIds;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);


    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FindBookingCommand)) {
            return false;
        }

        FindBookingCommand otherCommand = (FindBookingCommand) other;
        return otherCommand.names.equals(this.names)
                && otherCommand.roomIds.equals(this.roomIds)
                && otherCommand.personIds.equals(personIds);
    }

    @Override
    public String toString() {
        return names.toString() + " " + roomIds.toString() + " " + personIds.toString();
    }

}
