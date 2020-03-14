package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.FetchBillCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class FetchBillCommandParser implements Parser<FetchBillCommand>{


    /**
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FetchBillCommand parse(String args) throws ParseException {
        requireNonNull(args);

        return new FetchBillCommand();
    }
}
