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
    private final Room Room = new RoomBuilder().build();
    private final Person Payee = new PersonBuilder().build();
    private final LocalDateTime From_Date = LocalDateTime.parse("2020-12-12T12:30:00");
    private final LocalDateTime To_Date = LocalDateTime.parse("2020-12-23T12:30:00");
    private final PersonId Id = Payee.getPersonId();

    @Test
    public void parse_personIdSpecified_success() {
        String userInput = PREFIX_ID + Id.toString() + " "
                + PREFIX_ROOMNUMBER + Room.getRoomNum() + " "
                + PREFIX_FROMDATE + From_Date + " "
                + PREFIX_TODATE + To_Date;
        ReserveCommand expectedCommand = new ReserveCommand(Id, Room.getRoomNum(), From_Date, To_Date);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReserveCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, ReserveCommand.COMMAND_WORD, expectedMessage);

        // no personId
        String userInput = PREFIX_ROOMNUMBER + Room.getRoomNum() + PREFIX_FROMDATE
                + From_Date + PREFIX_TODATE + To_Date;
        assertParseFailure(parser, ReserveCommand.COMMAND_WORD + userInput, expectedMessage);

        //no room number
        String input = PREFIX_ID + Id.toString() + PREFIX_FROMDATE
                + From_Date + PREFIX_TODATE + To_Date;
        assertParseFailure(parser, ReserveCommand.COMMAND_WORD + input, expectedMessage);

        //no dates
        String userinput = PREFIX_ID + Id.toString() + PREFIX_ROOMNUMBER + Room.getRoomNum();
        assertParseFailure(parser, ReserveCommand.COMMAND_WORD + userinput, expectedMessage);
    }
    //add invalid value tests
}
