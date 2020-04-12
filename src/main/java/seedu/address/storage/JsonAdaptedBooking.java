package seedu.address.storage;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Hotel;
import seedu.address.model.hotel.booking.Booking;
import seedu.address.model.hotel.person.Name;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.ids.PersonId;

/**
 * Jackson-friendly version of {@link Booking}.
 */
public class JsonAdaptedBooking {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Booking's %s field is missing!";
    private static final String MISSING_ROOM_MESSAGE_FORMAT = "Room %s does not exist!";
    private final String room;
    private final String payeeName;
    private final String payeeId;
    private final String timeFrom;
    private final String timeTo;
    private final String uuid;

    /**
     * Constructs a {@code JsonAdaptedBooking} with the given booking details.
     */
    @JsonCreator
    public JsonAdaptedBooking(@JsonProperty("room") String roomNum,
                              @JsonProperty("payeeName") String payeeName,
                              @JsonProperty("payeeId") String payeeId,
                              @JsonProperty("from") String timeFrom,
                              @JsonProperty("to") String timeTo,
                              @JsonProperty("uuid") String uuid) {
        this.room = roomNum;
        this.payeeName = payeeName;
        this.payeeId = payeeId;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.uuid = uuid;
    }

    /**
     * Converts a given {@code Booking} into this class for Jackson use.
     */
    public JsonAdaptedBooking(Booking source) {
        room = source.getRoom().getRoomNum();
        payeeName = source.getPayee().getName().toString();
        payeeId = source.getPayee().getPersonId().toString();
        timeFrom = source.getTimeFrom().toString();
        timeTo = source.getTimeTo().toString();
        uuid = source.getBookingId();
    }

    /**
     * Converts this Jackson-friendly adapted booking object into the model's {@code Booking} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted booking.
     */
    public Booking toModelType(Hotel hotel) throws IllegalValueException {

        if (room == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Room.class.getSimpleName()));
        }

        Optional<Room> optionalRoom = hotel.getRoom(room);
        if (optionalRoom.isEmpty()) {
           throw new IllegalValueException(String.format(MISSING_ROOM_MESSAGE_FORMAT, room));
        }
        final Room modelRoom = optionalRoom.get();

        if (payeeName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "PayeeName"));
        }

        if (payeeId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "PayeeId"));
        }

        final Person modelPayee = new Person(new Name(payeeName), new PersonId(payeeId));

        if (timeFrom == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "From"));
        }

        if (timeTo == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "To"));
        }

        if (uuid == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "BookingId"));
        }

        final LocalDateTime modelTimeFrom = LocalDateTime.parse(timeFrom);
        final LocalDateTime modelTimeTo = LocalDateTime.parse(timeTo);

        return new Booking(modelPayee, modelRoom, modelTimeFrom, modelTimeTo, uuid);
    }
}


