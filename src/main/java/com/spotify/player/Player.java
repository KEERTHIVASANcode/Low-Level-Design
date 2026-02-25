
package com.spotify.player;

import com.spotify.model.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Player handles playback logic.
 * 
 * DESIGN DECISION:
 * - Maintains List<Song> + index pointer.
 * - Supports next & previous navigation.
 * - Not singleton (each user has independent player).
 */
public class Player {

    private List<Song> queue = new ArrayList<>();
    private int currentIndex = -1;
    private boolean isPlaying = false;

    public void loadPlaylist(List<Song> songs) {
        this.queue = new ArrayList<>(songs);
        this.currentIndex = songs.isEmpty() ? -1 : 0;
        this.isPlaying = false;
    }

    public void play() {
        if (currentIndex >= 0 && currentIndex < queue.size()) {
            isPlaying = true;
            System.out.println("Playing: " + getCurrentSong().getName());
        }
    }

    public void pause() {
        isPlaying = false;
        System.out.println("Paused");
    }

    public void next() {
        if (currentIndex + 1 < queue.size()) {
            currentIndex++;
            play();
        }
    }

    public void previous() {
        if (currentIndex - 1 >= 0) {
            currentIndex--;
            play();
        }
    }

    public Song getCurrentSong() {
        if (currentIndex >= 0 && currentIndex < queue.size()) {
            return queue.get(currentIndex);
        }
        return null;
    }
}
