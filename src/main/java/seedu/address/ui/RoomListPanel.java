package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.hotel.room.Room;

/**
 * Panel containing the list of rooms.
 */
public class RoomListPanel extends UiPart<Region> {
    private static final String FXML = "RoomListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RoomListPanel.class);

    @FXML
    private ListView<Room> RoomListView;

    public RoomListPanel(ObservableList<Room> roomList) {
        super(FXML);
        RoomListView.setItems(roomList);
        RoomListView.setCellFactory(listView -> new RoomListPanel.RoomListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class RoomListViewCell extends ListCell<Room> {
        @Override
        protected void updateItem(Room room, boolean empty) {
            super.updateItem(room, empty);

            if (empty || room == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new RoomCard(room, getIndex() + 1).getRoot());
            }
        }
    }

}
