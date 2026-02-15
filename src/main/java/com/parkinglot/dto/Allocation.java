
package com.parkinglot.dto;

import com.parkinglot.ParkingFloor;
import com.parkinglot.ParkingSpot;

/**
 * Simple DTO representing floor + spot allocation result.
 */
public class Allocation {

    private final ParkingFloor floor;
    private final ParkingSpot spot;

    public Allocation(ParkingFloor floor, ParkingSpot spot) {
        this.floor = floor;
        this.spot = spot;
    }

    public ParkingFloor getFloor() {
        return floor;
    }

    public ParkingSpot getSpot() {
        return spot;
    }
}
