package seedu.address.model.hotel;

import java.util.ArrayList;

import seedu.address.model.hotel.person.Person;

/**
 * Store room information.
 */
public class Room {
    private ArrayList<Person> guests;
    private Tier roomTier;


    /**
     * Create a default room.
     */
    public Room() {
        guests = new ArrayList<>();
        roomTier = null;
    }
}
