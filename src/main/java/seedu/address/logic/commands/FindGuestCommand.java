package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.hotel.person.Person;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindGuestCommand extends Command {

    public static final String COMMAND_WORD = "findguest";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": return all people with matching id or matching name"
            + "Parameters: [i/ID] ... [i/ID] [n/KEYWORD] ... [n/KEYWORD] \n"
            + "Example: " + COMMAND_WORD
            + PREFIX_ID + "A0000000" + " "
            + PREFIX_ID + "B0000000" + " "
            + PREFIX_NAME + "Alice";

    private final Predicate<Person> predicate;

    public FindGuestCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindGuestCommand // instanceof handles nulls
                && predicate.equals(((FindGuestCommand) other).predicate)); // state check
    }
}
