package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.hotel.bill.AvailableService;
import seedu.address.model.hotel.bill.Cost;
import seedu.address.model.ids.AvailableServiceId;


/**
 * Jackson-friendly version of {@link seedu.address.model.hotel.bill.AvailableService}.
 */
public class JsonAvailableService {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Service'field %d is missing!";
    private final String description;
    private final String id;
    private final String cost;

    /**
     * Constructs a {@code JsonAvailableService} with the given service details.
     */
    @JsonCreator
    public JsonAvailableService(@JsonProperty("description") String description,
                                @JsonProperty("id") String id,
                                @JsonProperty("cost") String cost) {
        this.description = description;
        this.id = id;
        this.cost = cost;
    }

    /**
     * Converts a given {@code AvailableService} into this class for Jackson use.
     */
    public JsonAvailableService(AvailableService source) {
        description = source.getDescription();
        id = source.getId().toString();
        cost = source.getCost().toString();
    }

    /**
     * Converts this Jackson-friendly adapted service object into the model's {@code AvailableService} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted service.
     */
    public AvailableService toModelType() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "descrption")
            );
        }

        if (id == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, AvailableServiceId.class.getSimpleName())
            );
        }

        if (cost == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Cost.class.getSimpleName())
            );
        }

        return new AvailableService(description, new Cost(cost), new AvailableServiceId(id));
    }
}
