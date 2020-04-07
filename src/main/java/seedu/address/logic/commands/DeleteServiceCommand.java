package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICEID;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyHotel;
import seedu.address.model.ids.AvailableServiceId;


/**
 * Deletes an available services
 */
public class DeleteServiceCommand extends Command {
    public static final String COMMAND_WORD = "deleteservice";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": delete an available service from hotel"
            + "Parameters: "
            + PREFIX_SERVICEID + "ID "
            + PREFIX_DESCRIPTION + "DESCRPTION\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SERVICEID + "WC\n";

    public static final String MESSAGE_SUCCESS = "The available service with ID: %1$s has been deleted";
    public static final String MESSAGE_SERVICE_NOT_FOUND = "The system has no service with id: %1$s. Please try again";

    private AvailableServiceId id;

    /**
     * creates an DeleteServiceCommand with given id.
     * @param id: an id
     */
    public DeleteServiceCommand(AvailableServiceId id) {
        requireNonNull(id);

        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ReadOnlyHotel hotel = model.getHotel();

        if (hotel.getAvailableServiceList().stream().noneMatch(availableService -> availableService.getId()
                .equals(this.id))) {
            throw new CommandException(MESSAGE_SERVICE_NOT_FOUND);
        }

        model.deleteAvailableService(id);
        return new CommandResult(String.format(MESSAGE_SUCCESS, id.toString()));
    }

}
