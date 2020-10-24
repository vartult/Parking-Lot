package com.cellfishpool.utils.enums;

public enum SlotStatus{
    AVAILABLE(true),
    UNAVAILABLE(false);

    private Boolean state;

    private SlotStatus(Boolean available){
        this.state = available;
    }
    public Boolean getState(){
        return state;
    }
}
