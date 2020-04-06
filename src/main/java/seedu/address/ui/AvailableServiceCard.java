package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import seedu.address.model.hotel.bill.AvailableService;

/**
 * An UI component that displays information of a {@code AvailableService}.
 */
public class AvailableServiceCard extends UiPart<Region> {
    private static final String FXML = "AvailableServiceCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    public final AvailableService service;

    @FXML
    private HBox cardPane;

    @FXML
    private Label id;

    @FXML
    private Label description;

    @FXML
    private Label cost;

    /**
     * Creates new card given a service.
     */
    public AvailableServiceCard(AvailableService service) {
        super(FXML);
        this.service = service;

        id.setText(service.getId().toString());
        description.setText(service.getDescription());
        cost.setText(service.getCost().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instance of this
        if (!(other instanceof AvailableServiceCard)) {
            return false;
        }

        // state check
        AvailableServiceCard otherCard = (AvailableServiceCard) other;
        return description.getText().equals(otherCard.description.getText())
                && cost.getText().equals(otherCard.cost.getText())
                && id.getText().equals(otherCard.id.getText());
    }
}
