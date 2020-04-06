package seedu.address.ui;


import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;

/**Panel showing welcome information*/
public class WelcomePanel extends UiPart<Region> {
    private static final String FXML = "WelcomePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(WelcomePanel.class);
    private final String welcomeMessage = "Welcome to Morpheus. \n " +
            "For help, click on 'Help' \n" +
            "Enter a command below! \n" +
            "reserve i/D000000 rn/001 df/2020-12-12 dt/2020-12-23";
    private final String helpMessage = "For help, click on 'Help'";
    private final String command = "Enter a command below!";
    private final String exampleCommand = "reserve i/D000000 rn/001 df/2020-12-12 dt/2020-12-23";

    @FXML
    Label WelcomeCard;

    public WelcomePanel() {
        super(FXML);
        WelcomeCard.setText(welcomeMessage);
    }
}

