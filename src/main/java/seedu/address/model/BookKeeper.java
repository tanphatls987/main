package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.hotel.Stay;
import seedu.address.model.hotel.bill.AvailableService;
import seedu.address.model.hotel.bill.Bill;
import seedu.address.model.hotel.bill.Cost;
import seedu.address.model.hotel.bill.RoomCost;
import seedu.address.model.hotel.bill.UniqueBillList;
import seedu.address.model.ids.PersonId;
import seedu.address.model.ids.RoomId;

/**
 * Manages the bills of guests in the hotel
 */
public class BookKeeper implements ReadOnlyBookKeeper {

    private final UniqueBillList bills;

    /**
     * Creates a new empty bookkeeper.
     */
    public BookKeeper() {
        {
            bills = new UniqueBillList();
        }
    }

    /**
     * Creates a BookKeeper using the bills in {@code toBeCopied}
     */
    public BookKeeper(ReadOnlyBookKeeper toBeCopied) {
        this();
        requireNonNull(toBeCopied);
        setBills(toBeCopied.getBillList());
    }

    /**
     * Replaces the contents of the bill list with {@code bills}.
     * {@code bills} must not contain duplicate persons.
     */
    public void setBills(List<Bill> bills) {
        this.bills.setBills(bills);
    }

    /**
     * Adds a bill to the bookkeeper.
     * Bill must not already exist in the bookkeeper.
     */
    public void addBill(Bill bill) {
        bills.add(bill);
    }

    /**
     * Removes bill for {@code roomId} from this {@code BookKeeper}.
     * Bill must exist in the book keeper.
     */
    public void deleteBill(RoomId roomId) {
        Bill bill = bills.asUnmodifiableObservableList()
                .stream()
                .filter(b -> b.getRoomId().equals(roomId))
                .findFirst()
                .get();

        bills.remove(bill);
    }

    /**
     * Returns true if a bill with the same identity as {@code bill} exists in the book keeper.
     */
    public boolean hasBill(Bill bill) {
        requireNonNull(bill);
        return bills.contains(bill);
    }

    /**
     * Charges {@code roomCost} to the bill of {@code roomId}.
     */
    public void chargeRoomCostToBill(RoomId roomId, RoomCost roomCost, Stay stay) {
        Bill bill = bills.asUnmodifiableObservableList()
                .stream()
                .filter(b -> b.getRoomId().equals(roomId))
                .findFirst()
                .get();

        bill.addRoomCost(roomCost, stay);
    }


    /**
     * Charges {@code service} to the bill of {@code roomId}.
     */
    public void chargeServiceToBill(RoomId roomId, AvailableService service) {
        Bill bill = bills.asUnmodifiableObservableList()
            .stream()
            .filter(b -> b.getRoomId().equals(roomId))
            .findFirst()
            .get();

        bill.addService(service);
    }

    /**
     * Returns the total cost of all bills belonging to {@code personId}.
     */
    public Cost getGuestBillsTotal(PersonId personId) {
        double total = bills.asUnmodifiableObservableList()
                .stream()
                .filter(b -> b.getPersonId().equals(personId))
                .map(b -> b.getBillTotal().getCostAsDouble())
                .reduce(0.0, (subtotal, billTotal) -> subtotal + billTotal);

        return new Cost(total);
    }

    /**
     * Returns the list of bills for {@code personId}.
     */
    public ObservableList<Bill> getBills(PersonId personId) {
        return bills.asUnmodifiableObservableList()
            .stream()
            .filter(b -> b.getPersonId().equals(personId))
            .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    /**
     * Returns the bill for {@code roomId}.
     */
    public Optional<Bill> getBill(RoomId roomId) {
        return bills.asUnmodifiableObservableList()
            .stream()
            .filter(b -> b.getRoomId().equals(roomId))
            .findFirst();
    }

    // util methods

    @Override
    public ObservableList<Bill> getBillList() {
        return bills.asUnmodifiableObservableList();
    }

    @Override
    public String toString() {
        return bills.asUnmodifiableObservableList().size() + " bills";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookKeeper // instanceof handles nulls
                && bills.equals(((BookKeeper) other).bills));
    }

    @Override
    public int hashCode() {
        return bills.hashCode();
    }
}
