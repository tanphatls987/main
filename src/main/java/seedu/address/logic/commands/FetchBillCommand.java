package seedu.address.logic.commands;

//import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
//import seedu.address.model.hotel.person.Person;

/**
 * Retrieves the bill of a guest
 */
public class FetchBillCommand extends Command {

    public static final String COMMAND_WORD = "bill";

    public static final String MESSAGE_SUCCESS = "This is the bill for the guest.";

    /**
     *
     */
    public FetchBillCommand() {
        //requireNonNull(person);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}
