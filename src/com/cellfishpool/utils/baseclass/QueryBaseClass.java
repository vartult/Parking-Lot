package com.cellfishpool.utils.baseclass;

import com.cellfishpool.models.Command;
import com.cellfishpool.services.ParkingLotService;
import com.cellfishpool.utils.output.OutputPrinter;

/**
* Common baseclass for queries
 * Provides ParkingLotService & OutputPrinter
* */
public abstract class QueryBaseClass {
    protected ParkingLotService parkingLotService;
    protected OutputPrinter outputPrinter;

    public QueryBaseClass(ParkingLotService parkingLotService, OutputPrinter outputPrinter){
        this.outputPrinter = outputPrinter;
        this.parkingLotService = parkingLotService;
    }

    /**
    * Compute the result and sends it to outputPrinterService
    * */
    public abstract void computeQuery(Command command);

    /*
    * Check for if the given command is correct or not
    * */
    public abstract boolean checkValidQuery(Command command);
}
