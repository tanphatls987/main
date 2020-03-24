package seedu.address.model.hotel.bill;

/**
 * Represents a Chargeable item/service that guests have requested for.
 */
public interface Chargeable {

    /**
     * Returns true if a given string is a valid cost.
     */
    public static boolean isValidCost(String test);

    /**
     * Returns the cost of the chargeable item/service
     */
    public double getCost();
}
