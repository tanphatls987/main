package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICEID;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hotel.bill.AvailableService;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.ids.AvailableServiceId;
import seedu.address.model.ids.PersonId;
import seedu.address.model.ids.RoomId;

/**
 * Adds a service to a room in a guest’s tab.
 */
public class ChargeServiceCommand extends Command {

    public static final String COMMAND_WORD = "chargeservice";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a service to the guest's bill. "
            + "Parameters: "
            + PREFIX_ID + "PERSON_ID "
            + PREFIX_ROOMNUMBER + "ROOM_NUMBER "
            + PREFIX_SERVICEID + "SERVICE_ID\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "G1231231X "
            + PREFIX_ROOMNUMBER + "101 "
            + PREFIX_SERVICEID + "WC";

    public static final String MESSAGE_SUCCESS = "Added service (ID: %1$s) to the bill of "
            + "guest (ID: %2$s) for room %3$s.";
    public static final String MESSAGE_GUEST_NONEXISTENT = "Guest (ID: %1$s) does not exist in the system.";
    public static final String MESSAGE_ROOM_NONEXISTENT = "Room %1$s does not exist in the system.";
    public static final String MESSAGE_SERVICE_NONEXISTENT = "Service (ID: %1$s) does not exist in the system.";
    public static final String MESSAGE_NO_BOOKING = "Guest (ID: %1$s) is not checked into room %2$s.";

    private final PersonId personId;
    private final RoomId roomId;
    private final AvailableServiceId serviceId;

    /**
     * @param personId to add bill to
     * @param roomId that the bill is for
     * @param serviceId of service to add
     */
    public ChargeServiceCommand(PersonId personId, RoomId roomId, AvailableServiceId serviceId) {
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

        if (person.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_GUEST_NONEXISTENT, personId));
        }

        if (room.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_ROOM_NONEXISTENT, roomId));
        }

        if (service.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_SERVICE_NONEXISTENT, serviceId));
        }

        if (!model.isGuestCheckedIn(person.get(), room.get())) {
            throw new CommandException(String.format(MESSAGE_NO_BOOKING, personId, roomId));
        }

        model.chargeService(roomId, service.get());
        return new CommandResult(String.format(MESSAGE_SUCCESS, serviceId, personId, roomId));
    }
}
