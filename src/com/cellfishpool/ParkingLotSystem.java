package com.cellfishpool;

import com.cellfishpool.services.ParkingLotService;
import com.cellfishpool.utils.factories.QueryFactory;
import com.cellfishpool.utils.filesystem.FileUtil;
import com.cellfishpool.utils.output.OutputPrinter;

import java.io.IOException;
/**
* Anchor for the parking system
 * Files is stored in root/input folder
* */
public class ParkingLotSystem {

    public static void main(String[] args) {
        final OutputPrinter outputPrinter = new OutputPrinter();
        final ParkingLotService parkingLotService = new ParkingLotService();

        final QueryFactory queryFactory = new QueryFactory(parkingLotService);
        /*
        * FileUtils initilisation
        * */
        try {
            new FileUtil(queryFactory, outputPrinter,  null).process();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
