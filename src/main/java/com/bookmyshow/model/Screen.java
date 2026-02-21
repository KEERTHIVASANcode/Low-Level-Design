
package com.bookmyshow.model;

import java.util.List;

/**
 * Screen contains static seat layout.
 */
public class Screen {

    private final String id;
    private final String name;
    private final List<Seat> seats;

    public Screen(String id, String name, List<Seat> seats) {
        this.id = id;
        this.name = name;
        this.seats = seats;
    }

    public List<Seat> getSeats() { return seats; }
}
