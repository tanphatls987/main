package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SwitchViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser for switch view command.
 */
public class SwitchViewCommandParser implements Parser<SwitchViewCommand> {
    @Override
    public SwitchViewCommand parse(String userInput) throws ParseException {
        userInput = userInput.trim();
        if (userInput.isEmpty()) {
            throw new ParseException(String.format(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SwitchViewCommand.MESSAGE_USAGE)
            ));
        }
        return new SwitchViewCommand(userInput.trim());
    }
}
