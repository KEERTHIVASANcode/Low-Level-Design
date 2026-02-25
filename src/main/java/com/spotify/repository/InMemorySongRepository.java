
package com.spotify.repository;

import com.spotify.model.Song;
import java.util.*;

/**
 * In-memory implementation of SongRepository.
 */
public class InMemorySongRepository implements Repository<Song> {

    private final Map<UUID, Song> storage = new HashMap<>();

    @Override
    public void save(Song song) {
        storage.put(song.getId(), song);
    }

    @Override
    public Optional<Song> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Song> findAll() {
        return new ArrayList<>(storage.values());
    }
}
