package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;

import java.util.stream.Stream;

import seedu.address.logic.commands.SetRoomCostCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.hotel.bill.Cost;

/**
 * Parses input arguments and creates a new SetRoomCostCommand object
 */
public class SetRoomCostCommandParser implements Parser<SetRoomCostCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SetRoomCostCommand
     * and returns an SetRoomCostCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetRoomCostCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ROOMNUMBER, PREFIX_COST);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROOMNUMBER, PREFIX_COST)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetRoomCostCommand.MESSAGE_USAGE)
            );
        }

        String roomNum = argMultimap.getValue(PREFIX_ROOMNUMBER).get();
        Cost cost = ParserUtil.parseCost(argMultimap.getValue(PREFIX_COST).get());

        return new SetRoomCostCommand(roomNum, cost);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
