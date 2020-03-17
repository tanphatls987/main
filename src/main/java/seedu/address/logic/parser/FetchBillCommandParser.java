package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;

import java.util.stream.Stream;

import seedu.address.logic.commands.FetchBillCommand;
import seedu.address.logic.commands.ReserveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ids.PersonId;
import seedu.address.model.ids.RoomId;

/**
 * Parses input arguments and creates a new FetchBillCommand object
 */
public class FetchBillCommandParser implements Parser<FetchBillCommand> {


    /**
     * Parses the given {@code String} of arguments in the context of the FetchBillCommand
     * and returns an FetchBillCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FetchBillCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_ROOMNUMBER);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReserveCommand.MESSAGE_USAGE)
            );
        }

        PersonId personId = ParserUtil.parsePersonId(argMultimap.getValue(PREFIX_ID).get());

        if (arePrefixesPresent(argMultimap, PREFIX_ROOMNUMBER)) {
            RoomId roomNum = ParserUtil.parseRoom(argMultimap.getValue(PREFIX_ROOMNUMBER).get());

            return new FetchBillCommand(personId, roomNum);
        } else {
            return new FetchBillCommand(personId);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
