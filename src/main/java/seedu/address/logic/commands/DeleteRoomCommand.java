package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;

import java.util.List;

import seedu.address.commons.core.either.Either;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hotel.room.Room;


/**
 * Deletes a room from hotel
 */
public class DeleteRoomCommand extends Command {

    public static final String COMMAND_WORD = "deleteroom";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a room from Morpheus by ROOM_ID"
            + ", or  index number used in the displayed room list\n"
            + "Parameters: "
            + PREFIX_ROOMNUMBER + "ROOM_NUM\n"
            + "or Parameters: Index INDEX (must be a positive integer)"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROOMNUMBER + "12E\n"
            + "Example: " + COMMAND_WORD + " 0";

    public static final String MESSAGE_SUCCESS = "Room %1$s has been deleted.";
    public static final String MESSAGE_ROOM_NOT_FOUND = "There is no such room.";
    public static final String MESSAGE_INDEX_INVALID = "Invalid index";


    private final Either<String, Index> toDelete;
    /**
     * Constructs a {@code DeleteRoomCommand}
     * @param roomNum: a string denoting room's number.
     */
    public DeleteRoomCommand(String roomNum) {
        requireNonNull(roomNum);

        this.toDelete = Either.ofLeft(roomNum);
    }

    public DeleteRoomCommand(Index targetIndex) {
        requireNonNull(targetIndex);

        this.toDelete = Either.ofRight(targetIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (this.toDelete.isLeft()) {
            String roomNum = toDelete.getLeft();
            if (!model.hasRoom(roomNum)) {
                throw new CommandException(MESSAGE_ROOM_NOT_FOUND);
            }
            model.deleteRoom(roomNum);
            return new CommandResult(String.format(MESSAGE_SUCCESS, roomNum), "room");
        } else {
            Index targetIndex = this.toDelete.getRight();
            List<Room> lastShownList = model.getFilteredRoomList();

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(MESSAGE_INDEX_INVALID);
            }

            Room room = lastShownList.get(targetIndex.getZeroBased());
            model.deleteRoom(room.getRoomNum());
            return new CommandResult(String.format(MESSAGE_SUCCESS, room.getRoomNum()), "room");
        }

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
