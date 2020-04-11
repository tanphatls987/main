package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.BookKeeper;
import seedu.address.model.Hotel;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyBookKeeper;
import seedu.address.model.ReadOnlyHotel;
import seedu.address.model.hotel.bill.Bill;
import seedu.address.model.hotel.bill.RoomCost;
import seedu.address.model.hotel.booking.Booking;
import seedu.address.model.hotel.person.Email;
import seedu.address.model.hotel.person.Name;
import seedu.address.model.hotel.person.Person;
import seedu.address.model.hotel.person.Phone;
import seedu.address.model.hotel.person.Remark;
import seedu.address.model.hotel.room.Room;
import seedu.address.model.hotel.room.Tier;
import seedu.address.model.ids.PersonId;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Morpheus} with sample data.
 */
public class SampleDataUtil {
    public static final Remark EMPTY_REMARK = new Remark("");
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new PersonId("A000000"),
                new Phone("87438807"), new Email("alexyeoh@example.com"),
                    EMPTY_REMARK,
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new PersonId("B000000"),
                new Phone("99272758"), new Email("berniceyu@example.com"),
                    EMPTY_REMARK,
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new PersonId("C000000"),
                new Phone("93210283"), new Email("charlotte@example.com"),
                    EMPTY_REMARK,
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new PersonId("D000000"),
                new Phone("91031282"), new Email("lidavid@example.com"),
                    EMPTY_REMARK,
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new PersonId("I000000"),
                new Phone("92492021"), new Email("irfan@example.com"),
                    EMPTY_REMARK,
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new PersonId("R000000"),
                new Phone("92624417"), new Email("royb@example.com"),
                    EMPTY_REMARK,
                getTagSet("colleagues"))
        };
    }

    public static Room[] getSampleRooms() {
        return new Room[] {
            new Room("001", new Tier("GOLD"), new RoomCost("150.00")),
            new Room("002", new Tier("SILVER"), new RoomCost("100.00")),
            new Room("003", new Tier("SILVER"), new RoomCost("100.00")),
            new Room("004", new Tier("BRONZE"), new RoomCost("50.00")),
            new Room("005", new Tier("BRONZE"), new RoomCost("50.00")),
            new Room("006", new Tier("BRONZE"), new RoomCost("50.00"))
        };
    }

    public static Booking[] getSampleBookings() {
        return new Booking[] {
            new Booking(getSamplePersons()[0],
                        getSampleRooms()[0], LocalDateTime.now(),
                        LocalDateTime.of(2020, 12,
                                12, 12, 12, 12))
        };
    }

    private static Bill[] getSampleBills() {
        return new Bill[] {

        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyHotel getSampleHotel() {
        Hotel sampleHotel = new Hotel();
        for (Room sampleRoom: getSampleRooms()) {
            sampleHotel.addRoom(sampleRoom);
        }
        for (Booking sampleBooking: getSampleBookings()) {
            sampleHotel.addBooking(sampleBooking);
        }
        return sampleHotel;
    }

    public static ReadOnlyBookKeeper getSampleBookKeeper() {
        BookKeeper sampleBookKeeper = new BookKeeper();
        for (Bill samepleBill : getSampleBills()) {
            sampleBookKeeper.addBill(samepleBill);
        }
        return sampleBookKeeper;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
