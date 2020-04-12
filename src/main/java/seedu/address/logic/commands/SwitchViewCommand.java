

package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Switch to a specify tab and reset all filter on it.
 */


public class SwitchViewCommand extends Command {
    public static final String COMMAND_WORD = "switch";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " : "
        + "Switch to and reset filter on a selected tab\n"
        + "Parameter: "
        + "TAB_NAME"
        + "(One of {welcome, guest, room, booking, service, bill})\n"
        + "Example: " + COMMAND_WORD + " " + "welcome";

    private final String uiView;

    /**
     * Construcctor.
     * @param uiView
     */
    public SwitchViewCommand(String uiView) {
        this.uiView = uiView;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        switch (uiView) {
        case "welcome" :
            break;
        case "guest" :
            model.updateFilteredPersonList(u -> true);
            break;
        case "room" :
            model.updateFilteredRoomList(u -> true);
            break;
        case "booking" :
            model.updateFilteredBookingList(u -> true);
            break;
        case "service" :
            model.updateFilteredServiceList(u -> true);
            break;
        case "bill":
            model.updateFilteredBillList(u -> true);
            break;
        default:
            throw new CommandException("Not a valid tab name");
        }
        return new CommandResult(
            "Switch tab",
            false,
            false,
            uiView
        );
    }

    @Override
    public boolean equals(Object oth) {
        if (this == oth) {
            return true;
        }
        if (!(oth instanceof SwitchViewCommand)) {
            return false;
        }
        SwitchViewCommand othSwitchViewCommand = (SwitchViewCommand) oth;
        return this.uiView.equals(othSwitchViewCommand.uiView);
    }
}
