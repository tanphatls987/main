package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.ids.RoomId;

/**
 * Checks out a guest to the hotel.
 */
public class CheckOutCommand extends Command {

    public static final String COMMAND_WORD = "checkout";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Checks out a guest to the hotel.\n"
        + "Parameters: "
        + PREFIX_ROOMNUMBER + "ROOM_NUMBER\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_ROOMNUMBER + "101 ";

    public static final String MESSAGE_SUCCESS = "Room %1$s is successfully checked out!";
    public static final String MESSAGE_ROOM_NOT_EXISTS = "Room %1$s does not exist.";
    public static final String MESSAGE_ROOM_NOT_OCCUPIED = "Room %1$s is empty.";

    private final RoomId roomId;

    /**
     * Creates a CheckOutCommand from current date until {@code toDate}
     * @param roomId the ID of the Room that is going to be checked out.
     */
    public CheckOutCommand(RoomId roomId) {
        requireNonNull(roomId);
        this.roomId = roomId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Room> room = model.findRoom(roomId);

        if (room.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_ROOM_NOT_EXISTS, roomId));
        }

        if (model.checkOut(room.get())) {

            model.deleteBill(roomId);
            return new CommandResult(String.format(MESSAGE_SUCCESS, roomId), "room");
        } else {
            throw new CommandException(String.format(MESSAGE_ROOM_NOT_OCCUPIED, roomId));
        }

    }
}
