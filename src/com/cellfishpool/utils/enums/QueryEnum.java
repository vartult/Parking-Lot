package com.cellfishpool.utils.enums;


public enum  QueryEnum {
    CREATE_PARKING_LOT("create_parking_lot"),
    PARK_VEHICLE("park"),
    SLOT_NUMBER_FROM_AGE_QUERY("slot_numbers_for_driver_of_age"),
    SLOT_NUMBER_FROM_VEHICLE_NUMBER_QUERY("slot_number_for_car_with_number"),
    VACATE_SLOT("leave"),
    VEHICLE_NUMBER_FROM_AGE("vehicle_registration_number_for_driver_of_age");

    private String command;
    public static QueryEnum getQueryCommand(String queryName){
        for (QueryEnum i: QueryEnum.values()) {
            if(i.command.equals(queryName))
                return i;
        }
        return null;
    }
    private QueryEnum(String command){
        this.command = command;
    }
}
