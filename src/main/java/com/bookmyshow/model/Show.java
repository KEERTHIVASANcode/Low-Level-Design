
package com.bookmyshow.model;

import com.bookmyshow.enums.SeatStatus;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Show represents a movie running on a screen at specific time.
 * Seat availability is maintained per show.
 */
public class Show {

    private final String id;
    private final Movie movie;
    private final Screen screen;

    // seatId -> status
    private final Map<String, SeatStatus> seatStatusMap = new ConcurrentHashMap<>();

    public Show(String id, Movie movie, Screen screen) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;

        screen.getSeats().forEach(seat ->
                seatStatusMap.put(seat.getId(), SeatStatus.AVAILABLE)
        );
    }

    public SeatStatus getSeatStatus(String seatId) {
        return seatStatusMap.get(seatId);
    }

    public void setSeatStatus(String seatId, SeatStatus status) {
        seatStatusMap.put(seatId, status);
    }

    public Seat getSeatById(String seatId) {
        return screen.getSeats().stream()
                .filter(s -> s.getId().equals(seatId))
                .findFirst()
                .orElseThrow();
    }
}
