package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hotel.Room;
import seedu.address.model.ids.PersonId;
import seedu.address.model.ids.RoomId;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;

public class CheckInCommand extends Command {

    public static final String COMMAND_WORD = "checkin";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Checks in a guest to the hotel. "
        + "Parameters: "
        + PREFIX_ID + "ID "
        + PREFIX_ROOMNUMBER + "ROOMNUMBER "
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_ID + "G1231231U"
        + PREFIX_ROOMNUMBER + "101";

    public static final String MESSAGE_SUCCESS = "Room %1$s is booked by %2$s";
    public static final String MESSAGE_ROOM_OCCUPIED = "Room %1$s is occupied";
    public static final String MESSAGE_ROOM_NOT_EXISTS = "Room %1$s does not exists";

    private final PersonId personId;
    private final RoomId roomId;


    public CheckInCommand(PersonId personId, RoomId roomId) {
        requireNonNull(personId);
        requireNonNull(roomId);
        this.personId = personId;
        this.roomId = roomId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if ()

        return null;
    }
}
