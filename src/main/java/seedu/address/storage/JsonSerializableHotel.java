package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Hotel;
import seedu.address.model.ReadOnlyHotel;
import seedu.address.model.hotel.room.Room;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
public class JsonSerializableHotel {


    public static final String MESSAGE_DUPLICATE_ROOMS = "Room list contains duplicate room(s).";
    private final List<JsonAdaptedRoom> rooms = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableHotel(@JsonProperty("rooms") List<JsonAdaptedRoom> rooms) {
        this.rooms.addAll(rooms);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param sourceHotel future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableHotel(ReadOnlyHotel sourceHotel) {
        rooms.addAll(sourceHotel.getRoomList().stream()
                .map(JsonAdaptedRoom::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Hotel toModelType() throws IllegalValueException {
        Hotel hotel = new Hotel();
        for (JsonAdaptedRoom jsonAdaptedRoom : rooms) {
            Room room = jsonAdaptedRoom.toModelType();
            if (hotel.hasRoom(room)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ROOMS);
            }
            hotel.addRoom(room);
        }
        return hotel;
    }

}
