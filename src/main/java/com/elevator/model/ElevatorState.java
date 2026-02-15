package com.elevator.model;

/**
 * ElevatorState represents the operational condition of the elevator.
 * 
 * This is intentionally separate from Direction.
 * State tells WHAT the elevator is doing.
 */
public enum ElevatorState {
    MOVING,
    IDLE,
    DOOR_OPEN,
    DOOR_CLOSED
}