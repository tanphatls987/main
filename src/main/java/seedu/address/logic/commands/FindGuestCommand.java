package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.HashSet;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.hotel.person.MatchNamePredicate;
import seedu.address.model.hotel.person.MatchPersonIdPredicate;
import seedu.address.model.hotel.person.Name;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.ids.PersonId;

/**
 * Finds and lists all persons in address book whose match specified names of ids.
 */
public class FindGuestCommand extends Command {

    public static final String COMMAND_WORD = "findguest";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": return all people with matching id or matching name.\n"
            + "Parameters: [i/ID] ... [i/ID] [n/KEYWORD] ... [n/KEYWORD] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "A0000000" + " "
            + PREFIX_ID + "B0000000" + " "
            + PREFIX_NAME + "Alice";

    private final HashSet<Name> nameList;
    private final HashSet<PersonId> personIdList;

    /**
     * Constructor for findguestcommand.
     * @param nameList
     * @param personIdList
     */
    public FindGuestCommand(HashSet<Name> nameList, HashSet<PersonId> personIdList) {
        this.nameList = nameList;
        this.personIdList = personIdList;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        Predicate<Person> predicate = new MatchNamePredicate(nameList).or(
            new MatchPersonIdPredicate(personIdList)
        );
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof FindGuestCommand)) {
            return false;
        }
        FindGuestCommand othCommand = (FindGuestCommand) other;
        return othCommand.nameList.equals(nameList)
            && othCommand.personIdList.equals(personIdList);
    }

    @Override
    public String toString() {
        return nameList.toString() + " " + personIdList.toString();
    }
}
