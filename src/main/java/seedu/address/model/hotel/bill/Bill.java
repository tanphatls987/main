package seedu.address.model.hotel.bill;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.hotel.Stay;
import seedu.address.model.ids.PersonId;
import seedu.address.model.ids.RoomId;

/**
 * Contains a list of chargeable objects that a guest has requested for a specific room.
 */
public class Bill {
    private PersonId personId;
    private RoomId roomId;
    private ArrayList<Chargeable> charges;
    private double billTotal;

    /**
     * Creates an empty {@code bill}.
     */
    public Bill(PersonId personId, RoomId roomId) {
        this.personId = personId;
        this.roomId = roomId;
        this.charges = new ArrayList<>();
    }

    /**
     * Creates a {@code bill} with all fields present. For storage purposes.
     */
    public Bill(PersonId personId, RoomId roomId, ArrayList<Chargeable> charges, double totalExpenses) {
        requireAllNonNull(personId, roomId, charges, totalExpenses);
        this.personId = personId;
        this.roomId = roomId;
        this.charges = charges;
        this.billTotal = totalExpenses;
    }

    /**
     * Adds {@code roomCost} to the stored list of chargeable objects.
     */
    public void addRoomCost(RoomCost roomCost, Stay stay) {
        charges.add(roomCost);
        long daysOfStay = stay.getBookDuration().getDays();

        billTotal += roomCost.getCostAsDouble() * daysOfStay;
    }

    /**
     * Adds {@code service} to the stored list of chargeable objects.
     */
    public void addService(AvailableService service) {
        charges.add(service);
        billTotal += service.getCost().getCostAsDouble();
    }

    /**
     * Returns true if there is no chargeable objects in the bill.
     */
    public boolean isEmpty() {
        return charges.isEmpty();
    }

    public PersonId getPersonId() {
        return personId;
    }

    public RoomId getRoomId() {
        return roomId;
    }

    public ObservableList<Chargeable> getCharges() {
        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(charges));
    }

    public Cost getBillTotal() {
        return new Cost(billTotal);
    }

    /***
     * Checks if bill is same as {@code bill}
     */
    public boolean isSameBill(Bill bill) {
        if (bill == this) {
            return true;
        }

        return bill != null
                && bill.getPersonId().equals(getPersonId())
                && bill.getRoomId().equals(getRoomId());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        for (Chargeable charge : charges) {
            if (charge instanceof RoomCost) {
                builder.append("- Room Cost ($" + charge + " per night)\n");
            } else {
                builder.append("- " + charge + "\n");
            }
        }

        builder.append("Total payable: $" + Double.toString(billTotal));

        return builder.toString();
    }
}

