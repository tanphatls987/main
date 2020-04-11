package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKINGID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteBookingCommand;
import seedu.address.logic.parser.exceptions.ParseException;



/**
 * Parses string input to {@code DeleteBookingCommand}
 */
public class DeleteBookingCommandParser implements Parser<DeleteBookingCommand> {

    /**
     * Parses
     * @return an {@code DeleteBookingCommand}
     * @throws ParseException if parser fails.
     */
    @Override
    public DeleteBookingCommand parse(String userInput) throws ParseException {
        ArgumentMultimap mapping =
            ArgumentTokenizer.tokenize(userInput, PREFIX_BOOKINGID);
        if (isAnyPrefixPresent(mapping, PREFIX_BOOKINGID)) {
            ///use ID case
            String bookingId = mapping.getValue(PREFIX_ROOMNUMBER).get();
            return new DeleteBookingCommand(bookingId);
        } else {
            try {
                Index index = ParserUtil.parseIndex(userInput);
                return new DeleteBookingCommand(index);
            } catch (ParseException e) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteBookingCommand.MESSAGE_USAGE), e);
            }
        }
    }

    private boolean isAnyPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    };
}
