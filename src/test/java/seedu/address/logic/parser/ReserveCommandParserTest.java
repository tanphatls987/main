package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROMDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TODATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ReserveCommand;
import seedu.address.model.hotel.Room;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.ids.PersonId;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.RoomBuilder;

public class ReserveCommandParserTest {
    private ReserveCommandParser parser = new ReserveCommandParser();
    private final Room ROOM = new RoomBuilder().build();
    private final Person PAYEE = new PersonBuilder().build();
    private final LocalDateTime FROM_DATE = LocalDateTime.parse("2020-12-12");
    private final LocalDateTime TO_DATE = LocalDateTime.parse("2020-12-23");
    private final PersonId ID = PAYEE.getPersonId();

    @Test
    public void parse_personIdSpecified_success() {
        String userInput = PREFIX_ID + ID.toString()
                + PREFIX_ROOMNUMBER + ROOM.getRoomNum()
                + PREFIX_FROMDATE + FROM_DATE
                + PREFIX_TODATE + TO_DATE;
        ReserveCommand expectedCommand = new ReserveCommand(ID, ROOM.getRoomNum(), FROM_DATE, TO_DATE);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReserveCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, ReserveCommand.COMMAND_WORD, expectedMessage);

        // no personId
        String userInput = PREFIX_ROOMNUMBER + ROOM.getRoomNum() + PREFIX_FROMDATE
                + FROM_DATE + PREFIX_TODATE + TO_DATE;
        assertParseFailure(parser, ReserveCommand.COMMAND_WORD + userInput, expectedMessage);

        //no room number
        String input = PREFIX_ID + ID.toString() + PREFIX_FROMDATE
                + FROM_DATE + PREFIX_TODATE + TO_DATE;
        assertParseFailure(parser, ReserveCommand.COMMAND_WORD + input, expectedMessage);

        //no dates
        String userinput = PREFIX_ID + ID.toString() + PREFIX_ROOMNUMBER + ROOM.getRoomNum();
        assertParseFailure(parser, ReserveCommand.COMMAND_WORD + userinput, expectedMessage);
    }
    //add invalid value tests
}
