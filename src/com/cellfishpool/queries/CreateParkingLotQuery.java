package com.cellfishpool.queries;

import com.cellfishpool.models.Command;
import com.cellfishpool.models.Parking;
import com.cellfishpool.services.ParkingLotService;
import com.cellfishpool.utils.baseclass.QueryBaseClass;
import com.cellfishpool.utils.output.OutputPrinter;
import com.cellfishpool.utils.strategy.NaturalOrderingParkingStrategy;
import com.cellfishpool.utils.validator.IntegerValidator;

/*
* It is used for the command
* Create_parking_lot
* */
public class CreateParkingLotQuery extends QueryBaseClass {

    public CreateParkingLotQuery(final ParkingLotService parkingLotService, final OutputPrinter outputPrinter){
        super(parkingLotService, outputPrinter);
    }

    @Override
    public void computeQuery(Command command) {
        final int parkingLotCapacity = Integer.parseInt(command.getParams().get(0));
        final Parking parkingLot = new Parking(parkingLotCapacity);
        parkingLotService.createParkingLot(parkingLot, new NaturalOrderingParkingStrategy());
        outputPrinter.printWithNewLine(
                "Created a parking lot with " + parkingLot.getCapacity() + " slots");
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
