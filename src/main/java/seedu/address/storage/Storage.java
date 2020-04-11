package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyBookKeeper;
import seedu.address.model.ReadOnlyHotel;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage, HotelStorage, BookKeeperStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    @Override
    Optional<ReadOnlyHotel> readHotel() throws DataConversionException, IOException;

    @Override
    Optional <ReadOnlyHotel> readHotel(Path filePath) throws DataConversionException, IOException;

    @Override
    void saveHotel(ReadOnlyHotel hotel) throws IOException;

    @Override
    Optional<ReadOnlyBookKeeper> readBookKeeper() throws DataConversionException, IOException;

    @Override
    Path getBookKeeperFilePath();

    @Override
    void saveBookKeeper(ReadOnlyBookKeeper bookKeeper) throws IOException;

}
