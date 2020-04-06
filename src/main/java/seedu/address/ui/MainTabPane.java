package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.logic.Logic;


/**
 * TabPane for traversing between different list.
 */
public class MainTabPane extends UiPart<Region> {
    private static final String FXML = "MainTabPane.fxml";

    private Logic logic;
    private WelcomePanel welcomePanel;
    private PersonListPanel personListPanel;
    private RoomListPanel roomListPanel;
    private BookingListPanel bookingListPanel;
    private AvailableServiceListPanel availableServiceListPanel;
    private MainWindow mainWindow;

    @FXML
    private TabPane mainTabPane;
    @FXML
    private Tab welcomeTab;
    @FXML
    private Tab roomTab;
    @FXML
    private Tab guestTab;
    @FXML
    private Tab bookingTab;
    @FXML
    private StackPane welcomePlaceholder;
    @FXML
    private StackPane personListPlaceholder;
    @FXML
    private StackPane roomListPanelPlaceholder;
    @FXML
    private StackPane bookingListPanelPlaceholder;
    @FXML
    private StackPane availableServiceListPanelPlaceholder;

    /**
     * Create a mainTabPane
     * @param logic
     */
    public MainTabPane(Logic logic) {
        super(FXML);
        this.logic = logic;


        ///set up personListPanel and roomListPanel
        setWelcomePlaceholder();
        setPersonListPanel();
        setRoomListPanel();
        setBookingListPanel();
        setAvailableServiceListPanel();
        mainTabPane.getStyleClass().add("floating");
    }

    private void setWelcomePlaceholder() {
        welcomePanel = new WelcomePanel();
        welcomePlaceholder.getChildren().add(welcomePanel.getRoot());
    }
    private void setPersonListPanel() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPlaceholder.getChildren().add(personListPanel.getRoot());
    }

    private void setRoomListPanel() {
        roomListPanel = new RoomListPanel(logic.getRoomList());
        roomListPanelPlaceholder.getChildren().add(roomListPanel.getRoot());
    }

    private void setBookingListPanel() {
        bookingListPanel = new BookingListPanel(logic.getBookingList());
        bookingListPanelPlaceholder.getChildren().add(bookingListPanel.getRoot());
    }

    private void setAvailableServiceListPanel() {
        availableServiceListPanel = new AvailableServiceListPanel(logic.getHotel().getAvailableServices());
        availableServiceListPanelPlaceholder.getChildren().add(availableServiceListPanel.getRoot());
    }
}
