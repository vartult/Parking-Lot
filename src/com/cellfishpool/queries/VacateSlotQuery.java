package com.cellfishpool.queries;

import com.cellfishpool.models.Command;
import com.cellfishpool.services.ParkingLotService;
import com.cellfishpool.utils.baseclass.QueryBaseClass;
import com.cellfishpool.utils.output.OutputPrinter;

public class VacateSlotQuery extends QueryBaseClass {
    public VacateSlotQuery(final ParkingLotService parkingLotService, final OutputPrinter outputPrinter){
        super(parkingLotService, outputPrinter);
    }

    @Override
    public void computeQuery(Command command) {
        final int slotNum = Integer.parseInt(command.getParams().get(0));
        parkingLotService.makeSlotFree(slotNum);
        outputPrinter.printWithNewLine("Slot number " + slotNum + " is free");
    }

    @Override
    public boolean checkValidQuery(Command command) {
        return command.getParams().size() == 1;
    }
}
