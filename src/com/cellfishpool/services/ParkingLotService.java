package com.cellfishpool.services;

import com.cellfishpool.exception.ParkingLotException;
import com.cellfishpool.models.Parking;
import com.cellfishpool.models.Slot;
import com.cellfishpool.models.Vehicle;
import com.cellfishpool.utils.strategy.ParkingStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingLotService {
    private Parking parking;
    private ParkingStrategy parkingStrategy;

    public void createParkingLot(final Parking parking, final ParkingStrategy parkingStrategy) {
        if (this.parking != null) {
            throw new ParkingLotException("Parking lot already exists.");
        }
        this.parking = parking;
        this.parkingStrategy = parkingStrategy;
        for (int i = 1; i <= this.parking.getCapacity(); i++) {
            parkingStrategy.addSlot(i);
        }
    }

    public Integer park(final Vehicle vehicle) {
        validateParkingLotExists();
        final Integer nextFreeSlot = parkingStrategy.getNextSlot();
        parking.park(vehicle, nextFreeSlot);
        parkingStrategy.removeSlot(nextFreeSlot);
        return nextFreeSlot;
    }

    public void makeSlotFree(final Integer slotNumber) {
        validateParkingLotExists();
        parking.makeSlotFree(slotNumber);
        parkingStrategy.addSlot(slotNumber);
    }

    public List<Slot> getOccupiedSlots() {
        validateParkingLotExists();
        final List<Slot> occupiedSlotsList = new ArrayList<>();
        final Map<Integer, Slot> allSlots = parking.getSlots();

        for (int i = 1; i <= parking.getCapacity(); i++) {
            if (allSlots.containsKey(i)) {
                final Slot slot = allSlots.get(i);
                if (!slot.isSlotFree().getState()) {
                    occupiedSlotsList.add(slot);
                }
            }
        }
        return occupiedSlotsList;
    }

    private void validateParkingLotExists() {
        if (parking == null) {
            throw new ParkingLotException("Parking lot does not exists to park.");
        }
    }

    public List<Slot> getVehicleForAge(final String age) {
        final List<Slot> occupiedSlots = getOccupiedSlots();
        return occupiedSlots.stream()
                .filter(slot -> slot.getParkedCar().getDriverAge().equals(age))
                .collect(Collectors.toList());
    }

}

