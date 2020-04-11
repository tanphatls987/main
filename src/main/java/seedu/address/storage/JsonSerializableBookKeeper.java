package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.BookKeeper;
import seedu.address.model.ReadOnlyBookKeeper;
import seedu.address.model.hotel.bill.Bill;

/**
 * An Immutable BookKeeper that is serializable to JSON format.
 */
@JsonRootName(value = "bookkeeper")
class JsonSerializableBookKeeper {

    public static final String MESSAGE_DUPLICATE_BILL = "Persons list contains duplicate bill(s).";

    private final List<JsonAdaptedBill> bills = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableBookKeeper} with the given bills.
     */
    @JsonCreator
    public JsonSerializableBookKeeper(@JsonProperty("bills") List<JsonAdaptedBill> bills) {
        this.bills.addAll(bills);
    }

    /**
     * Converts a given {@code ReadOnlyBookKeeper} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableBookKeeper}.
     */
    public JsonSerializableBookKeeper(ReadOnlyBookKeeper source) {
        bills.addAll(source.getBillList().stream().map(JsonAdaptedBill::new).collect(Collectors.toList()));
    }

    /**
     * Converts this book keeper into the model's {@code BookKeeper} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public BookKeeper toModelType() throws IllegalValueException {
        BookKeeper bookKeeper = new BookKeeper();

        for (JsonAdaptedBill jsonAdaptedBill : bills) {
            Bill bill = jsonAdaptedBill.toModelType();
            if (bookKeeper.hasBill(bill)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_BILL);
            }
            bookKeeper.addBill(bill);
        }

        return bookKeeper;
    }
}
