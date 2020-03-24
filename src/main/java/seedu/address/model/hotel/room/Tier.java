package seedu.address.model.hotel.room;


/**
 * Store room tier.
 */
public class Tier {
    public static final String MESSAGE_CONSTRAINTS = "Tier must be gold, silver or bronze";
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

    public static boolean isValid(String tier) {
        return tier.equals("gold") || tier.equals("silver") || tier.equals("bronze");
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
        return name;
    }
}
