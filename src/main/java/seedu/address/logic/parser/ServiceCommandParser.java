package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;

import java.util.stream.Stream;

import seedu.address.logic.commands.ServiceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.hotel.bill.Cost;
import seedu.address.model.hotel.bill.Service;
import seedu.address.model.ids.PersonId;
import seedu.address.model.ids.RoomId;

/**
 * Parses input arguments and creates a new ServiceCommand object
 */
public class ServiceCommandParser implements Parser<ServiceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ServiceCommand
     * and returns an ServiceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ServiceCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_ROOMNUMBER, PREFIX_DESCRIPTION, PREFIX_COST);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_ROOMNUMBER, PREFIX_DESCRIPTION, PREFIX_COST)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            ServiceCommand.MESSAGE_USAGE)
            );
        }
        PersonId personId = ParserUtil.parsePersonId(argMultimap.getValue(PREFIX_ID).get());
        RoomId roomNum = ParserUtil.parseRoom(argMultimap.getValue(PREFIX_ROOMNUMBER).get());
        String description = argMultimap.getValue(PREFIX_DESCRIPTION).get();
        Cost cost = ParserUtil.parseCost(argMultimap.getValue(PREFIX_COST).get());

        Service service = new Service(description, cost);

        return new ServiceCommand(personId, roomNum, service);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
