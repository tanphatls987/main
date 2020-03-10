package seedu.address.model.hotel;

import java.util.ArrayList;

import seedu.address.model.hotel.person.Person;
import seedu.address.model.ids.RoomId;

/**
 * Store room information.
 */
public class Room {
    private ArrayList<Person> guests;
    private Tier roomTier;
    private String roomName;
    private RoomId roomId;


    /**
     * Create a default room.
     */
    public Room(String roomName) {
        guests = new ArrayList<>();
        roomTier = null;
        this.roomName = roomName;
        this.roomId = RoomId.generate(roomName);
    }
}
