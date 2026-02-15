
package com.parkinglot.model;

public class Truck extends Vehicle {

    public Truck(String vehicleNumber) {
        super(vehicleNumber);
    }

    @Override
    public SpotType getRequiredSpotType() {
        return SpotType.LARGE;
    }

    @Override
    public String getVehicleCategory() {
        return "TRUCK";
    }
}
