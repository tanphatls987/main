package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROMDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TODATE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hotel.Room;
import seedu.address.model.hotel.person.Person;

public class ReserveCommand extends Command{

    /**
     * Changes the remark of an existing person in the address book.
     */

        public static final String COMMAND_WORD = "reserve";

        public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reserves room of the person identified "
            + "by the index number used in the last person listing. "
            + "under room number\n"
            + PREFIX_ROOMNUMBER + "\n"
            + "Parameters: INDEX, ROOMNUMBER, FROMDATE, TODATE\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ROOMNUMBER + "101\n"
            + PREFIX_FROMDATE + "2020-12-12\n"
            + PREFIX_TODATE + "2020-12-23\n";

        public static final String MESSAGE_ADD_RESERVE_SUCCESS = "Added booking to Person: %1$s";

        private final String roomNum;
        private final Index index;
        private final LocalDate fromDate;
        private final LocalDate toDate;

        public ReserveCommand(Index index, String roomNum, LocalDate fromDate, LocalDate toDate) {
            requireAllNonNull(roomNum, fromDate, toDate);
            this.roomNum = roomNum;
            this.fromDate = fromDate;
            this.toDate = toDate;
            this.index = index;
        }

        @Override
        public CommandResult execute(Model model) throws CommandException {
            List<Person> lastShownList = model.getFilteredPersonList();
            model.fillRoomList(); //need to change to data in json
            ArrayList<Room> rooms = model.getRoomList();
            Person payee = lastShownList.get(index.getZeroBased());
            int i = Integer.parseInt(this.roomNum);
            rooms.get(i).bookRoom(payee, this.fromDate, this.toDate);
            return new CommandResult(generateSuccessMessage(roomNum, payee));
        }

    /**
     * Generates a command execution success message based on whether the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(String roomNum, Person payee) {
        String message = MESSAGE_ADD_RESERVE_SUCCESS;
        return String.format(message, payee);
    }
}
