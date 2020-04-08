package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;

import java.util.HashSet;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hotel.booking.MatchBookingPredicate;
import seedu.address.model.hotel.person.MatchNamePredicate;
import seedu.address.model.hotel.person.MatchPersonIdPredicate;
import seedu.address.model.hotel.person.Name;
import seedu.address.model.hotel.room.MatchRoomPredicate;
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
            + PREFIX_NAME + "Tuan Le"
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

        MatchBookingPredicate predicate = new MatchBookingPredicate();
        predicate.setNamePredicate(new MatchNamePredicate(names));
        predicate.setPersonIdPredicate(new MatchPersonIdPredicate(personIds));
        predicate.setRoomPredicate(new MatchRoomPredicate(roomIds));

        model.updateFilteredBookingList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_BOOKINGS_LISTED_OVERVIEW, model.getFilteredBookingList().size())
        );
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
