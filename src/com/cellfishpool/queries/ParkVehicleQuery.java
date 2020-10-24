package com.cellfishpool.queries;

import com.cellfishpool.exception.NoFreeSlotAvailableException;
import com.cellfishpool.models.Command;
import com.cellfishpool.models.Vehicle;
import com.cellfishpool.services.ParkingLotService;
import com.cellfishpool.utils.baseclass.QueryBaseClass;
import com.cellfishpool.utils.constants.SubQuery;
import com.cellfishpool.utils.output.OutputPrinter;
import com.cellfishpool.utils.validator.IntegerValidator;

public class ParkVehicleQuery extends QueryBaseClass {
    public ParkVehicleQuery(final ParkingLotService parkingLotService, final OutputPrinter outputPrinter){
        super(parkingLotService, outputPrinter);
    }

    @Override
    public void computeQuery(Command command) {
        final Vehicle vehicle = new Vehicle(command.getParams().get(2),command.getParams().get(0));
        try {
            final Integer slot = parkingLotService.park(vehicle);
            outputPrinter.printWithNewLine(slot.toString());
        } catch (NoFreeSlotAvailableException exception) {
            outputPrinter.parkingLotFull();
        }
    }

    @Override
    public boolean checkValidQuery(Command command) {
        return (command.getParams().size() == 3 && command.getParams().get(1).toLowerCase().equals(SubQuery.DRIVER_AGE) && IntegerValidator.isInteger(command.getParams().get(2)));
    }
}
