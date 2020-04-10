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
import seedu.address.model.ReadOnlyBookKeeper;

/**
 * A class to access BookKeeper data stored as a json file on the hard disk.
 */
public class JsonBookKeeperStorage implements BookKeeperStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonHotelStorage.class);

    private Path filePath;

    public JsonBookKeeperStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getBookKeeperFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyBookKeeper> readBookKeeper() throws DataConversionException {
        return readBookKeeper(filePath);
    }

    /**
     * Similar to {@link #readBookKeeper()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyBookKeeper> readBookKeeper(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableBookKeeper> jsonBookKeeper = JsonUtil.readJsonFile(
                filePath, JsonSerializableBookKeeper.class);
        if (!jsonBookKeeper.isPresent()) {
            logger.warning("BookKeeper not found");
            return Optional.empty();
        }

        try {
            return Optional.of(jsonBookKeeper.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveBookKeeper(ReadOnlyBookKeeper bookKeeper) throws IOException {
        saveBookKeeper(bookKeeper, filePath);
    }

    /**
     * Similar to {@link #saveBookKeeper(ReadOnlyBookKeeper)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveBookKeeper(ReadOnlyBookKeeper bookKeeper, Path filePath) throws IOException {
        requireNonNull(bookKeeper);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableBookKeeper(bookKeeper), filePath);
    }

}
