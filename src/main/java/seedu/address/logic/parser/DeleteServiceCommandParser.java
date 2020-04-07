package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICEID;

import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteServiceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ids.AvailableServiceId;

/**
 * Parses input arguments and creates a new DeleteServiceCommand
 */
public class DeleteServiceCommandParser implements Parser<DeleteServiceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteRoomCommand
     * and returns an DeleteRoomCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteServiceCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SERVICEID);

        if (!arePrefixesPresent(argMultimap, PREFIX_SERVICEID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            DeleteServiceCommand.MESSAGE_USAGE)
            );
        }
        String id = argMultimap.getValue(PREFIX_SERVICEID).get();
        return new DeleteServiceCommand(new AvailableServiceId(id));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
