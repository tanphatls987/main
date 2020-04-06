package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;

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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": add an available service to hotel"
            + "Parameters: "
            + PREFIX_ID + "ID "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_COST + "COST "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "1"
            + PREFIX_DESCRIPTION + "Wash clothes"
            + PREFIX_COST + "5";

    public static final String MESSAGE_SUCCESS = "New available services added";
    public static final String DUPLICATED_MESSAGE = "This id has been used. Please choose other id";

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
        if (hotel.getAvailableServices().stream().anyMatch(availableService -> availableService.getId()
                .equals(toAdd.getId()))) {
            throw new CommandException(DUPLICATED_MESSAGE);
        }

        model.addAvailableService(toAdd);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
