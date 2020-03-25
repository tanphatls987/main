package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
//import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FetchBillCommand;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.ids.PersonId;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.RoomBuilder;

public class FetchBillCommandParserTest {
    private static final Person Guest = new PersonBuilder().build();
    private static final PersonId PersonId = Guest.getPersonId();
    private static final Room Room = new RoomBuilder().build();
    private static final String RoomNum = Room.getRoomNum();

    private FetchBillCommandParser parser = new FetchBillCommandParser();
    /*
    @Test
    public void parse_allFieldsPresent_success() {
        // with roomId
        String userInput = FetchBillCommand.COMMAND_WORD + " "
                + PREFIX_ID + " " + PersonId.toString()
                + PREFIX_ROOMNUMBER + Room.getRoomNum();
        FetchBillCommand expectedCommand = new FetchBillCommand(PersonId, RoomNum);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // without roomId
        String userInput = FetchBillCommand.COMMAND_WORD + " "
                + PREFIX_ID + " " + PersonId.toString();
        FetchBillCommand expectedCommand = new FetchBillCommand(PersonId);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
    */
    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FetchBillCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, FetchBillCommand.COMMAND_WORD, expectedMessage);

        // no personId
        assertParseFailure(parser, FetchBillCommand.COMMAND_WORD + " "
                + PREFIX_ROOMNUMBER + " 101", expectedMessage);
    }
}
