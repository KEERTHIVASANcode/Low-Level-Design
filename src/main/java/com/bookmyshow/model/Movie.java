
package com.bookmyshow.model;

/**
 * Represents a movie entity.
 */
public class Movie {

    private final String id;
    private final String name;
    private final int durationMinutes;

    public Movie(String id, String name, int durationMinutes) {
        this.id = id;
        this.name = name;
        this.durationMinutes = durationMinutes;
    }

    public String getId() { return id; }
    public String getName() { return name; }
}
