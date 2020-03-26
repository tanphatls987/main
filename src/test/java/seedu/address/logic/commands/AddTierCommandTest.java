package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.hotel.room.Tier;
import seedu.address.testutil.TypicalRooms;
/**
 * Test for add tier command.
 */
public class AddTierCommandTest {
    @Test
    public void construct_nullRoom_thowsnNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTierCommand(null, null));
    }

    @Test
    public void equals() {
        String firstTierName = "Gold";
        String secondTierName = "Diamond";
        Tier goldTier = new Tier(firstTierName);
        Tier diamondTier = new Tier(secondTierName);
        ArrayList roomList = (ArrayList) TypicalRooms.getTypicalRooms();
        AddTierCommand firstCommand = new AddTierCommand(goldTier, roomList);
        AddTierCommand secondCommand = new AddTierCommand(diamondTier, roomList);

        //same object, returns true
        assertTrue(goldTier.equals(goldTier));

        //same values, returns true
        AddTierCommand copyFirst = new AddTierCommand(goldTier, roomList);
        assertTrue(copyFirst.equals(firstCommand));

        //different types, returns false
        assertFalse(secondCommand.equals(1));

        //null, returns false
        assertFalse(firstCommand.equals(null));

        //different values, return false
        assertFalse(firstCommand.equals(secondCommand));
    }
}
