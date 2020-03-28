package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.hotel.room.Room;

/**
 * Test for add room command
 */
public class AddRoomCommandTest {

    @Test
    public void construct_nullRoom_throwsnNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddRoomCommand(null));
    }

    @Test
    public void equals() {
        String firstName = "12E";
        String secondName = "12F";
        Room firstRoom = new Room(firstName);
        Room secondRoom = new Room(secondName);
        AddRoomCommand addFirst = new AddRoomCommand(firstName);
        AddRoomCommand addSecond = new AddRoomCommand(secondName);

        //same object -> returns true
        assertTrue(addFirst.equals(addFirst));

        //same values -> returns true
        AddRoomCommand copyFirst = new AddRoomCommand(firstName);
        assertTrue(addFirst.equals(copyFirst));

        //different types -> returns false
        assertFalse(addFirst.equals(1));

        //null -> returns false
        assertFalse(addSecond.equals(null));

        //different name -> return false
        assertFalse(addFirst.equals(addSecond));
    }
}
