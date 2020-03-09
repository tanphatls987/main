package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.hotel.Hotel;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.hotel.person.Person;

/**
 * Add a guest to the hotel.
 */
public class AddGuestCommand implements HotelCommand {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to guest list. "
        + "Parameters: "
        + PREFIX_NAME + "NAME "
        + PREFIX_ID + "ID "
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "John Doe "
        + PREFIX_ID + "C0000100";

    public static final String MESSAGE_SUCCESS = "New guest added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in guest list";


    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddGuestCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Hotel hotel) throws CommandException {
        requireNonNull(hotel);

        if (hotel.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        hotel.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddGuestCommand // instanceof handles nulls
            && toAdd.equals(((AddGuestCommand) other).toAdd));
    }
}
