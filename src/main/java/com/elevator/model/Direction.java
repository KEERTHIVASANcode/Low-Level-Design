package com.elevator.model;

/**
 * Direction represents the movement orientation of the elevator.
 * 
 * We keep this separate from ElevatorState to follow SRP (Single Responsibility Principle).
 * Direction tells WHERE the elevator is moving.
 */
public enum Direction {
    UP,
    DOWN,
    IDLE
}