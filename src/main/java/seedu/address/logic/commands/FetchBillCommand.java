package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.ids.PersonId;

/**
 * Retrieves the bill of a guest
 */
public class FetchBillCommand extends Command {

    public static final String COMMAND_WORD = "bill";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Retrieves the bill of a guest.\n"
            + "Parameters:"
            + PREFIX_ID + "NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "J0000000";

    public static final String MESSAGE_SUCCESS = "This is the bill for %1$s.";
    public static final String MESSAGE_GUEST_NONEXISTENT = "The guest does not exist in the database.";

    private final PersonId personId;

    /**
     * @param personId to fetch the bill of
     */
    public FetchBillCommand(PersonId personId) {
        requireNonNull(personId);
        this.personId = personId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Person> person = model.findPersonWithId(personId);

        if (!model.hasPerson(person)) {
            throw new CommandException(MESSAGE_GUEST_NONEXISTENT);
        }

        model.fetchBill(personId);
        return new CommandResult(String.format(MESSAGE_SUCCESS, personId));
    }
}
