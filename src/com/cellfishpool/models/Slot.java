package com.cellfishpool.models;

import com.cellfishpool.utils.enums.SlotStatus;

public class Slot {
    private Integer slotNumber;
    private SlotStatus slotStatus = SlotStatus.AVAILABLE;
    private Vehicle vehicle = null;
}
