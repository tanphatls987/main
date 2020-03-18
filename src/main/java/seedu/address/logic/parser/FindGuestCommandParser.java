package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindGuestCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.hotel.person.MatchNamePredicate;
import seedu.address.model.hotel.person.MatchPersonIdPredicate;
import seedu.address.model.hotel.person.Name;
import seedu.address.model.ids.PersonId;

/**
 * Parses input arguments and creates a new FindGuestCommand object
 */
public class FindGuestCommandParser implements Parser<FindGuestCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindGuestCommand
     * and returns a FindGuestCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindGuestCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID);

        if (!isAnyPrefixPresent(argMultimap, PREFIX_NAME, PREFIX_ID)) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindGuestCommand.MESSAGE_USAGE)
            );
        }

        List<PersonId> personIdList = argMultimap.getAllValues(PREFIX_ID)
            .stream()
            .map(PersonId::new)
            .collect(Collectors.toList());
        List<Name> nameList = argMultimap.getAllValues(PREFIX_NAME)
            .stream()
            .map(Name::new)
            .collect(Collectors.toList());
        return new FindGuestCommand(new MatchNamePredicate(nameList)
            .or(new MatchPersonIdPredicate(personIdList))
        );
    }

    private boolean isAnyPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    };

}
