package seedu.address.model.ids;

/**
 * Id in the system
 */
public abstract class Id {
    private String serializedId;

    protected Id(String serializedId) {
        this.serializedId = serializedId;
    }

    protected String getSerializedId() {
        return serializedId;
    }

    @Override
    public String toString() {
        return serializedId;
    }

    @Override
    public boolean equals(Object oth) {
        if (oth == this) {
            return true;
        }
        if (!(oth instanceof Id)) {
            return false;
        }
        Id othId = (Id) oth;
        return othId.serializedId.equals(serializedId);
    }
}
