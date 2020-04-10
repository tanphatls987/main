package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIER;

import java.util.function.Predicate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hotel.bill.RoomCost;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.hotel.room.Tier;



/**
 * Adds a room to hotel
 */
public class AddRoomCommand extends Command {

    public static final String COMMAND_WORD = "addroom";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a room to Morpheus.\n"
            + "Parameters: "
            + PREFIX_ROOMNUMBER + "ROOM_NUM "
            + PREFIX_TIER + "TIER "
            + PREFIX_COST + "COST "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROOMNUMBER + "12E "
            + PREFIX_TIER + "GOLD "
            + PREFIX_COST + "150.00";

    public static final String MESSAGE_SUCCESS = "New room added: %1$s";
    public static final String MESSAGE_DUPLICATE_ROOM = "This room was added before!";
    public static final String MESSAGE_ROOM_NAME_NOT_ALLOWED = "This room name is not allowed";

    private final String toAdd;
    private final RoomCost cost;
    private final Tier tier;

    /**
     * Construct a room command with a room name
     * @param roomName: name of the added room.
     */
    public AddRoomCommand(String roomName, RoomCost cost, Tier tier) {
        requireNonNull(roomName);
        requireNonNull(cost);
        requireNonNull(tier);
        this.toAdd = roomName;
        this.cost = cost;
        this.tier = tier;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Room name cannot contain space
        if (this.toAdd.contains(" ") || this.toAdd.equals("")) {
            throw new CommandException(MESSAGE_ROOM_NAME_NOT_ALLOWED);
        }

        if (model.hasRoom(this.toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ROOM);
        }

        model.addRoom(this.toAdd, tier, cost);
        Predicate<Room> predicate = room -> room.hasName(this.toAdd);
        model.updateFilteredRoomList(predicate);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), "room");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddRoomCommand // instanceof handles nulls
                && toAdd.equals(((AddRoomCommand) other).toAdd));
    }
}
