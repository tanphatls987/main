package seedu.address.model.ids;

/**
 * Id of available services.
 * To be factorized by hotel.
 */
public class AvailableServiceId extends Id {
    public AvailableServiceId(String serializedId) {
        super(serializedId);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AvailableServiceId)) {
            return false;
        }

        AvailableServiceId otherId = (AvailableServiceId) other;
        return otherId.getSerializedId().equals(getSerializedId());
    }
}
