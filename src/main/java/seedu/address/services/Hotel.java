package seedu.address.services;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.hotel.Room;
import seedu.address.model.hotel.person.Person;
import seedu.address.storage.Storage;

/**
 *
 */
public class Hotel implements Model {
    private Storage storage;
    private List<Room> rooms;
    private List<Person> persons;

    /**
     * [Hotel description]
     * @return [description]
     */
    public Hotel() {
        persons = new ArrayList<>();
        rooms = new ArrayList<>();
    }

    /**
     * Create new customer.
     */
    public void createCustomer() {
        ///TODO
    }

    /**
     * Create new rooms.
     */
    public void createRoom(String roomName) {

    }

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     *
     * @param userPrefs
     */
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {

    }

    /**
     * Returns the user prefs.
     */
    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return null;
    }

    /**
     * Returns the user prefs' GUI settings.
     */
    @Override
    public GuiSettings getGuiSettings() {
        return null;
    }

    /**
     * Sets the user prefs' GUI settings.
     *
     * @param guiSettings
     */
    @Override
    public void setGuiSettings(GuiSettings guiSettings) {

    }

    /**
     * Returns the user prefs' address book file path.
     */
    @Override
    public Path getAddressBookFilePath() {
        return null;
    }

    /**
     * Sets the user prefs' address book file path.
     *
     * @param addressBookFilePath
     */
    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {

    }

    /**
     * Replaces address book data with the data in {@code addressBook}.
     *
     * @param addressBook
     */
    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {

    }

    /**
     * Returns the AddressBook
     */
    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return null;
    }

    public boolean hasPerson(Person who) {
        return false;
    }

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     *
     * @param target
     */
    @Override
    public void deletePerson(Person target) {

    }

    /**
     * Add a new person to guest list.
     * @param person
     */
    public void addPerson(Person person) {
        assert(!hasPerson(person));
        persons.add(person);
    }

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     *
     * @param target
     * @param editedPerson
     */
    @Override
    public void setPerson(Person target, Person editedPerson) {

    }

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return null;
    }

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @param predicate
     * @throws NullPointerException if {@code predicate} is null.
     */
    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {

    }
}
