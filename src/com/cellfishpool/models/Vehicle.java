package com.cellfishpool.models;

public class Vehicle {
    private String age;
    private String carNumber;

    public Vehicle(final String age, final String carNumber) {
        this.age = age;
        this.carNumber = carNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public String getDriverAge() {
        return age;
    }
}
