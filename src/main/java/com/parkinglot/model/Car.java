
package com.parkinglot.model;

public class Car extends Vehicle {

    public Car(String vehicleNumber) {
        super(vehicleNumber);
    }

    @Override
    public SpotType getRequiredSpotType() {
        return SpotType.MEDIUM;
    }

    @Override
    public String getVehicleCategory() {
        return "CAR";
    }
}
