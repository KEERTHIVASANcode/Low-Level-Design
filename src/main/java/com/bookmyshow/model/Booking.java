
package com.bookmyshow.model;

import com.bookmyshow.enums.BookingStatus;
import java.util.List;

/**
 * Represents booking aggregate root.
 */
public class Booking {

    private final String id;
    private final Show show;
    private final List<String> seatIds;
    private BookingStatus status;

    public Booking(String id, Show show, List<String> seatIds) {
        this.id = id;
        this.show = show;
        this.seatIds = seatIds;
        this.status = BookingStatus.CREATED;
    }

    public void markConfirmed() { this.status = BookingStatus.CONFIRMED; }
    public void markFailed() { this.status = BookingStatus.FAILED; }
}
