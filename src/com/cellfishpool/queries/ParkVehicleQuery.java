package com.cellfishpool.queries;

import com.cellfishpool.exception.NoFreeSlotAvailableException;
import com.cellfishpool.models.Command;
import com.cellfishpool.models.Vehicle;
import com.cellfishpool.services.ParkingLotService;
import com.cellfishpool.utils.baseclass.QueryBaseClass;
import com.cellfishpool.utils.output.OutputPrinter;

public class ParkVehicleQuery extends QueryBaseClass {
    public ParkVehicleQuery(final ParkingLotService parkingLotService, final OutputPrinter outputPrinter){
        super(parkingLotService, outputPrinter);
    }

    @Override
    public void computeQuery(Command command) {
        final Vehicle vehicle = new Vehicle(command.getParams().get(0), command.getParams().get(2));
        try {
            final Integer slot = parkingLotService.park(vehicle);
            outputPrinter.printWithNewLine(slot.toString());
        } catch (NoFreeSlotAvailableException exception) {
            outputPrinter.parkingLotFull();
        }
    }

    //TODO : 2nd parameter is DRIVER_AGE
    @Override
    public boolean checkValidQuery(Command command) {
        return command.getParams().size() == 3;
    }
}
