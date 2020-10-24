package com.cellfishpool.queries;

import com.cellfishpool.models.Command;
import com.cellfishpool.models.Slot;
import com.cellfishpool.services.ParkingLotService;
import com.cellfishpool.utils.baseclass.QueryBaseClass;
import com.cellfishpool.utils.output.OutputPrinter;
import com.cellfishpool.utils.validator.IntegerValidator;

import java.util.List;
import java.util.Optional;

public class SlotNumberFromVehicleNumberQuery extends QueryBaseClass {
    public SlotNumberFromVehicleNumberQuery(final ParkingLotService parkingLotService, final OutputPrinter outputPrinter){
        super(parkingLotService, outputPrinter);
    }
    @Override
    public void computeQuery(Command command) {
        final List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots();
        final String regNumberToFind = command.getParams().get(0);
        String result = "";
        for (Slot it: occupiedSlots) {
            if(it.getParkedCar().getCarNumber().equals(regNumberToFind)){
                result += it.getSlotNumber();
                break;
            }
        }
        if (!result.equals("")) {
            outputPrinter.printWithNewLine(result);
        } else {
            outputPrinter.notFound();
        }
    }

    @Override
    public boolean checkValidQuery(Command command) {
        return (command.getParams().size() == 1);
    }
}
