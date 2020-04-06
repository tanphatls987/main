package seedu.address.model.hotel.bill;

/**
 * Represents the cost of the room for a night.
 */
public class RoomCost extends Cost implements Chargeable {
    private static final String DEFAULT_ROOM_COST = "10.00";
    /**
     * Construsts a {@code RoomCost} without a set {@code cost}.
     */
    public RoomCost(String cost) {
        super(cost);
    }

    public RoomCost(double cost) {
        super(cost);
    }

    /**
     * Default constructor
     */
    public RoomCost() {
        super(DEFAULT_ROOM_COST);
    }

    @Override
    public Cost getCost() {
        return this;
    }
}
