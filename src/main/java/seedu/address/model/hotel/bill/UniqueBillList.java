package seedu.address.model.hotel.bill;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.hotel.bill.exceptions.BillNotFoundException;
import seedu.address.model.hotel.bill.exceptions.DuplicateBillException;


/**
 * A list of BILLS that enforces uniqueness between its elements and does not allow nulls.
 * A bill is considered unique by comparing using {@code Bill#isSameBill(Bill)}. As such,
 * adding and updating of bills uses Bills#isSameBille(Bill) for equality so as to ensure
 * that the bill being added or updated is unique in terms of personId & roomId in the UniqueBillList. However,
 * the removal of a bill uses Bill#equals(Object) so as to ensure that the bill with exactly the same
 * fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Bill#isSameBill(Bill)
 */
public class UniqueBillList implements Iterable<Bill> {

    private final ObservableList<Bill> internalList = FXCollections.observableArrayList();
    private final ObservableList<Bill> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent bill as the given argument.
     */
    public boolean contains(Bill toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameBill);
    }

    /**
     * Adds a service to the list.
     * The service must not already exist in the list.
     */
    public void add(Bill toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateBillException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the bill {@code target} in the list with {@code editedBill}.
     * {@code target} must exist in the list.
     * The bill identity of {@code editedBill} must not be the same as another existing bill in the list.
     */
    public void setBill(Bill target, Bill editedBill) {
        requireAllNonNull(target, editedBill);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new BillNotFoundException();
        }

        if (!target.isSameBill(editedBill) && contains(editedBill)) {
            throw new DuplicateBillException();
        }

        internalList.set(index, editedBill);
    }

    /**
     * Replaces the contents of this {@code internalList} with
     * {@code internalList} of {@code replacement}.
     */
    public void setBill(UniqueBillList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }
    /**
     * Replaces the contents of this list with {@code bills}.
     * {@code bills} must not contain duplicate bills.
     */
    public void setBills(List<Bill> bills) {
        requireAllNonNull(bills);
        if (!billsAreUnique(bills)) {
            throw new DuplicateBillException();
        }

        internalList.setAll(bills);
    }

    /**
     * Removes the equivalent bill from the list.
     * The bill must exist in the list.
     */
    public void remove(Bill toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new BillNotFoundException();
        }
    }


    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Bill> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Bill> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueBillList // instanceof handles nulls
                && internalList.equals(((UniqueBillList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code bills} contains only unique bills.
     */
    private boolean billsAreUnique(List<Bill> bills) {
        for (int i = 0; i < bills.size() - 1; i++) {
            for (int j = i + 1; j < bills.size(); j++) {
                if (bills.get(i).isSameBill(bills.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
