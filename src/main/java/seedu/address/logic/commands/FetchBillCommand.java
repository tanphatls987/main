package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.hotel.person.Person;

/**
 * Retrieves the bill of a guest
 */
public class FetchBillCommand extends Command{

    public static final String COMMAND_WORD = "bill";

    /**
     *
     */
    public FetchBillCommandCommand(Person person) {
        requireNonNull(person);
    }
}
