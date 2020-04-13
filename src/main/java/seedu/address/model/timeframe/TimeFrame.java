package seedu.address.model.timeframe;

import static java.time.LocalTime.NOON;
import static java.time.temporal.ChronoUnit.DAYS;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.timeframe.exception.InvalidTimeFrameException;

/**
 * Represent a period of time.
 */
public class TimeFrame {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Construct a new timeframe.
     * Use LocalDateTime.MIN or LocalDateTime.MAX in case no start or end time.
     * @param from
     * @param to
     * @throws InvalidTimeFrameException
     */
    public TimeFrame(LocalDateTime from, LocalDateTime to) throws InvalidTimeFrameException {
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
    public LocalDateTime getStartTime() {
        return from;
    }

    /**
     * End time getter
     * @return
     */
    public LocalDateTime getEndTime() {
        return to;
    }

    /**
     * Set end time of the timeframe to toDate
     * @param td
     */
    public void setEndTime(LocalDateTime toDate) {
        to = toDate;
    }

    /**
     * Gets the number of days in the time frame
     */
    public long getDays() {
        long days = from.until(to, DAYS);

        if (LocalDateTime.now().toLocalTime().isAfter(NOON)) {
            days++;
        }

        return days;
    }

    /**
     * Get the number of days different from the fromDate to the current toDate
     * @param fromDate
     * @return
     */
    public long getDaysDiff(LocalDateTime fromDate) {
        return fromDate.until(to, DAYS) + 1;
    }

    /**
     * Check if 2 timeframe clash.
     * @param oth
     * @return true if at least 1 second period is common in 2 timeframe
     */
    public boolean isClash(TimeFrame oth) {
        LocalDateTime commonLeft = from;
        if (commonLeft.isBefore(oth.from)) {
            commonLeft = oth.from;
        }
        LocalDateTime commonRight = to;
        if (commonRight.isAfter(oth.to)) {
            commonRight = oth.to;
        }
        return commonLeft.isBefore(commonRight);
    }

    /**
     * Check if oth is inside current timeframe.
     * @param oth other timeframe
     * @return true if is inside
     */
    public boolean isInside(TimeFrame oth) {
        return isInside(oth.from) && isInside(oth.to);
    }

    /**
     * Check if the time is inside the timeframe.
     * @param time the time that is going to be checked
     * @return true if the time is between from and to.
     */
    public boolean isInside(LocalDateTime time) {
        return !from.isAfter(time) && !to.isBefore(time);
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

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedFrom = from.format(formatter);
        String formattedTo = to.format(formatter);
        return "From: " + formattedFrom + " To: " + formattedTo + "\n";
    }
}
