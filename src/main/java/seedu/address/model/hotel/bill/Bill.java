package seedu.address.model.hotel.bill;

import java.util.ArrayList;

import seedu.address.model.ids.RoomId;

/**
 * Contains a list of chargeable objects that a guest has requested for a specific room.
 */
public class Bill {
    private RoomId roomId;
    private ArrayList<Chargeable> charges;
    private double totalExpenses;

    /**
     * Creates an empty {@code bill}.
     */
    public Bill(RoomId roomId) {
        this.roomId = roomId;
        this.charges = new ArrayList<>();
    }

    /**
     * Adds {@code chargeable} to the stored list of chargeable objects.
     */
    public void addChargeable(Chargeable chargeable) {
        charges.add(chargeable);
        totalExpenses += chargeable.getCost().getCostAsDouble();
    }

    /**
     * Returns true if there is no chargeable objects in the bill.
     */
    public boolean isEmpty() {
        return charges.isEmpty();
    }

    public RoomId getRoomId() {
        return roomId;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        for (Chargeable charge : charges) {
            builder.append(charge);
        }

        builder.append("Total payable: $" + Double.toString(totalExpenses));

        return builder.toString();
    }
}

