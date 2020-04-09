package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROMDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TODATE;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindEmptyRoomCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.timeframe.TimeFrame;

/**
 * Parser for find empty room command.
 */
public class FindEmptyRoomCommandParser implements Parser<FindEmptyRoomCommand> {
    @Override
    public FindEmptyRoomCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
            PREFIX_FROMDATE, PREFIX_TODATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_FROMDATE, PREFIX_TODATE)) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEmptyRoomCommand.MESSAGE_USAGE)
            );
        }
        LocalDateTime fromDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_FROMDATE).get());
        LocalDateTime toDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_TODATE).get());
        return new FindEmptyRoomCommand(new TimeFrame(fromDate, toDate));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
