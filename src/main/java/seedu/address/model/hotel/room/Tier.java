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

    /* MESSAGE_CONSTRAINTS should be generated from TierName */
    public static final String MESSAGE_CONSTRAINTS = "Tier must be GOLD, SILVER or BRONZE";
    public static final String MESSAGE_INVALID_TIER = "Invalid Tier format\n" + MESSAGE_CONSTRAINTS;
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
     * Check whether s is a valid tier option.
     * @param s compared string
     * @return true if valid
     */
    public static boolean isTierOption(String s) {
        for (TierName tn: TierName.values()) {
            if (tn.name().equals(s)) {
                return true;
            }
        }

        return false;
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
