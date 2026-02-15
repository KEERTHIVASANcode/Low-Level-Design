
package com.parkinglot;

import com.parkinglot.model.SpotType;
import com.parkinglot.model.Vehicle;

import java.util.*;

/**
 * Represents a single parking floor.
 * Allocation methods are synchronized to ensure thread safety.
 */
public class ParkingFloor {

    private final String floorId;
    private final Map<SpotType, PriorityQueue<ParkingSpot>> availableSpots;
    private final List<SpotType> sortedSpotTypes;

    public ParkingFloor(String floorId, List<ParkingSpot> spots) {
        this.floorId = floorId;
        this.availableSpots = new HashMap<>();

        this.sortedSpotTypes = Arrays.stream(SpotType.values())
                .sorted(Comparator.comparingInt(SpotType::getSizeRank))
                .toList();

        for (SpotType type : SpotType.values()) {
            availableSpots.put(type,
                    new PriorityQueue<>(Comparator.comparingInt(ParkingSpot::getDistanceFromEntry)));
        }

        for (ParkingSpot spot : spots) {
            availableSpots.get(spot.getSpotType()).offer(spot);
        }
    }

    public synchronized ParkingSpot allocateSpot(Vehicle vehicle) {
        SpotType required = vehicle.getRequiredSpotType();

        for (SpotType type : sortedSpotTypes) {
            if (type.getSizeRank() >= required.getSizeRank()) {
                PriorityQueue<ParkingSpot> pq = availableSpots.get(type);
                if (!pq.isEmpty()) {
                    return pq.poll();
                }
            }
        }
        return null;
    }

    public synchronized void freeSpot(ParkingSpot spot) {
        availableSpots.get(spot.getSpotType()).offer(spot);
    }

    public String getFloorId() {
        return floorId;
    }
}
