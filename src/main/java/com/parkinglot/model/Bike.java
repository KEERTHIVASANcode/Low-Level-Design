
package com.parkinglot.model;

public class Bike extends Vehicle {

    public Bike(String vehicleNumber) {
        super(vehicleNumber);
    }

    @Override
    public SpotType getRequiredSpotType() {
        return SpotType.SMALL;
    }

    @Override
    public String getVehicleCategory() {
        return "BIKE";
    }
}
