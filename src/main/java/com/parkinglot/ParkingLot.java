
package com.parkinglot;

import com.parkinglot.dto.Allocation;
import com.parkinglot.model.*;

import java.util.*;

public class ParkingLot {

    private final Map<String, ParkingFloor> floors;

    public ParkingLot(List<ParkingFloor> floorList) {
        this.floors = new HashMap<>();
        for (ParkingFloor floor : floorList) {
            floors.put(floor.getFloorId(), floor);
        }
    }

    public Allocation allocateSpot(Vehicle vehicle) {
        for (ParkingFloor floor : floors.values()) {
            ParkingSpot spot = floor.allocateSpot(vehicle);
            if (spot != null) {
                return new Allocation(floor, spot);
            }
        }
        return null;
    }
}
