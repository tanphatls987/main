package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddServiceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.hotel.bill.Cost;
import seedu.address.model.ids.AvailableServiceId;

/**
 *  Parses input arguments ad creates new AddServiceCommand object.
 */
public class AddServiceCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the AddServiceCommand
     * and returns an AddServiceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddServiceCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_COST, PREFIX_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_COST, PREFIX_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            AddServiceCommand.MESSAGE_USAGE)
            );
        }
        Cost cost = ParserUtil.parseCost(argMultimap.getValue(PREFIX_COST).get());
        String description = argMultimap.getValue(PREFIX_DESCRIPTION).get();
        AvailableServiceId id = ParserUtil.parseAvailableServiceId(argMultimap.getValue(PREFIX_ID).get());

        return new AddServiceCommand(description, cost, id);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} valueis in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
