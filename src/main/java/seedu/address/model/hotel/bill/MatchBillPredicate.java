package seedu.address.model.hotel.bill;

import java.util.function.Predicate;

import seedu.address.model.ids.RoomId;

/**
 * Checks if a bill has a matching roomId.
 */
public class MatchBillPredicate implements Predicate<Bill> {
    private RoomId roomId;

    /**
     * Constructs a new MatchBillPredicate.
     */
    public MatchBillPredicate(RoomId roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean test(Bill bill) {
        return bill.getRoomId().equals(roomId);
    }
}
