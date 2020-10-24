package com.cellfishpool.queries;

import com.cellfishpool.exception.NoFreeSlotAvailableException;
import com.cellfishpool.models.Command;
import com.cellfishpool.models.Parking;
import com.cellfishpool.models.Vehicle;
import com.cellfishpool.services.ParkingLotService;
import com.cellfishpool.utils.baseclass.QueryBaseClass;
import com.cellfishpool.utils.output.OutputPrinter;
import com.cellfishpool.utils.strategy.NaturalOrderingParkingStrategy;

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

    @Override
    public boolean checkValidQuery(Command command) {
        return command.getParams().size() == 1;
    }
}
