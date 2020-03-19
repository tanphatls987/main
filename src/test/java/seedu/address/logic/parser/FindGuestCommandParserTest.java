package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindGuestCommand;
import seedu.address.model.hotel.person.Name;
import seedu.address.model.ids.PersonId;

public class FindGuestCommandParserTest {

    private FindGuestCommandParser parser = new FindGuestCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindGuestCommand.MESSAGE_USAGE)
        );
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        HashSet<Name> nameUse = new HashSet<>();
        nameUse.add(new Name("Alice"));
        nameUse.add(new Name("Bob"));

        HashSet<PersonId> personIdUse = new HashSet<>();
        personIdUse.add(new PersonId("B000000"));
        personIdUse.add(new PersonId("D000000"));
        StringBuilder userInput = new StringBuilder();

        nameUse.forEach(u -> userInput.append(PREFIX_NAME + u.fullName + " "));
        personIdUse.forEach(u -> userInput.append(PREFIX_ID + u.toString() + " "));

        assertEquals(userInput.toString(), "n/Bob n/Alice i/B000000 i/D000000 ");

        FindGuestCommand expectedFindGuestCommand = new FindGuestCommand(nameUse, personIdUse);
        assertParseSuccess(parser, " " + userInput.toString(), expectedFindGuestCommand);
    }

}
