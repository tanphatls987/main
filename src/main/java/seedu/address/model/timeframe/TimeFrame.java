package seedu.address.model.timeframe;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;

import seedu.address.model.timeframe.exception.InvalidTimeFrameException;

/**
 * Represent a period of time.
 */
public class TimeFrame {
    private LocalDate from;
    private LocalDate to;

    /**
     * Construct a new timeframe.
     * Use LocalDate.MIN or LocalDate.MAX in case no start or end time.
     * @param from
     * @param to
     * @throws InvalidTimeFrameException
     */
    public TimeFrame(LocalDate from, LocalDate to) throws InvalidTimeFrameException {
        requireAllNonNull(from, to);
        this.from = from;
        this.to = to;

        if (from.isAfter(to)) {
            throw new InvalidTimeFrameException();
        }
    }

    /**
     * Start time getter
     * @return
     */
    public LocalDate getStartTime() {
        return from;
    }

    /**
     * End time getter
     * @return
     */
    public LocalDate getEndTime() {
        return to;
    }

    /**
     * Check if 2 timeframe clash.
     * @param oth
     * @return true if at least 1 second period is common in 2 timeframe
     */
    public boolean isClash(TimeFrame oth) {
        LocalDate commonLeft = from;
        if (commonLeft.isBefore(oth.from)) {
            commonLeft = oth.from;
        }
        LocalDate commonRight = to;
        if (commonRight.isAfter(oth.to)) {
            commonRight = oth.to;
        }
        return commonLeft.isBefore(commonRight);
    }

    @Override
    public boolean equals(Object oth) {
        if (oth == this) {
            return true;
        }
        if (!(oth instanceof TimeFrame)) {
            return false;
        }
        TimeFrame othTimeFrame = (TimeFrame) oth;
        return othTimeFrame.from.equals(from)
            && othTimeFrame.to.equals(to);
    }
}
