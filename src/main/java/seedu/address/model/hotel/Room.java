package seedu.address.model.hotel;

import java.time.LocalDate;
import java.util.ArrayList;

import seedu.address.model.hotel.person.Person;
import seedu.address.model.hotel.person.exceptions.RoomBookedException;
import seedu.address.model.ids.RoomId;

/**
 * Store room information.
 */
public class Room {
    private ArrayList<Person> guests;
    private Tier roomTier;
    private String roomNum;
    private RoomId roomId;
    private ArrayList<Booking> bookings;

    /**
     * Create a default room.
     */
    public Room(String roomNum) {
        guests = new ArrayList<>();
        roomTier = null;
        this.roomNum = roomNum;
        this.roomId = RoomId.generate(roomNum);
        this.bookings = new ArrayList<Booking>();
    }

    /**
     * Books room under a payee
     * */
    public void bookRoom(Person payee, LocalDate dateFrom, LocalDate dateTo) throws RoomBookedException {
        try {
            if (this.bookings.isEmpty()) {
                this.bookings.add(new Booking(payee, dateFrom, dateTo, this));
            } else {
                for (Booking b : this.bookings) {
                    if (b.bookFrom.isAfter(dateFrom) && b.bookTo.isBefore(dateTo)) {
                        throw new RoomBookedException();
                    } else {
                        this.bookings.add(new Booking(payee, dateFrom, dateTo, this));
                    }
                }
            }
        } catch (RoomBookedException rbe) {
            rbe.getLocalizedMessage();
        }
    }
}
