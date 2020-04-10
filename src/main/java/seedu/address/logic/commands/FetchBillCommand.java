package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;

import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hotel.bill.Bill;
import seedu.address.model.hotel.bill.Cost;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.ids.PersonId;
import seedu.address.model.ids.RoomId;

/**
 * Retrieves the bill of a guest
 */
public class FetchBillCommand extends Command {

    public static final String COMMAND_WORD = "fetchbill";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Retrieves the bill of a guest.\n"
            + "Parameters: "
            + PREFIX_ID + "PERSONID "
            + "[" + PREFIX_ROOMNUMBER + "ROOMNUMBER]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "J0000000 "
            + PREFIX_ROOMNUMBER + "101";

    public static final String MESSAGE_SUCCESS_NONSPECIFIC = "The total amount payable for all bill/s of "
            + "guest %1$s (ID: %2$s) is: $%3$s.\n"
            + "For more details, check the bill tab.";
    public static final String MESSAGE_SUCCESS_SPECIFIC = "The total amount payable for the bill of "
            + "room %1$s of guest %2$s (ID: %3$s) is: $%4$s.\n"
            + "For more details, check the bill tab.";
    public static final String MESSAGE_GUEST_NONEXISTENT = "Guest (ID: %1$s) does not exist in the system.";
    public static final String MESSAGE_ROOM_NONEXISTENT = "Room %1$s does not exist in the system.";

    public static final String MESSAGE_BILL_NONEXISTENT = "Guest (ID: %1$s) does not have any outstanding bills.";
    public static final String MESSAGE_SPECIFIC_BILL_NONEXISTENT = "Guest (ID: %1$s) does not have any "
            + "outstanding bills for room %2$s.";

    private final PersonId personId;
    private final boolean isSpecific;
    private final RoomId roomId;

    /**
     * @param personId to fetch the bills of
     */
    public FetchBillCommand(PersonId personId) {
        requireNonNull(personId);
        this.personId = personId;
        this.roomId = null;
        this.isSpecific = false;
    }

    /**
     * Specifically fetch the bill of room number {@code roomId} for {@code personId}
     */
    public FetchBillCommand(PersonId personId, RoomId roomId) {
        requireNonNull(personId);
        requireNonNull(roomId);
        this.personId = personId;
        this.roomId = roomId;
        this.isSpecific = true;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Cost total;

        Optional<Person> person = model.findPersonWithId(personId);
        ObservableList<Bill> bills = model.findBillList(personId);

        if (person.isEmpty()) {
            throw new CommandException(MESSAGE_GUEST_NONEXISTENT);
        }

        if (bills.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_BILL_NONEXISTENT, personId));
        }

        if (isSpecific) {
            Optional<Room> room = model.findRoom(roomId);
            Optional<Bill> bill = model.findBill(roomId);

            if (room.isEmpty()) {
                throw new CommandException(String.format(MESSAGE_ROOM_NONEXISTENT, roomId));
            }

            if (bill.isEmpty()) {
                throw new CommandException(String.format(MESSAGE_SPECIFIC_BILL_NONEXISTENT, personId, roomId));
            }


            total = bill.get().getBillTotal();

            Predicate<Bill> predicate = b -> {
                if (b.getRoomId().equals(roomId)) {
                    return true;
                } else {
                    return false;
                }
            };
            model.updateFilteredBillList(predicate);

            return new CommandResult(String.format(MESSAGE_SUCCESS_SPECIFIC,
                    roomId, person.get().getName(), personId, total));
        } else {

            total = model.getGuestBillsTotal(personId);

            Predicate<Bill> predicate = b -> {
                if (b.getPersonId().equals(personId)) {
                    return true;
                } else {
                    return false;
                }
            };
            model.updateFilteredBillList(predicate);

            return new CommandResult(String.format(MESSAGE_SUCCESS_NONSPECIFIC,
                    person.get().getName(), personId, total));
        }
    }
}
