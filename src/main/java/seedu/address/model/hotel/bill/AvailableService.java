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
    private AvailableServiceId serviceId;

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
        this.serviceId = serviceId;
    }

    /***
     * Checks if service is same as {@code room}
     */
    public boolean isSameService(AvailableService service) {
        if (service == this) {
            return true;
        }

        return service != null
                && service.getServiceId().equals(getServiceId());
    }

    /**
     *
     * @return cost of this services
     */
    public Cost getCost() {
        return this.cost;
    }

    public AvailableServiceId getServiceId() {
        return this.serviceId;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append(" ServiceId: ")
                .append(getServiceId())
                .append(" Cost: ")
                .append(getCost());
        return builder.toString();
    }
}
