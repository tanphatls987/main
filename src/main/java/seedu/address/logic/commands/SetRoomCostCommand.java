package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hotel.bill.RoomCost;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.ids.RoomId;

/**
 * Sets the cost of a room.
 */
public class SetRoomCostCommand extends Command {
    public static final String COMMAND_WORD = "setrcost";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets the cost of the room number specified.\n"
            + "Parameters:"
            + PREFIX_ROOMNUMBER + "ROOMNUMBER "
            + PREFIX_COST + "COST \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROOMNUMBER + "101 "
            + PREFIX_COST + "50";

    public static final String MESSAGE_SUCCESS = "The cost for room %1$s is set to %2$s per night.";
    public static final String MESSAGE_ROOM_NONEXISTENT = "Room %1$s does not exist in the system.";
    public static final String MESSAGE_ROOM_COST_INVALID = "Room cost must be a non-negative number";

    private final RoomId roomNum;
    private final RoomCost cost;

    /**
     * @param roomNum to set the cost of
     * @param cost of room per night
     */
    public SetRoomCostCommand(RoomId roomNum, RoomCost cost) {
        requireAllNonNull(roomNum, cost);

        this.roomNum = roomNum;
        this.cost = cost;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Optional<Room> room = model.findRoom(roomNum);

        if (room.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_ROOM_NONEXISTENT, roomNum));
        }

        if (cost.isNegative()) {
            throw new CommandException(MESSAGE_ROOM_COST_INVALID);
        }

        model.setRoomCost(room.get(), cost);
        return new CommandResult(String.format(MESSAGE_SUCCESS, roomNum, cost));
    }
}
