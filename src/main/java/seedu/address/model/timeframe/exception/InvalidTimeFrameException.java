package seedu.address.model.timeframe.exception;

/**
 * Exception for invalid time frame.
 */
public class InvalidTimeFrameException extends RuntimeException {
    public InvalidTimeFrameException() {
        super("Invalid time frame");
    }
}
