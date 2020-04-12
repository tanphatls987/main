package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hotel.Stay;
import seedu.address.model.hotel.bill.Bill;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.ids.RoomId;
import seedu.address.model.timeframe.TimeFrame;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TODATE;

public class ExtendCommand extends Command{

    public static final String COMMAND_WORD = "extend";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Extend the guest's stay.\n"
        + "Parameters: \n"
        + PREFIX_ROOMNUMBER + "ROOMNUMBER "
        + PREFIX_TODATE + "TODATE "
        + "Example: \n" + COMMAND_WORD + " "
        + PREFIX_ROOMNUMBER + "101 "
        + PREFIX_TODATE + "2020-03-14";

    public static final String MESSAGE_SUCCESS = "Room %1$s is extended to %2$s";
    public static final String MESSAGE_ROOM_NOT_EXISTS = "Room %1$s does not exist.";
    public static final String MESSAGE_ROOM_OCCUPIED = "Room %1$s is occupied";
    public static final String MESSAGE_ROOM_NOT_CHECKED_IN = "Room %1$s is not checked in.";
    public static final String MESSAGE_DATE_PASSED = "%1$s has passed";

    private RoomId roomId;
    private LocalDateTime toDate;

    /**
     * Creates a ExtendCommand for room with {@code roomId} until {@code toDate}
     * @param roomId the ID of the Room that is going to be extended.
     * @param toDate the end Date of the stay.
     */
    public ExtendCommand(RoomId roomId, LocalDateTime toDate) {
        requireNonNull(roomId);
        requireNonNull(toDate);
        this.roomId = roomId;
        this.toDate = toDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Room> room = model.findRoom(roomId);

        if (room.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_ROOM_NOT_EXISTS, roomId));
        }

        Optional<Stay> stay = model.findStay(room.get());

        if (stay.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_ROOM_NOT_CHECKED_IN, roomId));
        }
        Person person = stay.get().getPayee();

        if (!(toDate.isAfter(LocalDateTime.now()))) {
            throw new CommandException(String.format(MESSAGE_DATE_PASSED, toDate));
        }

        TimeFrame stayTimeFrame = new TimeFrame(stay.get().getTimeTo(), toDate);
        if (!(model.isRoomFree(person, room.get(), stayTimeFrame))) {
            throw new CommandException(String.format(MESSAGE_ROOM_OCCUPIED, roomId));
        }

        LocalDateTime fromDate = stay.get().getTimeTo();
        model.extendRoom(stay.get(), toDate);

        Predicate<Room> predicate = thisRoom -> stay.get().getRoom().isSameRoom(thisRoom);
        model.updateFilteredRoomList(predicate);

        Optional<Bill> bill = model.findBill(roomId);
        if (bill.isEmpty()) {
            model.addBill(new Bill(person.getPersonId(), roomId));
        }

        model.chargeExtendRoomCost(roomId, room.get().getRoomCost(), stay.get(), fromDate);

        return new CommandResult(String.format(MESSAGE_SUCCESS, roomId, toDate), "room");
    }
}
