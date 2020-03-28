package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyHotel;

/**
 * Represents a storage for {@link seedu.address.model.Hotel}.
 */
public interface HotelStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getHotelFilePath();

    /**
     * Returns Hotel data as a {@link ReadOnlyHotel}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyHotel> readHotel() throws DataConversionException, IOException;

    /**
     * @see #getHotelFilePath()
     */
    Optional<ReadOnlyHotel> readHotel(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyHotel} to the storage.
     * @param hotel cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveHotel(ReadOnlyHotel hotel) throws IOException;

    /**
     * @see #saveHotel(ReadOnlyHotel)
     */
    void saveHotel(ReadOnlyHotel hotel, Path filePath) throws IOException;

}

