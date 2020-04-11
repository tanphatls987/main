package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.either.Either;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hotel.booking.Booking;



/**
 * Deletes a reservation
 */
public class DeleteBookingCommand extends Command {
    public static final String COMMAND_WORD = "deletebooking";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the booking identified by the index number used in the displayed booking list, or by booking"
            + " id.\n"
            + "Parameters: INDEX (must be a positive integer) or [bi/BOOKING_ID]\n"
            + "Example: " + COMMAND_WORD + " 1\n"
            + "Example: " + COMMAND_WORD + " bi/123455679";

    public static final String MESSAGE_INDEX_INVALID = "This index is not valid";

    public static final String MESSAGE_DELETE_BOOKING_SUCCESS = "Deleted Booking: %1$s";

    public static final String MESSAGE_BOOKING_ID_NOT_FOUND = "Booking id not found!";

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
            Optional<Booking> bookingOptional = model.findBookingById(id);
            if (bookingOptional.isEmpty()) {
                throw new CommandException(MESSAGE_BOOKING_ID_NOT_FOUND);
            }
            Booking booking = bookingOptional.get();
            model.deleteBooking(booking);
            return new CommandResult(String.format(MESSAGE_DELETE_BOOKING_SUCCESS, booking.getBookingId()));
        } else {
            Index index = this.toDelete.getLeft();
            List<Booking> lastShownList = model.getFilteredBookingList();
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(MESSAGE_INDEX_INVALID);
            }
            Booking booking = lastShownList.get(index.getZeroBased());
            model.deleteBooking(booking);
            return new CommandResult(String.format(MESSAGE_DELETE_BOOKING_SUCCESS, booking.getBookingId()));
        }
    }
}
