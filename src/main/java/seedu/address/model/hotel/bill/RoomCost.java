package seedu.address.model.hotel.bill;

/**
 * Represents the cost of the room for a night.
 */
public class RoomCost implements Chargeable {
    private Cost cost;
    private boolean isSet;

    /**
     * Construsts a {@code RoomCost} without a set {@code cost}.
     */
    public RoomCost() {
        this.cost = null;
        this.isSet = false;
    }

    /**
     * Constructs a {@code RoomCost} with a set {@code cost}.
     */
    public RoomCost(Cost cost) {
        this.cost = cost;
        this.isSet = false;
    }

    /**
     * Return true if cost is set.
     */
    public boolean isSet() {
        return isSet;
    }


    @Override
    public double getCost() {
        return this.cost;
    }
}
