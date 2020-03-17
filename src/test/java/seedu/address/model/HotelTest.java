package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.jupiter.api.Test;
/**
 * Test for Hotel
 */
public class HotelTest {
    private final Hotel hotel = new Hotel();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), hotel.getRoomList());
    }
}
