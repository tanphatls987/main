package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIER;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddRoomCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.hotel.bill.Cost;
import seedu.address.model.hotel.bill.RoomCost;
import seedu.address.model.hotel.room.Tier;


/**
 * Parses input arguments and creates a new AddRoomCommand object
 */
public class AddRoomCommandParser implements Parser<AddRoomCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddRoomCommand
     * and returns an AddRoomCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddRoomCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ROOMNUMBER, PREFIX_COST, PREFIX_TIER);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROOMNUMBER, PREFIX_COST, PREFIX_TIER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            AddRoomCommand.MESSAGE_USAGE)
            );
        }
        if (!Tier.isTierOption(argMultimap.getValue(PREFIX_TIER).get())) {
            throw new ParseException(Tier.MESSAGE_INVALID_TIER);
        }

        String roomNum = argMultimap.getValue(PREFIX_ROOMNUMBER).get();
        RoomCost cost = new RoomCost(ParserUtil.parseCost(argMultimap.getValue(PREFIX_COST).get()).toString());
        Tier tier = ParserUtil.parseTier(argMultimap.getValue(PREFIX_TIER).get());
        return new AddRoomCommand(roomNum, cost, tier);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
