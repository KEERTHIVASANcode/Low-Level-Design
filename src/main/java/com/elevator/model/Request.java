package com.elevator.model;

/**
 * Represents an external elevator request.
 * 
 * Encapsulates floor number and desired direction.
 * This object is passed to ElevatorSystem (Mediator).
 */
public class Request {

    private final int floorNumber;
    private final Direction direction;

    public Request(int floorNumber, Direction direction) {
        this.floorNumber = floorNumber;
        this.direction = direction;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public Direction getDirection() {
        return direction;
    }
}