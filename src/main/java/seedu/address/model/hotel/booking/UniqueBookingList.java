package seedu.address.model.hotel.booking;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.awt.print.Book;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.hotel.booking.exception.BookingNotFoundException;
import seedu.address.model.hotel.booking.exception.RoomBookedException;

/**
 * A list of bookings that enforces uniqueness between its elements and does not allow nulls.
 * A booking is considered unique by comparing using {@code Booking#isClash(Booking)}. As such, adding and updating of
 * bookings uses Booking#isClash(Booking)) for equality so as to ensure that the booking being added or updated is
 * unique in terms of TimeFrame in the UniqueRoomList.
 *
 * Supports a minimal set of list operations.
 *
 * @see Booking#isClash(Booking)
 */

public class UniqueBookingList implements Iterable<Booking> {
    private final ObservableList<Booking> internalList = FXCollections.observableArrayList();
    private final ObservableList<Booking> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent booking as the given argument.
     */
    public boolean contains(Booking toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameBooking);
    }

    /**
     * Adds a booking to the list.
     * The booking must not already exist in the list.
     */
    public void add(Booking toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new RoomBookedException();
        }
        internalList.add(toAdd);
    }


    /**
     * Replaces the contents of this {@code internalList} with
     * {@code internalList} of {@code replacement}.
     */
    public void setBookings(UniqueBookingList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }
    /**
     * Replaces the contents of this list with {@code bookings}.
     * {@code bookings} must not contain duplicate bookings.
     */
    public void setBookings(List<Booking> bookings) {
        requireAllNonNull(bookings);
        if (!bookingsAreUnique(bookings)) {
            throw new RoomBookedException();
        }

        internalList.setAll(bookings);
    }

    /**
     * Removes the equivalent booking from the list.
     * The booking must exist in the list.
     */
    public void remove(Booking toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new BookingNotFoundException();
        }
    }


    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Booking> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Booking> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueBookingList // instanceof handles nulls
                && internalList.equals(((UniqueBookingList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    public void removeIf(Predicate<Booking> p) {
        for (Booking booking: internalList) {
            if (p.test(booking)) {
                internalList.remove(booking);
            }
        }
    }

    /**
     * Returns true if {@code bookings} contains only unique bookings.
     */
    private boolean bookingsAreUnique(List<Booking> bookings) {
        for (int i = 0; i < bookings.size() - 1; i++) {
            for (int j = i + 1; j < bookings.size(); j++) {
                if (bookings.get(i).isSameBooking(bookings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public Optional<Booking> findBookingById(String booking) {
        return internalList
            .stream()
            .filter(u -> u.isMatchId(booking))
            .findFirst();
    }
}
