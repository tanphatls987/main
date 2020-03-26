package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTierCommand;
import seedu.address.model.hotel.room.Room;
import seedu.address.testutil.TypicalRooms;

/**
 * Test parsers
 */
public class AddTierCommandParserTest {
    private AddTierCommandParser parser = new AddTierCommandParser();

    @Test
    public void parse_missingCompulsoryFields_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTierCommand.MESSAGE_USAGE);
        List<Room> rooms = TypicalRooms.getTypicalRooms();

        //no parameters
        assertParseFailure(parser, AddTierCommand.COMMAND_WORD, expectedMessage);

        //no tier name
        String userInput = PREFIX_ROOMNUMBER + rooms.toString();
        assertParseFailure(parser, AddTierCommand.COMMAND_WORD + userInput, expectedMessage);

        //no room number
        userInput = PREFIX_TIER + " gold";
        assertParseFailure(parser, AddTierCommand.COMMAND_WORD + userInput, expectedMessage);
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTierCommand.MESSAGE_USAGE));
    }
}
