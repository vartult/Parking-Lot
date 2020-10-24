package com.cellfishpool.utils.factories;

import com.cellfishpool.models.Command;
import com.cellfishpool.queries.*;
import com.cellfishpool.services.ParkingLotService;
import com.cellfishpool.utils.baseclass.QueryBaseClass;
import com.cellfishpool.utils.enums.QueryEnum;
import com.cellfishpool.utils.output.OutputPrinter;

import java.util.HashMap;
import java.util.Map;

import static com.cellfishpool.utils.enums.QueryEnum.*;

public class QueryFactory {
    private Map<QueryEnum, QueryBaseClass> commands = new HashMap<>();

    public QueryFactory(final ParkingLotService parkingLotService){
        final OutputPrinter outputPrinter = new OutputPrinter();
        commands.put(CREATE_PARKING_LOT, new CreateParkingLotQuery(parkingLotService,outputPrinter));
        commands.put(PARK_VEHICLE, new ParkVehicleQuery(parkingLotService,outputPrinter));
        commands.put(SLOT_NUMBER_FROM_AGE_QUERY, new SlotNumberFromAgeQuery(parkingLotService,outputPrinter));
        commands.put(SLOT_NUMBER_FROM_VEHICLE_NUMBER_QUERY, new SlotNumberFromVehicleNumberQuery(parkingLotService,outputPrinter));
        commands.put(VACATE_SLOT, new VacateSlotQuery(parkingLotService,outputPrinter));
        commands.put(VEHICLE_NUMBER_FROM_AGE, new VehicleNumberFromAgeQuery(parkingLotService,outputPrinter));
    }

    public QueryBaseClass getCommandExecutor(final Command command) {
        final QueryBaseClass queryBaseClass = commands.get(command.getQueryName());
        if (queryBaseClass == null) {
            throw new RuntimeException();
        }
        return queryBaseClass;
    }
}
