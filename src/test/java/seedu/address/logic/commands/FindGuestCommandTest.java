package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.hotel.person.MatchNamePredicate;
import seedu.address.model.hotel.person.MatchPersonIdPredicate;
import seedu.address.model.hotel.person.Name;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.ids.PersonId;

/**
 * Contains integration tests (interaction with the Model) for {@code FindGuestCommand}.
 */
public class FindGuestCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Predicate<Person> firstPredicate = preparePredicate(List.of(new Name("Alice")),
            List.of(new PersonId("A0")));
        MatchNamePredicate secondPredicate = new MatchNamePredicate(List.of(new Name("Bob")));

        FindGuestCommand findFirstCommand = new FindGuestCommand(firstPredicate);
        FindGuestCommand findSecondCommand = new FindGuestCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindGuestCommand findFirstCommandCopy = new FindGuestCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }


    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        Predicate<Person> predicate = preparePredicate(
            List.of(ALICE.getName(), BENSON.getName()),
            List.of(CARL.getPersonId())
        );
        FindGuestCommand command = new FindGuestCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL), model.getFilteredPersonList());

    }

    private Predicate<Person> preparePredicate(List<Name> nameList, List<PersonId> personIdList) {
        return new MatchNamePredicate(nameList).or(new MatchPersonIdPredicate(personIdList));
    }
}
