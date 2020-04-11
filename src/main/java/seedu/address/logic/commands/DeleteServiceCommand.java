package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICEID;

import java.util.List;

import seedu.address.commons.core.either.Either;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyHotel;
import seedu.address.model.hotel.bill.AvailableService;
import seedu.address.model.ids.AvailableServiceId;



/**
 * Deletes an available services
 */
public class DeleteServiceCommand extends Command { public static final String COMMAND_WORD = "deleteservice";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": delete an available service from hotel by service id"
            + "or by  the index number used in the displayed room list"
            + "Parameters: "
            + PREFIX_SERVICEID + "ID\n"
            + "or \n"
            + "Parameters: INDEX (must be positive)"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SERVICEID + "WC\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "The available service with ID: %1$s has been deleted";
    public static final String MESSAGE_SERVICE_NOT_FOUND = "The system has no service with id: %1$s. Please try again";
    public static final String MESSAGE_INDEX_INVALID = "Invalid index";

    private final Either<AvailableServiceId, Index> toDelete;
    /**
     * creates an DeleteServiceCommand with given id.
     * @param id: an id
     */
    public DeleteServiceCommand(AvailableServiceId id) {
        requireNonNull(id);

        this.toDelete = Either.ofLeft(id);
    }

    /**
     * creates and DeleteServiceCommand with a given index.
     */
    public DeleteServiceCommand(Index targetIndex) {
        requireNonNull(targetIndex);

        this.toDelete = Either.ofRight(targetIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ReadOnlyHotel hotel = model.getHotel();

        if (this.toDelete.isLeft()) {
            AvailableServiceId id = this.toDelete.getLeft();
            if (hotel.getAvailableServiceList().stream().noneMatch(availableService -> availableService.getId()
                    .equals(id))) {
                throw new CommandException(String.format(MESSAGE_SERVICE_NOT_FOUND, id.toString()));
            }
            model.deleteAvailableService(id);
            return new CommandResult(String.format(MESSAGE_SUCCESS, id.toString()), "service");
        } else {
            List<AvailableService> services = model.getFilteredServiceList();
            Index index = this.toDelete.getRight();
            if (index.getZeroBased() >= services.size()) {
                throw new CommandException(MESSAGE_INDEX_INVALID);
            }
            AvailableService service = services.get(index.getZeroBased());
            model.deleteAvailableService(service.getId());
            return new CommandResult(String.format(MESSAGE_SUCCESS, service.getId().toString()), "service");
        }

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteServiceCommand)) {
            return false;
        }

        DeleteServiceCommand otherCommand = (DeleteServiceCommand) other;
        return this.toDelete.equals(otherCommand.toDelete);
    }

}
