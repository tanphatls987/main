package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddGuestCommand;
import seedu.address.logic.commands.AddRoomCommand;
import seedu.address.logic.commands.AddServiceCommand;
import seedu.address.logic.commands.AddTierCommand;
import seedu.address.logic.commands.ChargeServiceCommand;
import seedu.address.logic.commands.CheckInCommand;
import seedu.address.logic.commands.CheckOutCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteRoomCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FetchBillCommand;
import seedu.address.logic.commands.FindGuestCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.commands.ReserveCommand;
import seedu.address.logic.commands.SetRoomCostCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddGuestCommand.COMMAND_WORD:
            return new AddGuestCommandParser().parse(arguments);

        case AddRoomCommand.COMMAND_WORD:
            return new AddRoomCommandParser().parse(arguments);

        case AddServiceCommand.COMMAND_WORD:
            return new AddServiceCommandParser().parse(arguments);

        case CheckInCommand.COMMAND_WORD:
            return new CheckInCommandParser().parse(arguments);

        case CheckOutCommand.COMMAND_WORD:
            return new CheckOutCommandParser().parse(arguments);

        case AddTierCommand.COMMAND_WORD:
            return new AddTierCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DeleteRoomCommand.COMMAND_WORD:
            return new DeleteRoomCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FetchBillCommand.COMMAND_WORD:
            return new FetchBillCommandParser().parse(arguments);

        case FindGuestCommand.COMMAND_WORD:
            return new FindGuestCommandParser().parse(arguments);

        case RemarkCommand.COMMAND_WORD:
            return new RemarkCommandParser().parse(arguments);

        case ReserveCommand.COMMAND_WORD:
            return new ReserveCommandParser().parse(arguments);

        case ChargeServiceCommand.COMMAND_WORD:
            return new ChargeServiceCommandParser().parse(arguments);

        case SetRoomCostCommand.COMMAND_WORD:
            return new SetRoomCostCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
