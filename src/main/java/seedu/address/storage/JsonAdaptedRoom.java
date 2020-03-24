package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.hotel.room.Tier;

/**
 * Jackson-friendly version of {@link Room}.
 */
public class JsonAdaptedRoom {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Room's %s field is missing!";
    private final String tier;
    private final String number;
    //private final String Id;

    /**
     * Constructs a {@code JsonAdaptedRoom} with the given room details.
     */
    @JsonCreator
    public JsonAdaptedRoom(@JsonProperty("Tier") String tier, @JsonProperty("roomNum") String number) {
        this.tier = tier;
        this.number = number;
    }

    /**
     * Converts a given {@code Room} into this class for Jackson use.
     */
    public JsonAdaptedRoom(Room source) {
        tier = source.getTier().toString();
        number = source.getRoomNum();
    }

    /**
     * Converts this Jackson-friendly adapted room object into the model's {@code Room} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted room.
     */
    public Room toModelType() throws IllegalValueException {

        if (tier == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Tier.class.getSimpleName()));
        }
        if (!Tier.isValid(tier)) {
            throw new IllegalValueException(Tier.MESSAGE_CONSTRAINTS);
        }
        final Tier modelTier = new Tier(tier);

        if (number == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "room number"));
        }

        final String modelRoomNumber = number;

        return new Room(modelRoomNumber, modelTier);
    }
}
