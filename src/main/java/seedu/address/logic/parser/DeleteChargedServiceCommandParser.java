package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICEID;

import java.util.stream.Stream;

import seedu.address.logic.commands.ChargeServiceCommand;
import seedu.address.logic.commands.DeleteChargedServiceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ids.AvailableServiceId;
import seedu.address.model.ids.PersonId;
import seedu.address.model.ids.RoomId;

/**
 * Parses input arguments and creates a new DeleteChargesServiceCommand object
 */
public class DeleteChargedServiceCommandParser implements Parser<DeleteChargedServiceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteChargedServiceCommand
     * and returns a DeleteChargedServiceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteChargedServiceCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_ROOMNUMBER, PREFIX_SERVICEID);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_ROOMNUMBER, PREFIX_SERVICEID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            ChargeServiceCommand.MESSAGE_USAGE)
            );
        }
        PersonId personId = ParserUtil.parsePersonId(argMultimap.getValue(PREFIX_ID).get());
        RoomId roomNum = ParserUtil.parseRoom(argMultimap.getValue(PREFIX_ROOMNUMBER).get());
        AvailableServiceId serviceId = ParserUtil.parseAvailableServiceId(argMultimap.getValue(PREFIX_SERVICEID).get());

        return new DeleteChargedServiceCommand(personId, roomNum, serviceId);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
