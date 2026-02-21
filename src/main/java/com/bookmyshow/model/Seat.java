
package com.bookmyshow.model;

import com.bookmyshow.enums.SeatType;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Seat is static structure data.
 * It also contains a lock to enable per-seat concurrency control.
 */
public class Seat {

    private final String id;
    private final int row;
    private final int col;
    private final SeatType seatType;
    private final ReentrantLock lock = new ReentrantLock();

    public Seat(String id, int row, int col, SeatType seatType) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.seatType = seatType;
    }

    public String getId() { return id; }
    public SeatType getSeatType() { return seatType; }
    public ReentrantLock getLock() { return lock; }
}
