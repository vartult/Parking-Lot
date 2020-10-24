package com.cellfishpool;

import com.cellfishpool.services.ParkingLotService;
import com.cellfishpool.utils.factories.QueryFactory;
import com.cellfishpool.utils.filesystem.FileMode;
import com.cellfishpool.utils.output.OutputPrinter;

import java.io.IOException;

public class ParkingLotSystem {

    public static void main(String[] args) {
        final OutputPrinter outputPrinter = new OutputPrinter();
        final ParkingLotService parkingLotService = new ParkingLotService();
        final QueryFactory queryFactory =
                new QueryFactory(parkingLotService);

        try {
            new FileMode(queryFactory, outputPrinter,  null).process();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
