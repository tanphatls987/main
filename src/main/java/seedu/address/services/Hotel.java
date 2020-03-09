package seedu.address.hotel;

import java.util.List;

import seedu.address.model.hotel.Room;
import seedu.address.model.hotel.person.Person;
import seedu.address.storage.Storage;

/**
 *
 */
public class Hotel {
    private Storage storage;
    private List<Room> rooms;
    private List<Person> persons;

    /**
     * [Hotel description]
     * @return [description]
     */
    public Hotel() {
    }

    /**
     * Create new customer.
     */
    public void createCustomer() {
        ///TODO
    }

    /**
     * Create new rooms.
     */
    public void createRoom(String roomName) {

    }

    public boolean hasPerson(Person who) {
        return false;
    }
}
