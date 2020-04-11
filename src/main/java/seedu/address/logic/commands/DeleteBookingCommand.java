package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.either.Either;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a reservation
 */
public class DeleteBookingCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list, or by booking"
            + " id.\n"
            + "Parameters: INDEX (must be a positive integer) or [bi/BOOKING_ID]\n"
            + "Example: " + COMMAND_WORD + " 1\n"
            + "Example: " + COMMAND_WORD + " bi/123455679";

    public static final String MESSAGE_DELETE_BOOKING_SUCCESS = "Deleted Booking: %1$s";
    private Either<Index, String> toDelete;

    public DeleteBookingCommand(Index index) {
        this.toDelete = Either.ofLeft(index);
    }

    public DeleteBookingCommand(String bookingId) {
        this.toDelete = Either.ofRight(bookingId);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (this.toDelete.isRight()) {
            String id = this.toDelete.getRight();

        } else {
            Index index = this.toDelete.getLeft();
        }
    }
}
