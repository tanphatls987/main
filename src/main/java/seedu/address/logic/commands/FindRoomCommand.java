package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;

import java.util.HashSet;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.hotel.booking.Booking;
import seedu.address.model.hotel.person.Name;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.ids.PersonId;
import seedu.address.model.ids.RoomId;


/**
 * Finds and lists all rooms in hotel.
 */
public class FindRoomCommand extends Command {

    public static final String COMMAND_WORD = "findroom";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": returns all rooms with matching Room ID"
            + "or the person who stay in this room.\n"
            + "Parameters: [rn/ROOM_NUMBER] ... [rn/ROOM_NUMBER] [n/NAME] ... [n/NAME] [i/ID] ... [i/ID]"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "A000000"
            + PREFIX_ROOMNUMBER + "001"
            + PREFIX_NAME + "Tuan Le";

    private HashSet<Name> names;
    private HashSet<RoomId> roomIds;
    private HashSet<PersonId> personIds;

    /**
     * Creates new FindRoomCommand
     * @param names: list of names.
     * @param roomIds: list of roomIds
     * @param personIds: list of personIds
     */
    public FindRoomCommand(HashSet<Name> names, HashSet<RoomId> roomIds, HashSet<PersonId> personIds) {
        requireNonNull(names);
        requireNonNull(roomIds);
        requireNonNull(personIds);

        this.names = names;
        this.roomIds = roomIds;
        this.personIds = personIds;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        HashSet<Room> x = new HashSet<>();
        Predicate<Room> predicate = room -> {
            if (this.roomIds.contains(room.getRoomId())) {
                return true;
            }
            Optional<Booking> bookingOpt = model.getCurrentStay(room);
            if (bookingOpt.isPresent()) {
                Booking booking = bookingOpt.get();
                if (names.contains(booking.getPayee().getName())) {
                    return true;
                }
                if (personIds.contains(booking.getPayee().getPersonId())) {
                    return true;
                }
            }
            return false;
        };

        model.updateFilteredRoomList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ROOMS_LISTED_OVERVIEW, model.getFilteredRoomList().size())
        );
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof FindRoomCommand)) {
            return false;
        }

        FindRoomCommand otherCommand = (FindRoomCommand) other;
        return this.names.equals(otherCommand.names)
                && this.personIds.equals(otherCommand.personIds)
                && this.roomIds.equals(otherCommand.roomIds);
    }

    @Override
    public String toString() {
        return names.toString() + " " + roomIds.toString() + personIds.toString();
    }
}
