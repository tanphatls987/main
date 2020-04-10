package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.hotel.bill.AvailableService;
import seedu.address.model.hotel.bill.Bill;
import seedu.address.model.hotel.bill.Chargeable;
import seedu.address.model.hotel.bill.RoomCost;
import seedu.address.model.hotel.person.Name;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.ids.RoomId;

/**
 * Jackson-friendly version of {@link Bill}.
 */
public class JsonAdaptedBill {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Bill's %s field is missing!";

    private final String room;
    private final String roomCost;
    private final String total;
    private final List<JsonAvailableService> charged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedBill} with the given bill details.
     */
    @JsonCreator
    public JsonAdaptedBill(@JsonProperty("room") String room,
                           @JsonProperty("total") String total,
                           @JsonProperty("room cost:") String roomCost,
                           @JsonProperty("charged") List<JsonAvailableService> charged) {
        this.room = room;
        this.roomCost = roomCost;
        this.total = total;

        if (charged != null) {
            this.charged.addAll(charged);
        }
    }

    /**
     * Converts a given {@code Bill} into this class for Jackson use.
     */
    public JsonAdaptedBill(Bill source) {
        room = source.getRoomId().toString();
        roomCost = String.valueOf(source.getCharges().stream()
                .filter(c -> c instanceof RoomCost)
                .findFirst().get());
        total = Double.toString(source.getTotalExpenses());
        charged.addAll(source.getCharges().stream()
                .filter(c -> c instanceof AvailableService)
                .map(s -> new JsonAvailableService((AvailableService) s))
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Bill} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Bill toModelType() throws IllegalValueException {
        if (room == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, RoomId.class.getSimpleName()));
        }

        if (roomCost == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "room cost"));
        }

        if (total == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "total expenses"));
        }

        final RoomId modelRoomId = new RoomId(room);
        final double modelTotalExpenses = Double.valueOf(total);
        final ArrayList<Chargeable> modelCharges = new ArrayList<>();

        modelCharges.add(new RoomCost(roomCost));

        for (JsonAvailableService service : charged) {
            modelCharges.add(service.toModelType());
        }

        return new Bill(modelRoomId, modelCharges, modelTotalExpenses);
    }
}
