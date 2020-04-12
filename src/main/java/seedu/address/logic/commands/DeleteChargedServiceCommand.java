package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICEID;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hotel.bill.AvailableService;
import seedu.address.model.hotel.bill.Bill;
import seedu.address.model.hotel.bill.Chargeable;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.ids.AvailableServiceId;
import seedu.address.model.ids.PersonId;
import seedu.address.model.ids.RoomId;

/**
 * Deletes a charged service from the guest's bill.
 */
public class DeleteChargedServiceCommand extends Command {

    public static final String COMMAND_WORD = "deletecservice";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a service from the guest's bill. "
            + "Parameters: "
            + PREFIX_ID + "PERSON_ID "
            + PREFIX_ROOMNUMBER + "ROOM_NUMBER "
            + PREFIX_SERVICEID + "SERVICE_ID\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "G1231231X "
            + PREFIX_ROOMNUMBER + "101 "
            + PREFIX_SERVICEID + "WC";

    public static final String MESSAGE_SUCCESS = "Deleted service (ID: %1$s) from the bill of "
            + "guest (ID: %2$s) for room %3$s.";
    public static final String MESSAGE_GUEST_NONEXISTENT = "Guest (ID: %1$s) does not exist in the system.";
    public static final String MESSAGE_ROOM_NONEXISTENT = "Room %1$s does not exist in the system.";
    public static final String MESSAGE_SERVICE_NONEXISTENT = "Service (ID: %1$s) does not exist in the system.";
    public static final String MESSAGE_BILL_NONEXISTENT = "Guest (ID: %1$s) does not have any outstanding bills for"
            + "Room %2$s.";
    public static final String MESSAGE_SERIVCE_NOT_IN_BILL = "Service (ID: %1$s) does not exist in the bill.";

    private final PersonId personId;
    private final RoomId roomId;
    private final AvailableServiceId serviceId;

    /**
     * @param personId to delete charges service for
     * @param roomId that the bill is for
     * @param serviceId of service to delete
     */
    public DeleteChargedServiceCommand(PersonId personId, RoomId roomId, AvailableServiceId serviceId) {
        requireAllNonNull(personId, roomId, serviceId);
        this.personId = personId;
        this.roomId = roomId;
        this.serviceId = serviceId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Person> person = model.findPersonWithId(personId);
        Optional<Room> room = model.findRoom(roomId);
        Optional<AvailableService> service = model.findService(serviceId);
        Optional<Bill> bill = model.findBill(roomId);

        if (person.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_GUEST_NONEXISTENT, personId));
        }

        if (room.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_ROOM_NONEXISTENT, roomId));
        }

        if (service.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_SERVICE_NONEXISTENT, serviceId));
        }

        if (bill.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_BILL_NONEXISTENT, personId, roomId));
        }

        Optional<Chargeable> serviceInBill = bill.get().getService(serviceId);

        if (serviceInBill.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_SERIVCE_NOT_IN_BILL, serviceId));
        }

        model.deleteChargedService(roomId, service.get());

        Predicate<Bill> predicate = b -> {
            if (b.getRoomId().equals(roomId)) {
                return true;
            } else {
                return false;
            }
        };
        model.updateFilteredBillList(predicate);

        return new CommandResult(String.format(MESSAGE_SUCCESS, serviceId, personId, roomId), "bill");
    }
}
