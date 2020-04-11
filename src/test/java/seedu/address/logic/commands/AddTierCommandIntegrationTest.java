package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.BookKeeper;
import seedu.address.model.Hotel;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.hotel.room.Tier;
import seedu.address.testutil.TierBuilder;
import seedu.address.testutil.TypicalRooms;


/**
 * Integration test, interacting with model
 */
public class AddTierCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new Hotel(), new BookKeeper());
    }

    @Test
    public void executeAddTier_roomNotFound() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                model.getHotel(), model.getBookKeeper());

        Tier validTier = new TierBuilder().withTierName("GOLD");
        ArrayList<Room> roomList = TypicalRooms.getTypicalRooms();
        ArrayList<String> roomNums = new ArrayList<>();
        for (Room room: roomList) {
            roomNums.add(room.getName());
        }

        expectedModel.addTier(validTier, roomNums);
        assertCommandFailure(new AddTierCommand(validTier, roomNums), model,
                AddTierCommand.MESSAGE_ROOM_NOT_FOUND);
    }

}
