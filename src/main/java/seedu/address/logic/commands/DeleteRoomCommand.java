package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a room from hotel
 */
public class DeleteRoomCommand extends Command {

    public static final String COMMAND_WORD = "deleteroom";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a room from Morpheus.\n"
            + "Parameters: "
            + PREFIX_ROOMNUMBER + "ROOM_NUM\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROOMNUMBER + "12E";

    public static final String MESSAGE_SUCCESS = "Room %1$s has been deleted.";
    public static final String MESSAGE_ROOM_NOT_FOUND = "There is no such room.";

    private final String toDelete;

    /**
     * Constructs a {@code DeleteRoomCommand}
     * @param roomNum: a string denoting room's number.
     */
    public DeleteRoomCommand(String roomNum) {
        requireNonNull(roomNum);

        this.toDelete = roomNum;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasRoom(toDelete)) {
            throw new CommandException(MESSAGE_ROOM_NOT_FOUND);
        }

        model.deleteRoom(toDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete), "room");
    }

    @Override
    public boolean equals(Object other) {
        // short circuit
        if (this == other) {
            return true;
        }

        // check instance
        if (!(other instanceof DeleteRoomCommand)) {
            return false;
        }

        // check equality
        DeleteRoomCommand otherCommand = (DeleteRoomCommand) other;
        return toDelete.equals(otherCommand.toDelete);
    }
}
