package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path addressBookFilePath = Paths.get("data" , "addressbook.json");
    private Path hotelFilePath = Paths.get("data", "hotel.json");
    private Path bookKeeperFilePath = Paths.get("data", "bookkeeper.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
        setHotelFilePath(newUserPrefs.getHotelFilePath());
        setBookKeeperFilePath(newUserPrefs.getBookKeeperFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public Path getHotelFilePath() {
        return hotelFilePath;
    }

    public Path getBookKeeperFilePath() {
        return bookKeeperFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    public void setHotelFilePath(Path hotelFilePath) {
        requireNonNull(hotelFilePath);
        this.hotelFilePath = hotelFilePath;
    }

    public void setBookKeeperFilePath(Path bookKeeperFilePath) {
        requireNonNull(bookKeeperFilePath);
        this.bookKeeperFilePath = bookKeeperFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && addressBookFilePath.equals(o.addressBookFilePath)
                && hotelFilePath.equals(o.hotelFilePath)
                && bookKeeperFilePath.equals(o.bookKeeperFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath, hotelFilePath, bookKeeperFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nAddressBook data file location : " + addressBookFilePath);
        sb.append("\nHotel data file location 2 : " + hotelFilePath);
        sb.append("\nBook Keeper data file location 3 : " + bookKeeperFilePath);
        return sb.toString();
    }

}
