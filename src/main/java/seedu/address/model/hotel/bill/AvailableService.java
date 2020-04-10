package seedu.address.model.hotel.bill;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.ids.AvailableServiceId;

/**
 * Available services, to be added when initialization.
 * Stores: description, cost, id of the service.
 */
public class AvailableService implements Chargeable {
    private String description;
    private Cost cost;
    private AvailableServiceId id;

    /**
     *
     * @param desc: a short description of the service
     * @param cost: amount
     * @param serviceId: for reference
     */
    public AvailableService(String desc, Cost cost, AvailableServiceId serviceId) {
        requireAllNonNull(desc, cost, serviceId);
        this.description = desc;
        this.cost = cost;
        this.id = serviceId;
    }

    /***
     * Checks if service is same as {@code service}
     */
    public boolean isSameService(AvailableService service) {
        if (service == this) {
            return true;
        }

        return service != null
                && service.getId().equals(getId());
    }

    /**
     *
     * @return its own id.
     */
    public AvailableServiceId getId() {
        return this.id;
    }

    /**
     *
     * @return its own description.
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return cost of this services
     */
    public Cost getCost() {
        return this.cost;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getId())
                .append(": ")
                .append(getDescription())
                .append(" ($")
                .append(getCost())
                .append(")");
        return builder.toString();
    }
}
