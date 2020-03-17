package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.hotel.Room;

/**
 * Unmodifiable hotel
 */
public interface ReadOnlyHotel {
    /**
     *
     * return list of rooms.
     */
    ObservableList<Room> getImmutableRoomList();
}
