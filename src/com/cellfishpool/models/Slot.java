package com.cellfishpool.models;

import com.cellfishpool.utils.enums.SlotStatus;

public class Slot {
    private Integer slotNumber;
    private SlotStatus slotStatus = SlotStatus.AVAILABLE;
    private Vehicle vehicle = null;

    public Slot(final Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public Vehicle getParkedCar() {
        return vehicle;
    }

    public SlotStatus isSlotFree() {
        return slotStatus;
    }

    public void assignCar(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.slotStatus = SlotStatus.UNAVAILABLE;
    }

    public void unassignCar() {
        this.vehicle = null;
        this.slotStatus = SlotStatus.AVAILABLE;
    }
}
