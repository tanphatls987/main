package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.hotel.room.Room;

/**
 * Tests for DeleteRoomCommand
 */
public class DeleteRoomCommandTest {

    @Test
    public void construct_nullPtr() {
        assertThrows(NullPointerException.class, () -> new DeleteRoomCommand(null));
    }

    @Test
    public void equals() {
        String firstName = "12E";
        String secondName = "12F";
        Room firstRoom = new Room(firstName);
        Room secondRoom = new Room(secondName);
        DeleteRoomCommand deleteFirst = new DeleteRoomCommand(firstName);
        DeleteRoomCommand deleteSecond = new DeleteRoomCommand(secondName);
        // same object returns true
        assertTrue(deleteFirst.equals(deleteFirst));

        // same values returns true
        DeleteRoomCommand copyFirst = new DeleteRoomCommand(firstName);
        assertTrue(deleteFirst.equals(copyFirst));

        // different types, returns false
        assertFalse(deleteFirst.equals(1));

        // null, returns false
        assertFalse(deleteFirst.equals(null));

        // different room name, returns false
        assertFalse(deleteFirst.equals(deleteSecond));
    }
}
