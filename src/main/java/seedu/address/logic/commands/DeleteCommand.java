package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.either.Either;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.ids.PersonId;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list or a person,"
            + "or by a person ID.\n"
            + "Parameters: INDEX (must be a positive integer)\n."
            + "or Parameters: i/PERSON_ID"
            + "Example: " + COMMAND_WORD + " 1\n"
            + "Example: " + COMMAND_WORD + "i/A000000";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    public static final String MESSAGE_PERSON_NOT_FOUND = "Person id not found.";

    private final Either<Index, PersonId> toDelete;

    public DeleteCommand(Index targetIndex) {
        this.toDelete = Either.ofLeft(targetIndex);
    }

    public DeleteCommand(PersonId id) {
        this.toDelete = Either.ofRight(id);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (this.toDelete.isLeft()) {
            Index targetIndex = this.toDelete.getLeft();
            List<Person> lastShownList = model.getFilteredPersonList();

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deletePerson(personToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete), "guest");
        } else {
            PersonId id = this.toDelete.getRight();
            Optional<Person> personOptional = model.findPersonWithId(id);
            if (personOptional.isEmpty()) {
                throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
            }
            model.deletePerson(personOptional.get());
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personOptional.get()), "guest");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand command = (DeleteCommand) other;
        return this.toDelete.equals(command.toDelete);
    }
}
