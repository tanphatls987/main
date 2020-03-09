package seedu.address.logic.commands;

import seedu.address.hotel.Hotel;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Represent a command to be executed on Hotel object.
 */
public interface HotelCommand {
    public CommandResult execute(Hotel hotel) throws CommandException;
}
