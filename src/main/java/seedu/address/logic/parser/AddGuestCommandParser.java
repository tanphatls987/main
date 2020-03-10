package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddGuestCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.hotel.person.Name;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.ids.PersonId;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddGuestCommandParser implements Parser<AddGuestCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddGuestCommand
     * and returns an AddGuestCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddGuestCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ID)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        PersonId personId = ParserUtil.parsePersonId(argMultimap.getValue(PREFIX_ID).get());
        //Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());

        Person person = new Person(name, personId);

        return new AddGuestCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
