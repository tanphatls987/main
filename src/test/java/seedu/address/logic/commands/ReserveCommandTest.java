package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.hotel.Reserve;
import seedu.address.model.hotel.Room;
import seedu.address.model.hotel.booking.Booking;
import seedu.address.model.hotel.person.Person;
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

    @Test
    public void executeReserve_success() throws CommandException {
        Booking booking = RESERVE_STUB;
        ReserveCommand reserveCommand = new ReserveCommand(ALICE.getPersonId(),
                DEFAULT_ROOM.getRoomNum(), FROM_DATE, TO_DATE);
        model.getBookingList().add(new Reserve(ALICE, DEFAULT_ROOM, FROM_DATE, TO_DATE));
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        CommandResult expectedCommandResult = reserveCommand.execute(expectedModel);
        expectedModel.bookRoom(booking);
        assertEquals(String.format(ReserveCommand.MESSAGE_ADD_RESERVE_SUCCESS, ALICE.getName()), expectedCommandResult.getFeedbackToUser());
    }
}
