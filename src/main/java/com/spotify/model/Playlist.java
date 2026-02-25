
package com.spotify.model;

import java.util.*;

/**
 * Playlist entity (Mutable).
 * 
 * DESIGN DECISION:
 * - Uses LinkedHashMap to maintain insertion order.
 * - Prevents duplicate songs using songId as key.
 * - Synchronized methods for basic thread-safety.
 */
public class Playlist {

    private final UUID id;
    private String name;
    private final UUID ownerId;

    private final Map<UUID, Song> songs = new LinkedHashMap<>();
    private final Set<UUID> collaborators = new HashSet<>();

    public Playlist(UUID id, String name, UUID ownerId) {
        if (id == null || name == null || ownerId == null) {
            throw new IllegalArgumentException("Invalid playlist data");
        }
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
    }

    public synchronized void addSong(Song song) {
        songs.put(song.getId(), song);
    }

    public synchronized void removeSong(UUID songId) {
        songs.remove(songId);
    }

    public synchronized List<Song> getSongs() {
        return new ArrayList<>(songs.values());
    }

    public void addCollaborator(UUID userId) {
        collaborators.add(userId);
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public UUID getOwnerId() { return ownerId; }
}
