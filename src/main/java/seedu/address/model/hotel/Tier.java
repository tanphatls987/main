package seedu.address.model.hotel;


/**
 * Store room tier.
 */
public class Tier {
    private String name;
    private boolean isDefault;

    /**
     * Create a tier with name
     * @param name
     */
    public Tier(String name) {
        this.name = name;
        this.isDefault = false;
    }

    /**
     * Create a default tier
     */
    public Tier() {
        name = "default";
        this.isDefault = true;
    }

    /**
     * Check if this tier is default tier.
     */
    public boolean isDefaultTier() {
        return this.isDefault;
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

    @Override
    public String toString() {
        return this.name;
    }
}
