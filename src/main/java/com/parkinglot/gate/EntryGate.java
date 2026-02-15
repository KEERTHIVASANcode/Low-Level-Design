
package com.parkinglot.gate;

import com.parkinglot.ParkingFloor;
import com.parkinglot.ParkingSpot;
import com.parkinglot.dto.Allocation;
import com.parkinglot.model.*;
import com.parkinglot.ParkingLot;

import java.util.UUID;

public class EntryGate {

    private final ParkingLot parkingLot;

    public EntryGate(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        Allocation allocation = parkingLot.allocateSpot(vehicle);
        if (allocation == null) {
            throw new RuntimeException("No spot available");
        }

        ParkingFloor floor = allocation.getFloor();
        ParkingSpot spot = allocation.getSpot();

        spot.park(vehicle);

        return new Ticket(
                UUID.randomUUID().toString(),
                spot.getSpotId(),
                floor.getFloorId(),
                vehicle.getVehicleNumber(),
                System.currentTimeMillis()
        );
    }
}
