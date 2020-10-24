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

/**
 * Service for enable the functioning of a parking lot. This will have all the business logic of
 * how the parking service will operate.
 */
public class ParkingLotService {
    private Parking parkingLot;
    private ParkingStrategy parkingStrategy;

    public void createParkingLot(final Parking parking, final ParkingStrategy parkingStrategy) {
        if (this.parkingLot != null) {
            throw new ParkingLotException("Parking lot already exists.");
        }
        this.parkingLot = parking;
        this.parkingStrategy = parkingStrategy;
        for (int i = 1; i <= parkingLot.getCapacity(); i++) {
            parkingStrategy.addSlot(i);
        }
    }

    public Integer park(final Vehicle vehicle) {
        validateParkingLotExists();
        final Integer nextFreeSlot = parkingStrategy.getNextSlot();
        parkingLot.park(vehicle, nextFreeSlot);
        parkingStrategy.removeSlot(nextFreeSlot);
        return nextFreeSlot;
    }

    /**
     * Unparks a car from a slot. Freed slot number is given back to the parking strategy so that it
     * becomes available for future parkings.
     *
     * @param slotNumber Slot number to be freed.
     */
    public void makeSlotFree(final Integer slotNumber) {
        validateParkingLotExists();
        parkingLot.makeSlotFree(slotNumber);
        parkingStrategy.addSlot(slotNumber);
    }

    /**
     * Gets the list of all the slots which are occupied.
     */
    public List<Slot> getOccupiedSlots() {
        validateParkingLotExists();
        final List<Slot> occupiedSlotsList = new ArrayList<>();
        final Map<Integer, Slot> allSlots = parkingLot.getSlots();

        for (int i = 1; i <= parkingLot.getCapacity(); i++) {
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
        if (parkingLot == null) {
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

