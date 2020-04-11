package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.hotel.person.Person;
import seedu.address.testutil.PersonBuilder;


/**
 * Contains integration tests (interaction with the Model) for {@code AddGuestCommand}.
 */
public class AddGuestCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        ///avoid same person id
        Person validPerson = new PersonBuilder().withPersonId("somethingridiculous").build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                model.getHotel(), model.getBookKeeper());
        expectedModel.addPerson(validPerson);
        Predicate<Person> personPredicate = person -> person.getPersonId().equals(validPerson.getPersonId());
        expectedModel.updateFilteredPersonList(personPredicate);

        assertCommandSuccess(new AddGuestCommand(validPerson), model,
                String.format(AddGuestCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddGuestCommand(personInList), model, AddGuestCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
