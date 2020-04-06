package seedu.address.logic.commands;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.hotel.bill.Cost;
import seedu.address.model.ids.AvailableServiceId;

public class AddServiceCommandTest {
    @Test
    public void construct_nullCost() {
        String description = "Wash clothes";
        AvailableServiceId id = new AvailableServiceId("WC");
        assertThrows(NullPointerException.class, () -> new AddServiceCommand(description, null, id));
    }

    @Test
    public void construct_nullId() {
        String description = "Wash clothes";
        Cost cost = new Cost("100.00");
        assertThrows(NullPointerException.class, () -> new AddServiceCommand(description, cost, null));
    }

    @Test
    public void construct_nullDesc() {
        AvailableServiceId id = new AvailableServiceId("WC");
        Cost cost = new Cost("100.00");
        assertThrows(NullPointerException.class, () -> new AddServiceCommand(null, cost, id));
    }
}
