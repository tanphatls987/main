package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.ids.AvailableServiceId;
/**
 * Tests for DeleteServiceCommand
 */
public class DeleteServiceCommandTest {

    @Test
    public void equals() {
        String firstService = "WC";
        String secondService = "BJ";
        AvailableServiceId firstId = new AvailableServiceId(firstService);
        AvailableServiceId secondId = new AvailableServiceId(secondService);
        DeleteServiceCommand first = new DeleteServiceCommand(firstId);
        DeleteServiceCommand second = new DeleteServiceCommand(secondId);

        // same object, returns true
        assertTrue(first.equals(first));

        // same values, returns true
        DeleteServiceCommand copyFirst = new DeleteServiceCommand(firstId);
        assertTrue(copyFirst.equals(first));

        // different types, returns false
        assertFalse(first.equals(1));

        // null, returns false
        assertFalse(first.equals(null));

        // different values, returns false
        assertFalse(first.equals(second));
    }
}
