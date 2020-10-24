package com.cellfishpool.models;

import com.cellfishpool.exception.InvalidSlotException;
import com.cellfishpool.exception.ParkingLotException;
import com.cellfishpool.exception.SlotAlreadyOccupiedException;
import com.cellfishpool.utils.constants.Constants;

import java.util.HashMap;
import java.util.Map;

public class Parking {
    private int capacity;
    private Map<Integer,Slot> slots;

    public int getCapacity() {
        return capacity;
    }

    public Parking(final int capacity) {
        if (capacity > Constants.MAX_PARKING_SLOTS || capacity <= 0) {
            throw new ParkingLotException("Invalid capacity given for parking lot.");
        }
        this.capacity = capacity;
        this.slots = new HashMap<>();
    }

    public Map<Integer, Slot> getSlots() {
        return slots;
    }

    private Slot getSlot(final Integer slotNumber) {
        if (slotNumber > getCapacity() || slotNumber <= 0) {
            throw new InvalidSlotException();
        }
        final Map<Integer, Slot> allSlots = getSlots();
        if (!allSlots.containsKey(slotNumber)) {
            allSlots.put(slotNumber, new Slot(slotNumber));
        }
        return allSlots.get(slotNumber);
    }


    public Slot park(final Vehicle vehicle, final Integer slotNumber) {
        final Slot slot = getSlot(slotNumber);
        if (!slot.isSlotFree().getState()) {
            throw new SlotAlreadyOccupiedException();
        }
        slot.assignCar(vehicle);
        return slot;
    }

    public Slot makeSlotFree(final Integer slotNumber) {
        final Slot slot = getSlot(slotNumber);
        slot.unassignCar();
        return slot;
    }
}
