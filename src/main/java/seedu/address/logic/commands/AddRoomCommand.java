package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
/**
 * Adds a room to hotel
 */
public class AddRoomCommand extends Command {

    public static final String COMMAND_WORD = "addroom";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a room to Morpheus.\n"
            + "Parameters: "
            + PREFIX_ROOMNUMBER + "ROOM_NUM\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROOMNUMBER + "12E";

    public static final String MESSAGE_SUCCESS = "New room added: %1$s";
    public static final String MESSAGE_DUPLICATE_ROOM = "This room was added before!";
    public static final String MESSAGE_ROOM_NAME_NOT_ALLOWED = "This room name is not allowed";

    private final String toAdd;

    /**
     * Construct a room command with a room name
     * @param roomName: name of the added room.
     */
    public AddRoomCommand(String roomName) {
        requireNonNull(roomName);
        this.toAdd = roomName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Room name cannot contain space
        if (this.toAdd.contains(" ")) {
            throw new CommandException(MESSAGE_ROOM_NAME_NOT_ALLOWED);
        }

        if (model.hasRoom(this.toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ROOM);
        }

        model.addRoom(this.toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddRoomCommand // instanceof handles nulls
                && toAdd.equals(((AddRoomCommand) other).toAdd));
    }
}
