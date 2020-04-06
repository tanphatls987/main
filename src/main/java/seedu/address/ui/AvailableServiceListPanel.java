package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.hotel.bill.AvailableService;


/**
 * Available services ui.
 */
public class AvailableServiceListPanel extends UiPart<Region> {
    private static final String FXML = "AvailableServiceListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AvailableServiceListPanel.class);

    @FXML
    private ListView<AvailableService> availableServiceListView;

    public AvailableServiceListPanel(ObservableList<AvailableService> availableServices) {
        super(FXML);
        availableServiceListView.setItems(availableServices);
        availableServiceListView.setCellFactory(listView -> new AvailableServiceViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code AvailableService} using
     * a {@code AvailableServiceCard}.
     */
    class AvailableServiceViewCell extends ListCell<AvailableService> {
        @Override
        protected void updateItem(AvailableService service, boolean empty) {
            super.updateItem(service, empty);

            if (empty || service == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AvailableServiceCard(service).getRoot());
            }
        }
    }
}
