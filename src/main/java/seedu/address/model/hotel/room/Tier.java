package seedu.address.model.hotel.room;


/**
 * Store room tier.
 */
public class Tier {

    /**
     * Enum to show different types of tiers
     */
    enum TierName {
        BRONZE, SILVER, GOLD

    }
    public static final String MESSAGE_CONSTRAINTS = "Tier must be gold, silver or bronze";
    public static final String DEFAULT_TIER = "BRONZE";
    private TierName name;
    private boolean isDefault;

    /**
     * Create a tier with name
     * @param n
     */
    public Tier(String n) {
        this.name = TierName.valueOf(n);
        this.isDefault = false;
    }

    /**
     * Create a default tier
     */
    public Tier() {
        name = TierName.valueOf(DEFAULT_TIER);
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
        return name.toString();
    }
}
