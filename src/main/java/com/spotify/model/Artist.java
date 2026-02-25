
package com.spotify.model;

import java.util.UUID;

/**
 * Immutable Artist entity.
 * 
 * DESIGN DECISION:
 * - Does NOT maintain list of songs to avoid circular dependency.
 * - Lightweight domain model.
 */
public final class Artist {

    private final UUID id;
    private final String name;
    private final String bio;

    public Artist(UUID id, String name, String bio) {
        if (id == null || name == null) {
            throw new IllegalArgumentException("Invalid artist data");
        }
        this.id = id;
        this.name = name;
        this.bio = bio;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getBio() { return bio; }
}
