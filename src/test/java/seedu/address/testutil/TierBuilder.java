package seedu.address.testutil;

import seedu.address.model.hotel.Tier;

/**
 * A utility class to help build tiers
 */
public class TierBuilder {
    public static final Tier DEFAULT_TIER = new Tier();

    /**
     * Create a tier builder.
     */
    public TierBuilder() {
    }

    /**
     * Build a default tier
     */
    public Tier build() {
        return DEFAULT_TIER;
    }

    /**
     * Build a tier with tier name.
     */
    public Tier withTierName(String tierName) {
        return new Tier(tierName);
    }
}
