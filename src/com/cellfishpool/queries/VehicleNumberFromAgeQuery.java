package com.cellfishpool.queries;

import com.cellfishpool.models.Command;
import com.cellfishpool.models.Slot;
import com.cellfishpool.services.ParkingLotService;
import com.cellfishpool.utils.baseclass.QueryBaseClass;
import com.cellfishpool.utils.output.OutputPrinter;

import java.util.List;
import java.util.stream.Collectors;

public class VehicleNumberFromAgeQuery extends QueryBaseClass {
    public VehicleNumberFromAgeQuery(final ParkingLotService parkingLotService, final OutputPrinter outputPrinter){
        super(parkingLotService, outputPrinter);
    }

    @Override
    public void computeQuery(Command command) {
        final List<Slot> vehicleNumberForAge = parkingLotService.getVehicleForAge(command.getParams().get(0));
        if (vehicleNumberForAge.isEmpty()) {
            outputPrinter.notFound();
        } else {
            final String result =
                    vehicleNumberForAge.stream()
                            .map(slot -> slot.getParkedCar().getCarNumber())
                            .collect(Collectors.joining(", "));
            outputPrinter.printWithNewLine(result);
        }
    }

    @Override
    public boolean checkValidQuery(Command command) {
        return command.getParams().size() == 1;
    }
}
