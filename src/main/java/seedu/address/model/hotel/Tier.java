package seedu.address.model.hotel;


/**
 * Store room tier.
 */
public class Tier {
    private String name;

    /**
     * Create a tier with name
     * @param name
     */
    public Tier(String name) {
        this.name = name;
    }

    /**
     * Create a default tier
     */
    public Tier() {
        name = "default";
    }

    @Override
    public boolean equals(Object oth) {
        if (oth == this) {
            return true;
        }
        if (!(oth instanceof Tier)) {
            return false;
        }
        Tier othTier = (Tier) oth;
        return othTier.name.equals(name);
    }
}
