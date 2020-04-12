package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TODATE;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.address.logic.commands.ExtendCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ids.RoomId;

/**
 * Parses input arguments and creates a new ExtendCommand object.
 */
public class ExtendCommandParser implements Parser<ExtendCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ExtendCommand
     * @return an ExtendCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ExtendCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_ROOMNUMBER, PREFIX_TODATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROOMNUMBER, PREFIX_TODATE)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ExtendCommand.MESSAGE_USAGE)
            );
        }

        RoomId roomId = ParserUtil.parseRoom(argMultimap.getValue(PREFIX_ROOMNUMBER).get());
        LocalDateTime toDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_TODATE).get());

        return new ExtendCommand(roomId, toDate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
