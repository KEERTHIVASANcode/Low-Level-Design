
package com.parkinglot;

import com.parkinglot.model.SpotType;
import com.parkinglot.model.Vehicle;

/**
 * Represents a single parking spot.
 * Thread safety is ensured at floor level during allocation.
 */
public class ParkingSpot {

    private final String spotId;
    private final SpotType spotType;
    private final int distanceFromEntry;
    private Vehicle parkedVehicle;

    public ParkingSpot(String spotId, SpotType spotType, int distanceFromEntry) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.distanceFromEntry = distanceFromEntry;
    }

    public boolean isOccupied() {
        return parkedVehicle != null;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public int getDistanceFromEntry() {
        return distanceFromEntry;
    }

    public void park(Vehicle vehicle) {
        if (isOccupied()) {
            throw new RuntimeException("Spot already occupied");
        }
        this.parkedVehicle = vehicle;
    }

    public void unpark() {
        this.parkedVehicle = null;
    }

    public String getSpotId() {
        return spotId;
    }
}
