package seedu.address.model.hotel.bill;

import seedu.address.model.ids.AvailableServiceId;

/**
 * Available services, to be added when initialization.
 * Stores: description, cost, id of the service.
 */
public class AvailableService {
    private String description;
    private Cost cost;
    private AvailableServiceId id;

    /**
     *
     * @param desc: a short description of the service
     * @param cost: amount
     * @param id: for reference
     */
    public AvailableService(String desc, Cost cost, AvailableServiceId id) {
        this.description = desc;
        this.cost = cost;
        this.id = id;
    }

    /**
     *
     * @return cost of this services
     */
    public Cost getCost() {
        return this.cost;
    }
}
