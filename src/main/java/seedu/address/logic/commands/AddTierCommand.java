package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIER;

import java.util.ArrayList;
import java.util.function.Function;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyHotel;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.hotel.room.Tier;


/**
 * Add a tier, can grouped with list of rooms.
 */
public class AddTierCommand extends Command {
    public static final String COMMAND_WORD = "addtier";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tier name.\n"
            + "Parameters: "
            + PREFIX_TIER + "TIER_NAME "
            + PREFIX_ROOMNUMBER + "ROOM_NUM\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TIER + "Gold "
            + PREFIX_ROOMNUMBER + "12E 12F 12T";

    public static final String MESSAGE_SUCCESS = "New tier added: %1$s";
    public static final String MESSAGE_DUPLICATE_TIER = "This tier name was added before!";
    public static final String MESSAGE_ROOM_NOT_FOUND = "One of the rooms does not exist in the hotel!";
    public static final String MESSAGE_ROOM_HAS_TIER = "Some rooms have already have a tier,"
            + "unable to add tiers to them!";

    private final Tier toAdd;
    private final ArrayList<String> roomNums;

    /**
     * Construct a AddTierCommand with tier and list of rooms.
     * @param tier: tier
     * @param roomNums: list of room numbers.
     */
    public AddTierCommand(Tier tier, ArrayList<String> roomNums) {
        requireNonNull(tier);

        this.toAdd = tier;
        this.roomNums = roomNums;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTier(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TIER);
        }

        ReadOnlyHotel hotel = model.getHotel();

        for (String roomNum: roomNums) {
            if (!model.hasRoom(roomNum)) {
                throw new CommandException(MESSAGE_ROOM_NOT_FOUND);
            }
        }

        Function<String, Room> findRoom = (String s) -> {
            for (Room room: hotel.getRoomList()) {
                if (room.hasName(s)) {
                    return room;
                }
            }
            return null;
        };

        for (String roomNum: roomNums) {
            if (model.hasRoom(roomNum)) {
                Room current = findRoom.apply(roomNum);
                assert current != null;
                if (current.hasNonDefaultTier()) {
                    throw new CommandException(MESSAGE_ROOM_HAS_TIER);
                }
            }
        }

        model.addTier(this.toAdd, this.roomNums);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other instanceof AddTierCommand) {
            Tier otherTier = ((AddTierCommand) other).toAdd;
            ArrayList otherRoomNums = ((AddTierCommand) other).roomNums;
            return this.toAdd.equals(otherTier) && this.roomNums.equals(otherRoomNums);
        }

        return false;
    }
}
