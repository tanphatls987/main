package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyBookKeeper;
import seedu.address.model.ReadOnlyHotel;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Morpheus data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private HotelStorage hotelStorage;
    private BookKeeperStorage bookKeeperStorage;


    public StorageManager(AddressBookStorage addressBookStorage,
                          HotelStorage hotelStorage,
                          BookKeeperStorage bookKeeperStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.hotelStorage = hotelStorage;
        this.bookKeeperStorage = bookKeeperStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ Morpheus methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public Path getHotelFilePath() {
        return hotelStorage.getHotelFilePath();
    }

    @Override
    public Optional<ReadOnlyHotel> readHotel() throws DataConversionException, IOException {
        return readHotel(hotelStorage.getHotelFilePath());
    }

    @Override
    public Optional <ReadOnlyHotel> readHotel(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return hotelStorage.readHotel(filePath);
    }

    @Override
    public Path getBookKeeperFilePath() {
        return bookKeeperStorage.getBookKeeperFilePath();
    }

    @Override
    public Optional<ReadOnlyBookKeeper> readBookKeeper() throws DataConversionException, IOException {
        return readBookKeeper(bookKeeperStorage.getBookKeeperFilePath());
    }

    @Override
    public Optional<ReadOnlyBookKeeper> readBookKeeper(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return bookKeeperStorage.readBookKeeper(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    @Override
    public void saveHotel(ReadOnlyHotel hotel) throws IOException {
        saveHotel(hotel, hotelStorage.getHotelFilePath());
    }

    @Override
    public void saveHotel(ReadOnlyHotel hotel, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        hotelStorage.saveHotel(hotel, filePath);
    }

    @Override
    public void saveBookKeeper(ReadOnlyBookKeeper bookKeeper) throws IOException {
        saveBookKeeper(bookKeeper, bookKeeperStorage.getBookKeeperFilePath());
    }

    @Override
    public void saveBookKeeper(ReadOnlyBookKeeper bookKeeper, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        bookKeeperStorage.saveBookKeeper(bookKeeper, filePath);
    }

}
