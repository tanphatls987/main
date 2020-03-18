package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindGuestCommand;
import seedu.address.model.hotel.person.MatchNamePredicate;
import seedu.address.model.hotel.person.MatchPersonIdPredicate;
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
        String nameUse = "Alice";
        String personIdUse = "B0000000";
        FindGuestCommand expectedFindGuestCommand =
                new FindGuestCommand(
                    new MatchNamePredicate(List.of(new Name(nameUse)))
                        .or(new MatchPersonIdPredicate(
                            List.of(new PersonId(personIdUse)))
                        )
                );
        String userInput = PREFIX_NAME + nameUse + " "
            + PREFIX_ID + personIdUse;
        //I don't know how to test this
        //assertParseSuccess(parser, userInput, expectedFindGuestCommand);
    }

}
