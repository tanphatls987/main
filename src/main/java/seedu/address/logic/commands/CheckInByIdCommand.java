package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hotel.Stay;
import seedu.address.model.hotel.booking.Booking;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class CheckInByIdCommand extends CheckInCommand {

    public static final String MESSAGE_BOOKING_NOT_EXISTS = "Booking with ID %1$s does not exist.";

    private String bookingId;

    public CheckInByIdCommand(String bookingId) {
        requireNonNull(bookingId);

        this.bookingId = bookingId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        Optional<Booking> booking = model.findBookingById(bookingId);

        if (booking.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_BOOKING_NOT_EXISTS, bookingId));
        }

        model.checkIn(new Stay(booking.get(), ""));

        return null;
    }
}
