package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.hotel.Room;
import seedu.address.model.hotel.Tier;

/**
 * Unmodifiable hotel
 */
public interface ReadOnlyHotel {
    /**
     * return list of rooms.
     */
    ObservableList<Room> getImmutableRoomList();

    /**
     * @return: a list of tiers;
     */
    ObservableList<Tier> getImmutableTierList();
}
