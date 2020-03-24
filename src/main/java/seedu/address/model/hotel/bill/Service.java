package seedu.address.model.hotel.bill;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a service that a guest has requested for.
 */
public class Service implements Chargeable {

    public static final String DESC_MESSAGE_CONSTRAINTS =
            "Description should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String DESC_VALIDATION_REGEX = "[\\p{Alnum}]*";
    public static final String COST_MESSAGE_CONSTRAINTS =
            "Price should only contain numbers, and it should have a maximum of 2 decimal places";
    public static final String COST_VALIDATION_REGEX = "[\\d\.\\\d{2}]";

    private String description;
    private double cost;

    /**
     * Creates a {@code service} that has the cost of {@code price}.
     *
     * @param desc a valid description
     * @param cost a valid cost
     */
    public Service(String desc, String cost) {
        requireNonNull(desc);
        checkArgument(isValidDescription(desc), DESC_MESSAGE_CONSTRAINTS);
        requireNonNull(cost);
        checkArgument(isValidCost(cost), COST_MESSAGE_CONSTRAINTS);

        this.description = desc;
        this.cost = Double.valueOf(cost);
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(DESC_VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid cost.
     */
    @Override
    public static boolean isValidCost(String test) {
        return test.matches(COST_VALIDATION_REGEX);
    }

    @Override
    public double getCost() {
        return this.cost;
    }

    public String getDescription() {
        return this.description;
    }
}
