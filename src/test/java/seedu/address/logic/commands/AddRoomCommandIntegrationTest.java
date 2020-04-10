package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.BookKeeper;
import seedu.address.model.Hotel;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.hotel.room.Room;
import seedu.address.testutil.RoomBuilder;

/**
 * Integration test, interacting with model
 */
public class AddRoomCommandIntegrationTest {
    private static final Room DEFAULT_ROOM = new RoomBuilder().build();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new Hotel(), new BookKeeper());



    @Test
    public void executeAddRoom_success() throws CommandException {
        AddRoomCommand command = new AddRoomCommand(
            DEFAULT_ROOM.getName(),
            DEFAULT_ROOM.getRoomCost(),
            DEFAULT_ROOM.getTier());

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                model.getHotel(), model.getBookKeeper());
        model.addRoom(DEFAULT_ROOM.getName());
    }
}
