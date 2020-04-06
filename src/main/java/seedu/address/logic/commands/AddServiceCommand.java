package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICEID;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyHotel;
import seedu.address.model.hotel.bill.AvailableService;
import seedu.address.model.hotel.bill.Cost;
import seedu.address.model.ids.AvailableServiceId;

/**
 * Adds available service.
 */
public class AddServiceCommand extends Command {
    public static final String COMMAND_WORD = "addservice";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": add a service to hotel\n"
            + "Parameters: "
            + PREFIX_SERVICEID + "ID "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_COST + "COST\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SERVICEID + "WC "
            + PREFIX_DESCRIPTION + "Wash clothes "
            + PREFIX_COST + "5";

    public static final String MESSAGE_SUCCESS = "New available service added: %1$s (ID: %2$s)";
    public static final String DUPLICATED_MESSAGE = "This ID has been used. Please choose other ID.";

    private String description;
    private Cost cost;
    private AvailableServiceId id;

    /**
     * service without proper_id
     */
    public AddServiceCommand(String description, Cost cost, AvailableServiceId id) {
        requireNonNull(description);
        requireNonNull(cost);
        requireNonNull(id);

        this.description = description;
        this.cost = cost;
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ReadOnlyHotel hotel = model.getHotel();
        AvailableService toAdd = new AvailableService(description, cost, id);

        if (hotel.getAvailableServiceList().stream().anyMatch(availableService -> availableService.getId()
                .equals(toAdd.getId()))) {
            throw new CommandException(DUPLICATED_MESSAGE);
        }

        model.addAvailableService(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, description, id));
    }
}
