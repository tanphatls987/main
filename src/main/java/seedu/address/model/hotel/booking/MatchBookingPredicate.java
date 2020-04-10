package seedu.address.model.hotel.booking;

import java.util.function.Predicate;

import seedu.address.model.hotel.person.Name;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.ids.PersonId;


/**
 * Predicate for booking, (or) of 3 predicates
 */
public class MatchBookingPredicate implements Predicate<Booking> {
    private Predicate<Room> roomPredicate;
    private Predicate<PersonId> personIdPredicate;
    private Predicate<Name> namePredicate;

    /**
     * Constructs a default booking predicate.
     */
    public MatchBookingPredicate() {
        roomPredicate = unused -> false;
        personIdPredicate = unused -> false;
        namePredicate = unused -> false;
    }

    /**
     * set this roomPredicate.
     */
    public void setRoomPredicate(Predicate<Room> roomPredicate) {
        this.roomPredicate = roomPredicate;
    }

    /**
     * set this personPredicate
     */
    public void setPersonIdPredicate(Predicate<PersonId> personIdPredicate) {
        this.personIdPredicate = personIdPredicate;
    }

    /**
     * set this namePredicate
     */
    public void setNamePredicate(Predicate<Name> namePredicate) {
        this.namePredicate = namePredicate;
    }

    @Override
    public boolean test(Booking booking) {
        return roomPredicate.test(booking.getRoom())
                || namePredicate.test(booking.getPayee().getName())
                || personIdPredicate.test(booking.getPayee().getPersonId());
    }
}
