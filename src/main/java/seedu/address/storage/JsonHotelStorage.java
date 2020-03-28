package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyHotel;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonHotelStorage implements HotelStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonHotelStorage.class);

    private Path filePath;

    public JsonHotelStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getHotelFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyHotel> readHotel() throws DataConversionException, IOException {
        return readHotel(filePath);
    }

    /**
     * Similar to {@link #readHotel()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyHotel> readHotel(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableHotel> jsonHotel = JsonUtil.readJsonFile(
                filePath, JsonSerializableHotel.class);
        if (!jsonHotel.isPresent()) {
            logger.warning("Hotel not found");
            return Optional.empty();
        }

        try {
            return Optional.of(jsonHotel.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveHotel(ReadOnlyHotel hotel) throws IOException {
        saveHotel(hotel, filePath);
    }

    /**
     * Similar to {@link #saveHotel(ReadOnlyHotel)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveHotel(ReadOnlyHotel hotel, Path filePath) throws IOException {
        requireNonNull(hotel);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableHotel(hotel), filePath);
    }

}

