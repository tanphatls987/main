package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.hotel.Reserve;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.hotel.room.Room;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.RoomBuilder;

/***
 * Contains integration tests with model and unit tests for reservecommand
 */
public class ReserveCommandTest {
    private static final Person ALICE = new PersonBuilder().build();
    private static final Room DEFAULT_ROOM = new RoomBuilder().build();
    private static final LocalDateTime FROM_DATE = LocalDateTime.parse("2020-12-12T12:30:00");
    private static final LocalDateTime TO_DATE = LocalDateTime.parse("2020-12-23T12:30:00");
    private static final Reserve RESERVE_STUB = new Reserve(ALICE, DEFAULT_ROOM, FROM_DATE, TO_DATE);

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

}
