
package com.parkinglot.model;

/**
 * Abstract base class for all vehicles.
 * Each vehicle defines what type of spot it requires.
 */
public abstract class Vehicle {

    private final String vehicleNumber;

    protected Vehicle(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public abstract SpotType getRequiredSpotType();

    public abstract String getVehicleCategory();
}
