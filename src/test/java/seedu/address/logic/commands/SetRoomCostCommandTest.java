package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hotel.bill.RoomCost;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.hotel.room.Tier;
import seedu.address.model.ids.RoomId;
import seedu.address.testutil.TypicalRooms;

class SetRoomCostCommandTest {
    private Model modelStub = new ModelStubSetRoomCost();

    @Test
    void executeSuccess() throws Exception {

        Room bestRoom = TypicalRooms.BEST_ROOM;
        String newPrice = "24.00";
        SetRoomCostCommand command = new SetRoomCostCommand(bestRoom.getRoomId(), new RoomCost(newPrice));
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
            bestRoom.getRoomId(), newPrice);

        CommandResult commandResult = command.execute(modelStub);
        assertEquals(commandResult.getFeedbackToUser(),
            String.format(SetRoomCostCommand.MESSAGE_SUCCESS,
                bestRoom.getRoomNum(), newPrice)
        );
    }

    @Test
    void executeFailure_negative_price() {
        SetRoomCostCommand command = new SetRoomCostCommand(new RoomId("nonexist"),
            new RoomCost("-2.00")
        );
        assertThrows(CommandException.class,
            SetRoomCostCommand.MESSAGE_ROOM_COST_INVALID, () -> command.execute(modelStub)
        );
    }
    private class ModelStubSetRoomCost extends ModelStub {
        @Override
        public Optional<Room> findRoom(RoomId roomNum) {
            return Optional.of(new Room(roomNum.toString(), new Tier(), new RoomCost()));
        }

        @Override
        public void setRoomCost(Room room, RoomCost cost) {

        }
    }
}
