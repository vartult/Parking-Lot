package com.cellfishpool.queries;

import com.cellfishpool.models.Command;
import com.cellfishpool.services.ParkingLotService;
import com.cellfishpool.utils.baseclass.QueryBaseClass;
import com.cellfishpool.utils.output.OutputPrinter;

public class SlotNumberFromAgeQuery extends QueryBaseClass {
    SlotNumberFromAgeQuery(final ParkingLotService parkingLotService, final OutputPrinter outputPrinter){
        super(parkingLotService, outputPrinter);
    }

    @Override
    public void computeQuery(Command command) {

    }

    @Override
    public boolean checkValidQuery(Command command) {
        return false;
    }
}
