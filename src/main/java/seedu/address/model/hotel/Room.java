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

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";


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

    public void bookRoom(Person payee, LocalDate dateFrom, LocalDate dateTo) throws RoomBookedException {
        try {
            if(this.bookings.isEmpty()) {
                this.bookings.add(new Booking(payee, dateFrom, dateTo, this));
            } else {
                for(Booking b : this.bookings) {
                    if(b.bookFrom.isAfter(dateFrom) && b.bookTo.isBefore(dateTo)) {
                        throw new RoomBookedException();
                    } else {
                        this.bookings.add(new Booking(payee, dateFrom, dateTo, this));
                    }
                }
            }
        } catch (RoomBookedException rbe) {}
    }

    //public boolean isBooked()

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidRoom(String test) {
        return test.matches(VALIDATION_REGEX);
    }
}
