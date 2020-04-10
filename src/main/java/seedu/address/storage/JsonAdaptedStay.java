package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.hotel.Stay;
import seedu.address.model.hotel.booking.Booking;

/**
 * Jackson-friendly version of {@link Booking}.
 */
public class JsonAdaptedStay extends JsonAdaptedBooking {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Stay's %s field is missing!";

    private final String description;
    @JsonCreator
    public JsonAdaptedStay(@JsonProperty("room") String roomNum,
                              @JsonProperty("payeeName") String payeeName,
                              @JsonProperty("payeeId") String payeeId,
                              @JsonProperty("from") String timeFrom,
                              @JsonProperty("to") String timeTo,
                              @JsonProperty("description") String description) {
        super(roomNum, payeeName, payeeId, timeFrom, timeTo);
        this.description = description;
    }

    public JsonAdaptedStay(Stay stay) {
        super(stay);
        this.description = stay.getDescription();
    }

    /**
     * Constructs a Stay
     */
    public Stay toModelType() throws IllegalValueException {
        Booking booking = super.toModelType();
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "description"));
        }
        return new Stay(booking.getPayee(), booking.getRoom(), booking.getTimeFrom(), booking.getTimeTo(),
                description);
    }
}
