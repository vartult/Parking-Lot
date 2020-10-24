package com.cellfishpool.queries;

import com.cellfishpool.models.Command;
import com.cellfishpool.models.Slot;
import com.cellfishpool.services.ParkingLotService;
import com.cellfishpool.utils.baseclass.QueryBaseClass;
import com.cellfishpool.utils.constants.Constants;
import com.cellfishpool.utils.output.OutputPrinter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SlotNumberFromAgeQuery extends QueryBaseClass {
    public SlotNumberFromAgeQuery(final ParkingLotService parkingLotService, final OutputPrinter outputPrinter){
        super(parkingLotService, outputPrinter);
    }

    @Override
    public void computeQuery(Command command) {
        final List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots();
        final String ageToFind = command.getParams().get(0);
        final List<Slot> foundSlot = occupiedSlots.stream().filter(slot -> slot.getParkedCar().getDriverAge().equals(ageToFind)).collect(Collectors.toList());
        StringBuilder result = new StringBuilder();
        for (Slot i: foundSlot) {
            result.append(i.getSlotNumber());
            result.append(Constants.COMMA);
        }
        outputPrinter.printWithNewLine(result.toString());

    }

    @Override
    public boolean checkValidQuery(Command command) {
        return command.getParams().size() == 1;
    }
}

//SLOT TO FIND 18
