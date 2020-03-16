package seedu.address.model.timeframe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class TimeFrameTest {

    @Test
    void isClash() {
        TimeFrame frame1 = new TimeFrame(LocalDateTime.of(2020, 2, 10, 2, 30, 0),
            LocalDateTime.of(2020, 2, 10, 3, 0, 0));
        TimeFrame frame2 = new TimeFrame(LocalDateTime.of(2020, 2, 10, 3, 0, 0),
            LocalDateTime.MAX);
        TimeFrame frame3 = new TimeFrame(LocalDateTime.MIN,
            LocalDateTime.of(2020, 2, 10, 2, 45, 0));
        assertTrue(frame1.isClash(frame3));
        assertFalse(frame1.isClash(frame2));
        assertFalse(frame2.isClash(frame3));
    }
}
