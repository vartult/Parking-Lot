package com.cellfishpool.queries;

import com.cellfishpool.exception.NoFreeSlotAvailableException;
import com.cellfishpool.models.Command;
import com.cellfishpool.models.Vehicle;
import com.cellfishpool.services.ParkingLotService;
import com.cellfishpool.utils.baseclass.QueryBaseClass;
import com.cellfishpool.utils.constants.SubQuery;
import com.cellfishpool.utils.output.OutputPrinter;
import com.cellfishpool.utils.validator.IntegerValidator;
import com.cellfishpool.utils.validator.VehicleRegistrationNumberValidator;

public class ParkVehicleQuery extends QueryBaseClass {
    public ParkVehicleQuery(final ParkingLotService parkingLotService, final OutputPrinter outputPrinter){
        super(parkingLotService, outputPrinter);
    }

    @Override
    public void computeQuery(Command command) {
        final Vehicle vehicle = new Vehicle(command.getParams().get(2),command.getParams().get(0));
        try {
            final Integer slot = parkingLotService.park(vehicle);
            outputPrinter.printWithNewLine("Car with vehicle registration number \""+command.getParams().get(0)+"\" has been parked at slot number " + slot);
        } catch (NoFreeSlotAvailableException exception) {
            outputPrinter.parkingLotFull();
        }
    }
    /*
     * for this command-
     * 1. 3 param should be present(1 sub param)
     * 2. 1st param for carNumberValidation
     * 3. 2nd param for subParam
     * 4. 3rd param for if isInt?
     * */
    @Override
    public boolean checkValidQuery(Command command) {
        return (command.getParams().size() == 3 &&
                VehicleRegistrationNumberValidator.isValidCarNumber(command.getParams().get(0)) &&
                command.getParams().get(1).toLowerCase().equals(SubQuery.DRIVER_AGE) &&
                IntegerValidator.isInteger(command.getParams().get(2)));
    }
}
