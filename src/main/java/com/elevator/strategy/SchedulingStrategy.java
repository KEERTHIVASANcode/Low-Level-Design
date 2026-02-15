package com.elevator.strategy;

import com.elevator.Elevator;

/**
 * Strategy Pattern.
 * 
 * Allows different scheduling algorithms (SCAN, LOOK, etc.)
 * without modifying Elevator class (Open-Closed Principle).
 */
public interface SchedulingStrategy {

    void move(Elevator elevator);
}