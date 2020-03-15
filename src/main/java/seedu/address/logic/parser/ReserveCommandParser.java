package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROMDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TODATE;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddGuestCommand;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.commands.ReserveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.hotel.Room;
import seedu.address.model.hotel.person.Email;
import seedu.address.model.hotel.person.Name;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.hotel.person.Phone;
import seedu.address.model.hotel.person.Remark;
import seedu.address.model.ids.PersonId;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddGuestCommand object
 */
public class ReserveCommandParser implements Parser<ReserveCommand> {


    /**
     * Parses the given {@code String} of arguments in the context of the AddGuestCommand
     * and returns an AddGuestCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ReserveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ROOMNUMBER, PREFIX_FROMDATE, PREFIX_TODATE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReserveCommand.MESSAGE_USAGE), ive);
        }

       String roomNum = ParserUtil.parseRoom(argMultimap.getValue(PREFIX_ROOMNUMBER).get());
       LocalDate fromDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_FROMDATE).get());
       LocalDate toDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_TODATE).get());

        return new ReserveCommand(index, roomNum, fromDate, toDate);
    }
}
