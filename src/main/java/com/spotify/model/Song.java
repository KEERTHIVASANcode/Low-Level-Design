
package com.spotify.model;

import java.util.List;
import java.util.UUID;

/**
 * Immutable Song entity.
 * 
 * DESIGN DECISION:
 * - Marked final to prevent inheritance.
 * - All fields are final to ensure immutability.
 * - Thread-safe by design.
 */
public final class Song {

    private final UUID id;
    private final String name;
    private final int durationInSeconds;
    private final List<Artist> artists;
    private final String albumName;
    private final String genre;

    public Song(UUID id, String name, int durationInSeconds,
                List<Artist> artists, String albumName, String genre) {

        if (id == null || name == null || artists == null) {
            throw new IllegalArgumentException("Invalid song data");
        }

        this.id = id;
        this.name = name;
        this.durationInSeconds = durationInSeconds;
        this.artists = List.copyOf(artists); // Defensive copy
        this.albumName = albumName;
        this.genre = genre;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public int getDurationInSeconds() { return durationInSeconds; }
    public List<Artist> getArtists() { return List.copyOf(artists); }
    public String getAlbumName() { return albumName; }
    public String getGenre() { return genre; }
}
