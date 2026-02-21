
package com.bookmyshow.service;

import com.bookmyshow.model.Seat;
import com.bookmyshow.model.Show;
import com.bookmyshow.enums.SeatStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Handles per-seat locking logic.
 * Prevents race conditions and double booking.
 */
public class SeatAvailabilityService {

    public boolean lockSeats(Show show, List<String> seatIds) {

        List<Seat> seats = new ArrayList<>();
        for (String id : seatIds) {
            seats.add(show.getSeatById(id));
        }

        seats.sort((a, b) -> a.getId().compareTo(b.getId()));

        List<ReentrantLock> locks = new ArrayList<>();

        try {
            for (Seat seat : seats) {
                ReentrantLock lock = seat.getLock();
                lock.lock();
                locks.add(lock);

                if (show.getSeatStatus(seat.getId()) != SeatStatus.AVAILABLE) {
                    return false;
                }
            }

            for (Seat seat : seats) {
                show.setSeatStatus(seat.getId(), SeatStatus.LOCKED);
            }

            return true;

        } finally {
            if (locks.size() < seats.size()) {
                locks.forEach(ReentrantLock::unlock);
            }
        }
    }

    public void confirmSeats(Show show, List<String> seatIds) {
        for (String id : seatIds) {
            show.setSeatStatus(id, SeatStatus.BOOKED);
            show.getSeatById(id).getLock().unlock();
        }
    }

    public void unlockSeats(Show show, List<String> seatIds) {
        for (String id : seatIds) {
            show.setSeatStatus(id, SeatStatus.AVAILABLE);
            show.getSeatById(id).getLock().unlock();
        }
    }
}
