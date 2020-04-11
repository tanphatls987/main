package seedu.address.model.hotel.bill;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.hotel.bill.exceptions.DuplicateServiceException;
import seedu.address.model.hotel.bill.exceptions.ServiceNotFoundException;

/**
 * A list of AVAILABLESERVICES that enforces uniqueness between its elements and does not allow nulls.
 * A service is considered unique by comparing using {@code AvailableService#isSameService(AvailableService)}. As such,
 * adding and updating of services uses AvailableService#isSameService(AvailableService) for equality so as to ensure
 * that the service being added or updated is unique in terms of serviceId in the UniqueAvailableServiceList. However,
 * the removal of a service uses AvailableService#equals(Object) so as to ensure that the service with exactly the same
 * fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see AvailableService#isSameService(AvailableService)
 */
public class UniqueAvailableServiceList implements Iterable<AvailableService> {

    private final ObservableList<AvailableService> internalList = FXCollections.observableArrayList();
    private final ObservableList<AvailableService> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent service as the given argument.
     */
    public boolean contains(AvailableService toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameService);
    }

    /**
     * Adds a service to the list.
     * The service must not already exist in the list.
     */
    public void add(AvailableService toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateServiceException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the service {@code target} in the list with {@code editedService}.
     * {@code target} must exist in the list.
     * The service identity of {@code editedService} must not be the same as another existing service in the list.
     */
    public void setService(AvailableService target, AvailableService editedService) {
        requireAllNonNull(target, editedService);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ServiceNotFoundException();
        }

        if (!target.isSameService(editedService) && contains(editedService)) {
            throw new DuplicateServiceException();
        }

        internalList.set(index, editedService);
    }

    /**
     * Replaces the contents of this {@code internalList} with
     * {@code internalList} of {@code replacement}.
     */
    public void setServices(UniqueAvailableServiceList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }
    /**
     * Replaces the contents of this list with {@code services}.
     * {@code services} must not contain duplicate services.
     */
    public void setServices(List<AvailableService> services) {
        requireAllNonNull(services);
        if (!servicesAreUnique(services)) {
            throw new DuplicateServiceException();
        }

        internalList.setAll(services);
    }

    /**
     * Removes the equivalent service from the list.
     * The service must exist in the list.
     */
    public void remove(AvailableService toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ServiceNotFoundException();
        }
    }


    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<AvailableService> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<AvailableService> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueAvailableServiceList // instanceof handles nulls
                && internalList.equals(((UniqueAvailableServiceList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code services} contains only unique services.
     */
    private boolean servicesAreUnique(List<AvailableService> services) {
        for (int i = 0; i < services.size() - 1; i++) {
            for (int j = i + 1; j < services.size(); j++) {
                if (services.get(i).isSameService(services.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
