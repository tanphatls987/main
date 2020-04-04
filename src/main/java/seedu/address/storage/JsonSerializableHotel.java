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
import seedu.address.model.hotel.booking.Booking;
import seedu.address.model.hotel.room.Room;

/**
 * An Immutable Hotel that is serializable to JSON format.
 */
@JsonRootName(value = "hotel")
public class JsonSerializableHotel {


    public static final String MESSAGE_DUPLICATE_ROOMS = "Room list contains duplicate room(s).";
    private static final String MESSAGE_DUPLICATE_BOOKINGS = "Booking list contains duplicate booking(s)";
    private final List<JsonAdaptedRoom> rooms = new ArrayList<>();
    private final List<JsonAdaptedBooking> bookings = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableHotel} with the given rooms and bookings.
     */
    @JsonCreator
    public JsonSerializableHotel(@JsonProperty("rooms") List<JsonAdaptedRoom> rooms,
                                 @JsonProperty("bookings") List<JsonAdaptedBooking> bookings) {
        this.rooms.addAll(rooms);
        this.bookings.addAll(bookings);
    }

    /**
     * Converts a given {@code ReadOnlyHotel} into this class for Jackson use.
     *
     * @param sourceHotel future changes to this will not affect the created {@code JsonSerializableHotel}.
     */
    public JsonSerializableHotel(ReadOnlyHotel sourceHotel) {
        rooms.addAll(sourceHotel.getRoomList().stream()
                .map(JsonAdaptedRoom::new).collect(Collectors.toList()));
        bookings.addAll(sourceHotel.getBookingList().stream()
                .map(JsonAdaptedBooking::new).collect(Collectors.toList()));
    }

    /**
     * Converts this hotel into the model's {@code Hotel} object.
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
        for (JsonAdaptedBooking jsonAdaptedBooking : bookings) {
            Booking booking = jsonAdaptedBooking.toModelType();
            if (hotel.hasBooking(booking)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_BOOKINGS);
            }
            hotel.addBooking(booking);
        }
        return hotel;
    }

}
