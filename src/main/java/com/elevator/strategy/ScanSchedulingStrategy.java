package com.elevator.strategy;

import com.elevator.model.Direction;
import com.elevator.Elevator;
import com.elevator.model.ElevatorState;

/**
 * Implements SCAN (Elevator Algorithm).
 * 
 * Moves in one direction serving all requests,
 * then reverses direction.
 */
public class ScanSchedulingStrategy implements SchedulingStrategy {

    @Override
    public void move(Elevator elevator) {

        if (elevator.getDirection() == Direction.UP) {

            elevator.moveUpOneFloor();

            if (elevator.hasUpRequests() &&
                elevator.peekUpRequest() == elevator.getCurrentFloor()) {

                elevator.pollUpRequest();
                elevator.openDoor();
            }

            if (!elevator.hasUpRequests()) {

                if (elevator.hasDownRequests()) {
                    elevator.setDirection(Direction.DOWN);
                } else {
                    elevator.setDirection(Direction.IDLE);
                    elevator.setState(ElevatorState.IDLE);
                }
            }
        }

        else if (elevator.getDirection() == Direction.DOWN) {

            elevator.moveDownOneFloor();

            if (elevator.hasDownRequests() &&
                elevator.peekDownRequest() == elevator.getCurrentFloor()) {

                elevator.pollDownRequest();
                elevator.openDoor();
            }

            if (!elevator.hasDownRequests()) {

                if (elevator.hasUpRequests()) {
                    elevator.setDirection(Direction.UP);
                } else {
                    elevator.setDirection(Direction.IDLE);
                    elevator.setState(ElevatorState.IDLE);
                }
            }
        }
    }
}