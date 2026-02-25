
package com.spotify.model;

import java.util.*;

/**
 * User entity.
 * 
 * DESIGN DECISION:
 * - Stores only IDs for playlists, liked songs, followed artists.
 * - Avoids heavy object graph & circular dependencies.
 */
public class User {

    private final UUID id;
    private String name;

    private final Set<UUID> playlistIds = new HashSet<>();
    private final Set<UUID> likedSongIds = new HashSet<>();
    private final Set<UUID> followedArtistIds = new HashSet<>();

    public User(UUID id, String name) {
        if (id == null || name == null) {
            throw new IllegalArgumentException("Invalid user data");
        }
        this.id = id;
        this.name = name;
    }

    public void addPlaylist(UUID playlistId) {
        playlistIds.add(playlistId);
    }

    public void likeSong(UUID songId) {
        likedSongIds.add(songId);
    }

    public void followArtist(UUID artistId) {
        followedArtistIds.add(artistId);
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
}
