package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TODATE;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.address.logic.commands.CheckInCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ids.PersonId;
import seedu.address.model.ids.RoomId;

/**
 * Parses input arguments and creates a new CheckInCommand object
 */
public class CheckInCommandParser implements Parser<CheckInCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CheckInCommand
     * @return an CheckInCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CheckInCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_ROOMNUMBER, PREFIX_TODATE);

        if ((!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_ROOMNUMBER, PREFIX_TODATE)
            && (!arePrefixesPresent(argMultimap, PREFIX_BOOKING))
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CheckInCommand.MESSAGE_USAGE)
            );
        }

        PersonId personId = ParserUtil.parsePersonId(argMultimap.getValue(PREFIX_ID).get());
        RoomId roomId = ParserUtil.parseRoom(argMultimap.getValue(PREFIX_ROOMNUMBER).get());
        LocalDateTime toDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_TODATE).get());

        return new CheckInCommand(personId, roomId, toDate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
