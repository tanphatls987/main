package seedu.address.ui;

import java.util.HashMap;

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
    private BillListPanel billListPanel;
    private MainWindow mainWindow;

    private HashMap<String, Tab> tabMapping;

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
    private Tab availableServiceTab;
    @FXML
    private Tab billTab;
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
    @FXML
    private StackPane billListPanelPlaceholder;

    /**
     * Create a mainTabPane
     * @param logic
     */
    public MainTabPane(Logic logic) {
        super(FXML);
        this.logic = logic;


        ///set up panels
        setWelcomePlaceholder();
        setPersonListPanel();
        setRoomListPanel();
        setBookingListPanel();
        setAvailableServiceListPanel();
        setBillListPanel();
        mainTabPane.getStyleClass().add("floating");



        initializeMapping();
    }

    /**
     * Create mapping between name and tab.
     */
    private void initializeMapping() {
        tabMapping = new HashMap<>();
        tabMapping.put("welcome", welcomeTab);
        tabMapping.put("room", roomTab);
        tabMapping.put("guest", guestTab);
        tabMapping.put("booking", bookingTab);
        tabMapping.put("service", availableServiceTab);
        tabMapping.put("bill", billTab);
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
        roomListPanel = new RoomListPanel(logic.getFilteredRoomList());
        roomListPanelPlaceholder.getChildren().add(roomListPanel.getRoot());
    }

    private void setBookingListPanel() {
        bookingListPanel = new BookingListPanel(logic.getFilteredBookingList());
        bookingListPanelPlaceholder.getChildren().add(bookingListPanel.getRoot());
    }

    private void setAvailableServiceListPanel() {
        availableServiceListPanel = new AvailableServiceListPanel(logic.getHotel().getAvailableServiceList());
        availableServiceListPanelPlaceholder.getChildren().add(availableServiceListPanel.getRoot());
    }

    private void setBillListPanel() {
        billListPanel = new BillListPanel(logic.getFilteredBillList());
        billListPanelPlaceholder.getChildren().add(billListPanel.getRoot());
    }

    /**
     * Set to corresponding tab with tab name specified.
     * @param tabName
     * @return
     */
    public boolean setCurrentTab(String tabName) {
        if (!tabMapping.containsKey(tabName)) {
            return false;
        }
        Tab selectTab = tabMapping.get(tabName);
        mainTabPane.getSelectionModel().select(selectTab);
        return true;
    }
}
