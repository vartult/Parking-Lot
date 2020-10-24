package com.cellfishpool.queries;

import com.cellfishpool.models.Command;
import com.cellfishpool.models.Parking;
import com.cellfishpool.models.Slot;
import com.cellfishpool.services.ParkingLotService;
import com.cellfishpool.utils.baseclass.QueryBaseClass;
import com.cellfishpool.utils.output.OutputPrinter;
import com.cellfishpool.utils.validator.IntegerValidator;

import java.util.List;

public class VacateSlotQuery extends QueryBaseClass {
    public VacateSlotQuery(final ParkingLotService parkingLotService, final OutputPrinter outputPrinter){
        super(parkingLotService, outputPrinter);
    }

    @Override
    public void computeQuery(Command command) {
        final int slotNum = Integer.parseInt(command.getParams().get(0));
        final List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots();
        Slot targetSlot = null;
        int targetSlotNumber = 0;
        String targetSlotCarNumber = "";
        String targetSlotAge = "";
        for (Slot slot: occupiedSlots) {
            if(slot.getSlotNumber() == slotNum && slot.getParkedCar()!=null){
                targetSlotNumber = slot.getSlotNumber();
                targetSlotCarNumber = slot.getParkedCar().getCarNumber();
                targetSlotAge = slot.getParkedCar().getDriverAge();
                parkingLotService.makeSlotFree(slotNum);
                break;
            }
        }
        if(targetSlotNumber != 0)
        outputPrinter.printWithNewLine("Slot number "+targetSlotNumber+" vacated, the car with vehicle registration number \""+targetSlotCarNumber+ "\" left the space, the driver of the car was of age "+targetSlotAge );
        else outputPrinter.printWithNewLine("Slot number "+slotNum+" already unassigned");
    }

    /*
     * for this command-
     * 1. 1 param should be present
     * 2. That param should be an int
     * */

    @Override
    public boolean checkValidQuery(Command command) {
        return (command.getParams().size() == 1 && IntegerValidator.isInteger(command.getParams().get(0)));
    }
}
