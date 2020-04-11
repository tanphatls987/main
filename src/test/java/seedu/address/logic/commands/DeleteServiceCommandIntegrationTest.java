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
 * Integration test, interacting with model
 */
public class DeleteServiceCommandIntegrationTest {
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
        AvailableService service = new AvailableService(description, cost, id);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                model.getHotel(), model.getBookKeeper());
        expectedModel.addAvailableService(service);
        expectedModel.deleteAvailableService(id);
        model.addAvailableService(service);

        assertCommandSuccess(new DeleteServiceCommand(id), model,
                String.format(DeleteServiceCommand.MESSAGE_SUCCESS, id.toString()), expectedModel);
    }

    @Test
    public void execute_serviceNotFound() {
        String description = "Wash clothes";
        AvailableServiceId id = new AvailableServiceId("WC");
        Cost cost = new Cost("100.00");
        AvailableService service = new AvailableService(description, cost, id);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                model.getHotel(), model.getBookKeeper());
        expectedModel.addAvailableService(service);
        expectedModel.deleteAvailableService(id);

        assertCommandFailure(new DeleteServiceCommand(id), model,
                String.format(DeleteServiceCommand.MESSAGE_SERVICE_NOT_FOUND, id.toString()));
    }
}
