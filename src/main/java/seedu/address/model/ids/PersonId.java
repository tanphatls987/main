package seedu.address.model.ids;

/**
 * Wrap string as id.
 */
public class PersonId extends Id {

    public PersonId(String serializedId) {
        super(serializedId);
    }

    @Override
    public boolean equals(Object oth) {
        if (oth == this) {
            return true;
        }
        if (!(oth instanceof PersonId)) {
            return false;
        }
        PersonId othPersonId = (PersonId) oth;
        return othPersonId.getSerializedId().equals(getSerializedId());
    }
}
