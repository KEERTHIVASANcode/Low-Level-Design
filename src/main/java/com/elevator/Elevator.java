package com.elevator;

import com.elevator.model.Direction;
import com.elevator.model.ElevatorState;
import com.elevator.strategy.SchedulingStrategy;

import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * Elevator represents a single elevator unit.
 * 
 * Responsibilities:
 * - Maintain state (floor, direction, state)
 * - Maintain request queues
 * - Delegate movement logic to SchedulingStrategy
 * 
 * Thread Safety:
 * Critical mutation methods are synchronized.
 */
public class Elevator {

    private final int id;
    private int currentFloor;
    private Direction direction;
    private ElevatorState state;

    private final PriorityQueue<Integer> upRequests;
    private final PriorityQueue<Integer> downRequests;

    private final SchedulingStrategy schedulingStrategy;

    public Elevator(int id, int currentFloor, SchedulingStrategy strategy) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.direction = Direction.IDLE;
        this.state = ElevatorState.IDLE;
        this.schedulingStrategy = strategy;

        this.upRequests = new PriorityQueue<>();
        this.downRequests = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public synchronized void addRequest(int requestedFloor) {

        if (requestedFloor == currentFloor) {
            openDoor();
            return;
        }

        if (requestedFloor > currentFloor) {
            upRequests.offer(requestedFloor);
        } else {
            downRequests.offer(requestedFloor);
        }

        if (state == ElevatorState.IDLE) {
            decideDirection();
        }
    }

    public synchronized void move() {
        schedulingStrategy.move(this);
    }

    private void decideDirection() {
        if (!upRequests.isEmpty()) {
            direction = Direction.UP;
            state = ElevatorState.MOVING;
        } else if (!downRequests.isEmpty()) {
            direction = Direction.DOWN;
            state = ElevatorState.MOVING;
        }
    }

    // Controlled state manipulation methods (Encapsulation)

    public void moveUpOneFloor() {
        currentFloor++;
    }

    public void moveDownOneFloor() {
        currentFloor--;
    }

    public void openDoor() {
        state = ElevatorState.DOOR_OPEN;
        state = ElevatorState.MOVING;
    }

    public boolean hasUpRequests() { return !upRequests.isEmpty(); }
    public boolean hasDownRequests() { return !downRequests.isEmpty(); }

    public int peekUpRequest() { return upRequests.peek(); }
    public int peekDownRequest() { return downRequests.peek(); }

    public void pollUpRequest() { upRequests.poll(); }
    public void pollDownRequest() { downRequests.poll(); }

    public int getCurrentFloor() { return currentFloor; }
    public Direction getDirection() { return direction; }
    public ElevatorState getState() { return state; }

    public void setDirection(Direction direction) { this.direction = direction; }
    public void setState(ElevatorState state) { this.state = state; }
}