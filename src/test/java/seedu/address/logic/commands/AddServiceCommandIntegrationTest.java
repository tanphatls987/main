package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.BookKeeper;
import seedu.address.model.Hotel;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.hotel.bill.AvailableService;
import seedu.address.model.hotel.bill.Cost;
import seedu.address.model.ids.AvailableServiceId;

/**
 * Contains integration tests (interaction with the Model) for {@code AddServiceCommand}.
 */
public class AddServiceCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new Hotel(), new BookKeeper());
    }

    @Test
    public void execute_success() {
        String description = "Wash clothes";
        AvailableServiceId id = new AvailableServiceId("WC");
        Cost cost = new Cost("100.00");

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                model.getHotel(), model.getBookKeeper());
        expectedModel.addAvailableService(new AvailableService(description, cost, id));

        assertCommandSuccess(new AddServiceCommand(description, cost, id), model,
                String.format(AddServiceCommand.MESSAGE_SUCCESS, description, id), expectedModel);
    }

    @Test
    public void execute_duplicatedService() {
        String description = "Wash clothes";
        AvailableServiceId id = new AvailableServiceId("WC");
        Cost cost = new Cost("100.00");

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                model.getHotel(), model.getBookKeeper());
        expectedModel.addAvailableService(new AvailableService(description, cost, id));
        model.addAvailableService(new AvailableService(description, cost, id));

        assertCommandFailure(new AddServiceCommand(description, cost, id), model, AddServiceCommand.DUPLICATED_MESSAGE);
    }
}
