package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.hotel.bill.Bill;

/**
 * An UI component that displays information of a {@code Bill}.
 */
public class BillCard extends UiPart<Region> {
    private static final String FXML = "BillCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    public final Bill bill;

    @FXML
    private HBox cardPane;
    @FXML
    private Label roomNum;
    @FXML
    private Label personId;
    @FXML
    private Label details;

    /**
     * Creates new card given a bill.
     */
    public BillCard(Bill bill) {
        super(FXML);
        this.bill = bill;

        roomNum.setText("Room: " + bill.getRoomId().toString());
        personId.setText("Guest: " + bill.getPersonId().toString());
        details.setText(bill.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instance of this
        if (!(other instanceof BillCard)) {
            return false;
        }

        // state check
        BillCard otherCard = (BillCard) other;
        return roomNum.getText().equals(otherCard.roomNum.getText())
                && details.getText().equals(otherCard.details.getText());
    }
}
