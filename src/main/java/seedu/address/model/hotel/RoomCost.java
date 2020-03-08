package seedu.address.model.hotel;

/**
 *
 */
public class RoomCost implements Chargeable {
    private double price;

    /**
     * [RoomCost description]
     * @return [description]
     * TODO: update this.
     */
    public RoomCost() {
        this.price = 0;
    }

    @Override
    public double getCost() {
        return this.price;
    }
}
