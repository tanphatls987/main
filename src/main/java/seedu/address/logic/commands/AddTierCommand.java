package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIER;

/**
 * Add a tier, can grouped with list of rooms.
 */
public class AddTierCommand {
    public static final String COMMAND_WORD = "addtier";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tier name. "
            + "Parameters: "
            + PREFIX_TIER + "ROOM_NUM "
            + PREFIX_ROOMNUMBER
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TIER + "12E" + PREFIX_ROOMNUMBER + "abc def";

    public static final String MESSAGE_SUCCESS = "New tier added: %1s";
    public static final String MESSAGE_DUPLICATE_TIER = "This tier name was added before!";
    public static final String MESSAGE_ROOM_NOT_FOUND = "One room does not exist in the hotel!";
}
