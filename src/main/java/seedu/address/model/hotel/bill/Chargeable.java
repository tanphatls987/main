package seedu.address.model.hotel.bill;

/**
 * Represents a Chargeable item/service that guests have requested for.
 */
public interface Chargeable {
    /**
     * Returns the cost of the chargeable item/service
     */
    public double getCost();
}
