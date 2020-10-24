package com.cellfishpool.queries;

import com.cellfishpool.models.Command;
import com.cellfishpool.models.Slot;
import com.cellfishpool.services.ParkingLotService;
import com.cellfishpool.utils.baseclass.QueryBaseClass;
import com.cellfishpool.utils.constants.Constants;
import com.cellfishpool.utils.output.OutputPrinter;
import com.cellfishpool.utils.validator.IntegerValidator;

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
            StringBuilder result = new StringBuilder();
            for (Slot it: vehicleNumberForAge) {
                result.append(it.getParkedCar().getCarNumber());
                result.append(Constants.COMMA);
            }
            if(result.length()>0)
                outputPrinter.printWithNewLine(result.substring(0,result.length()-1));
            else
                outputPrinter.notFound();
        }
    }

    @Override
    public boolean checkValidQuery(Command command) {
        return (command.getParams().size() == 1 && IntegerValidator.isInteger(command.getParams().get(0)));
    }
}
