package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hotel.Stay;
import seedu.address.model.hotel.booking.Booking;

/**
 * Check in a guest using a booking ID.
 */
public class CheckInByIdCommand extends CheckInCommand {

    public static final String MESSAGE_BOOKING_NOT_EXISTS = "Booking with ID %1$s does not exist.";
    public static final String MESSAGE_BOOKING_TOO_EARLY = "The booking is from %1$s.";
    public static final String MESSAGE_BOOKING_TOO_LATE = "The booking already expired at %1$s";

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

        LocalDateTime fd = booking.get().getTimeFrom();
        LocalDateTime td = booking.get().getTimeTo();

        if (fd.isAfter(LocalDateTime.now())) {
            throw new CommandException(String.format(MESSAGE_BOOKING_TOO_EARLY, fd));
        }

        if (td.isBefore(LocalDateTime.now())) {
            throw new CommandException(String.format(MESSAGE_BOOKING_TOO_LATE, td));
        }

        model.checkIn(new Stay(booking.get(), ""));

        return null;
    }
}
