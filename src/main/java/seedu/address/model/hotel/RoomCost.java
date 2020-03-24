package seedu.address.model.hotel;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the cost of the room for a night.
 */
public class RoomCost implements Chargeable {

    public static final String MESSAGE_CONSTRAINTS =
            "Cost should only contain numbers, and it should have a maximum of 2 decimal places";
    public static final String VALIDATION_REGEX = "[\\d\.\\\d{2}]";

    private double cost;

    /**
     * Constructs a {@code RoomCost}.
     */
    public RoomCost(String cost) {
        requireNonNull(cost);
        checkArgument(isValidCost(cost), MESSAGE_CONSTRAINTS);
        this.cost = Double.valueOf(cost);
    }

    /**
     * Returns true if a given string is a valid price.
     */
    @Override
    public static boolean isValidCost(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public double getCost() {
        return this.cost;
    }
}
