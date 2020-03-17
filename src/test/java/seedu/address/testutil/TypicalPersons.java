package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.hotel.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPersonId("A000000")
            .withPhone("94351253").withRemark("She likes aardvarks.")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withPersonId("B000000")
            .withEmail("johnd@example.com").withPhone("98765432").withRemark("He can't take beer!")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withPersonId("C000000")
            .withPhone("95352563")
            .withEmail("heinz@example.com").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withPersonId("D000000")
            .withPhone("87652533")
            .withEmail("cornelia@example.com").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withPersonId("E000000")
            .withPhone("9482224")
            .withEmail("werner@example.com").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPersonId("F000000")
            .withPhone("9482427")
            .withEmail("lydia@example.com").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withPersonId("G000000").withPhone("9482442")
            .withEmail("anna@example.com").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPersonId("H000000")
            .withPhone("8482424")
            .withEmail("stefan@example.com").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPersonId("I000000")
            .withPhone("8482131")
            .withEmail("hans@example.com").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withPersonId(VALID_PERSON_ID_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withPersonId(VALID_PERSON_ID_AMY).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
