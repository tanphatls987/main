package seedu.address.model.hotel.room;

import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.ids.RoomId;

/**
 * Check if the person name is in a predetermine list.
 */
public class MatchRoomPredicate implements Predicate<Room> {
    private final HashSet<RoomId> roomIds;

    /**
     * Creates new MatchRoomPredicate to match a room in roomIds.
     * @param roomIds: a set of ids.
     */
    public MatchRoomPredicate(HashSet<RoomId> roomIds) {
        this.roomIds = roomIds;
    }

    /**
     * Alternative constructor, given a list of roomIds instead of a set.
     * @param roomIdList: list of roomId.
     */
    public MatchRoomPredicate(List<RoomId> roomIdList) {
        this.roomIds = new HashSet<>(roomIdList);
    }

    /**
     * Test a predicate with some input.
     * @return {@code true} if the input argument matches the predicate,
     * {@code false} otherwise.
     */
    @Override
    public boolean test(Room room) {
        return roomIds.contains(room.getRoomId());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MatchRoomPredicate)) {
            return false;
        }

        MatchRoomPredicate otherPredicate = (MatchRoomPredicate) other;
        return otherPredicate.roomIds.equals(this.roomIds);
    }

}
