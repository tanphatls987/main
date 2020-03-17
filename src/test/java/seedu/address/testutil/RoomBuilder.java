package seedu.address.testutil;

import seedu.address.model.hotel.Room;
import seedu.address.model.hotel.Tier;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.ids.RoomId;


/***
 * A utility class to help build rooms
 */
public class RoomBuilder {
    public static final Person ALICE = TypicalPersons.ALICE;
    public static final Tier DEFAULT_TIER = new Tier();
    public static final String DEFAULT_ROOMNUM = "1";
    public static final RoomId DEFAULT_ROOMID = RoomId.generate(DEFAULT_ROOMNUM);

    private Person person;
    private Tier roomTier;
    private String roomNum;
    private RoomId roomId;


    public RoomBuilder() {
        person = ALICE;
        roomTier = DEFAULT_TIER;
        roomNum = DEFAULT_ROOMNUM;
        roomId = DEFAULT_ROOMID;
    }

    public Room build() {
        return new Room(roomNum, roomTier);
    }

}
