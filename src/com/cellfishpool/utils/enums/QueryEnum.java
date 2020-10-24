package com.cellfishpool.utils.enums;

public enum  QueryEnum {
    CREATE_PARKING_LOT("rreate_parking_lot"),
    PARK_VEHICLE("park"),
    SLOT_NUMBER_FROM_AGE_QUERY("slot_numbers_for_driver_of_age"),
    SLOT_NUMBER_FROM_VEHICLE_NUMBER_QUERY("slot_number_for_car_with_number"),
    VACATE_SLOT("leave"),
    VEHICLE_NUMBER_FROM_AGE("vehicle_registration_number_for_driver_of_age");

    private String command;
    public String getQueryCommand(){
        return this.command;
    }
    private QueryEnum(String command){
        this.command = command;
    }
}
