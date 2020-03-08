package seedu.address.model.hotel;

/**
 *
 */
public class Service implements Chargeable {
    private double price;

    /**
     * [Service description]
     * @return [description]
     * TODO: update.
     */
    public Service() {
        this.price = 0;
    }

    /**
     * Get cost of this service.
     * @return an amount
     */
    public double getCost() {
        return this.price;
    }
}
