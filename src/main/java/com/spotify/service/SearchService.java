
package com.spotify.service;

import com.spotify.model.Song;
import com.spotify.repository.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SearchService handles searching logic.
 * 
 * DESIGN DECISION:
 * - Separated from repository (SRP).
 * - Easily replaceable with ElasticSearch later.
 */
public class SearchService {

    private final Repository<Song> songRepository;

    public SearchService(Repository<Song> songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> searchByName(String keyword) {
        return songRepository.findAll()
                .stream()
                .filter(song -> song.getName().toLowerCase()
                        .contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
