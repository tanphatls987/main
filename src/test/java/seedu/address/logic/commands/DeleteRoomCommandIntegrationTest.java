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


/**
 * Integration test, interacting with model
 */
public class DeleteRoomCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new Hotel(), new BookKeeper());
    }

    @Test
    public void execute_success() {
        String roomName = "13E";
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                model.getHotel(), model.getBookKeeper());
        expectedModel.addRoom(roomName);
        expectedModel.deleteRoom(roomName);
        model.addRoom(roomName);

        assertCommandSuccess(new DeleteRoomCommand(roomName), model,
                String.format(DeleteRoomCommand.MESSAGE_SUCCESS, roomName), expectedModel);
    }

    @Test
    public void execute_roomNotFound() {
        String roomName = "13E";
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                model.getHotel(), model.getBookKeeper());

        assertCommandFailure(new DeleteRoomCommand(roomName), model, DeleteRoomCommand.MESSAGE_ROOM_NOT_FOUND);
    }
}
