package seedu.address.ui;


import java.util.logging.Logger;

import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;

/**Panel showing welcome information*/
public class WelcomePanel extends UiPart<Region> {
    private static final String FXML = "WelcomePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(WelcomePanel.class);

    public WelcomePanel() {
        super(FXML);
    }
}

