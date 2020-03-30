package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.hotel.room.Tier;

/**
 * Unmodifiable hotel
 */
public interface ReadOnlyHotel {
    /**
     * return list of rooms.
     */
    ObservableList<Room> getRoomList();
    /**
     * @return: a list of tiers;
     */
    ObservableList<Tier> getTierList();
}
