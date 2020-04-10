package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.hotel.booking.Booking;

/**
 * An UI component that displays information of a {@code Booking}.
 */
public class BookingCard extends UiPart<Region> {
    private static final String FXML = "BookingCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    public final Booking booking;

    @FXML
    private HBox cardPane;
    @FXML
    private Label payee;
    @FXML
    private Label room;
    @FXML
    private Label duration;
    @FXML
    private Label bookingId;

    public BookingCard(Booking booking) {
        super(FXML);
        this.booking = booking;
        payee.setText(booking.getPayee().getName().toString() + "  " + booking.getPayee().getPersonId().toString());
        room.setText("Room: " + booking.getRoom().getRoomNum());
        duration.setText(booking.getBookDuration().toString());
        bookingId.setText(booking.getReadableBookingId());

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RoomCard)) {
            return false;
        }

        // state check
        BookingCard card = (BookingCard) other;
        return payee.getText().equals(card.payee.getText())
                && room.equals(card.room);
    }

}
