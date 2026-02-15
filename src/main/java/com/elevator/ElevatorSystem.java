package com.elevator;

import com.elevator.model.Direction;
import com.elevator.model.ElevatorState;
import com.elevator.model.Request;

import java.util.List;

/**
 * ElevatorSystem acts as a Mediator.
 * 
 * Responsibilities:
 * - Maintain list of elevators
 * - Assign best elevator for external request
 */
public class ElevatorSystem {

    private final List<Elevator> elevators;

    public ElevatorSystem(List<Elevator> elevators) {
        this.elevators = elevators;
    }

    public void handleExternalRequest(Request request) {

        Elevator bestElevator = findBestElevator(request);
        bestElevator.addRequest(request.getFloorNumber());
    }

    private Elevator findBestElevator(Request request) {

        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        // Step 1: Same direction and on the way
        for (Elevator elevator : elevators) {

            if (isSameDirectionAndOnTheWay(elevator, request)) {

                int distance = Math.abs(elevator.getCurrentFloor() - request.getFloorNumber());

                if (distance < minDistance) {
                    minDistance = distance;
                    bestElevator = elevator;
                }
            }
        }

        if (bestElevator != null) return bestElevator;

        // Step 2: Idle elevators
        for (Elevator elevator : elevators) {

            if (elevator.getState() == ElevatorState.IDLE) {

                int distance = Math.abs(elevator.getCurrentFloor() - request.getFloorNumber());

                if (distance < minDistance) {
                    minDistance = distance;
                    bestElevator = elevator;
                }
            }
        }

        if (bestElevator != null) return bestElevator;

        // Step 3: Fallback - closest elevator
        for (Elevator elevator : elevators) {

            int distance = Math.abs(elevator.getCurrentFloor() - request.getFloorNumber());

            if (distance < minDistance) {
                minDistance = distance;
                bestElevator = elevator;
            }
        }

        return bestElevator;
    }

    private boolean isSameDirectionAndOnTheWay(Elevator elevator, Request request) {

        if (elevator.getDirection() != request.getDirection()) {
            return false;
        }

        if (request.getDirection() == Direction.UP) {
            return elevator.getCurrentFloor() <= request.getFloorNumber();
        } else {
            return elevator.getCurrentFloor() >= request.getFloorNumber();
        }
    }
}