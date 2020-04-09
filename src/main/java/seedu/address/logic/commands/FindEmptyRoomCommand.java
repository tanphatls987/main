package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FROMDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TODATE;

import java.util.function.Predicate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.timeframe.TimeFrame;

/**
 * Find empty room in a period of time.
 */
public class FindEmptyRoomCommand extends Command {
    public static final String COMMAND_WORD = "findemptyroom";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "Find empty rooms in a time period\n"
        + "Parameters" + ": "
        + PREFIX_FROMDATE + " " + "START_DATE"
        + PREFIX_TODATE + " " + "END_DATE" + "\n"
        + "Example: "
        + COMMAND_WORD + " "
        + PREFIX_FROMDATE + "2020-12-12 "
        + PREFIX_TODATE + "2020-12-14 " + "\n";

    public static final String FIND_SUCCESS = "Show available rooms";

    private final TimeFrame timeFrame;

    public FindEmptyRoomCommand(TimeFrame timeFrame) {
        this.timeFrame = timeFrame;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Predicate<Room> roomPredicate =
            room -> {
                boolean clashBooking = model
                    .getBookingList()
                    .stream()
                    .anyMatch(u -> u.getBookDuration().isClash(timeFrame)
                        && u.getRoom().hasName(room.getRoomNum())
                    );
                return (!clashBooking);
            };
        model.updateFilteredRoomList(roomPredicate);
        return new CommandResult(FIND_SUCCESS);
    }
}
