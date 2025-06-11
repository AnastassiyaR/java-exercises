package ee.taltech.iti0202.tk.musicplayer;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayer {

    List<Song> playlist = new ArrayList<>();

    /**
     * Add song.
     */
    public void addSong(Song song) {
        if (!(playlist.contains(song))) {
            playlist.add(song);
            song.count = playlist.size() + 1;
        }
    }

    /**
     * Get playlist.
     */
    public List<Song> getPlaylist() {
        return playlist;
    }

    /**
     * Play song.
     */
    public String playSong(int index) {
        if (index < 0 || index > playlist.size() - 1 || playlist.get(index) == null) {
            return "Incorrect index!";
        }
        playlist.get(index).playing = true;
        StringBuilder res =  new StringBuilder();
        res.append("Now playing: ").append(playlist.get(index).getDescription());
        return res.toString();
    }

    /**
     * Stop song.
     */
    public String stop() {
        for (Song song : playlist) {
            if (song.playing) {
                song.playing = false;
                return "Stopped playing: " + song.getDescription();
            }
        }
        return "No song is currently playing.";
    }

    /**
     * Show playlist.
     */
    public String displayPlaylist() {
        if (playlist.isEmpty()) {
            return "The playlist is empty.";
        }
        StringBuilder result = new StringBuilder("Playlist:\n");
        for (int i = 0; i < playlist.size(); i++) {
            Song song = playlist.get(i);
            result.append(i + 1).append(". ").append(song.getDescription()).append("\n");
        }
        return result.toString().trim();
    }

    /**
     * The longest song.
     */
    public Song findLongestSong() {
        if (playlist.size() == 0) {
            return null;
        }

        int maxLength = 0;
        Song longestSong = null;
        for (Song song : getPlaylist()) {
            if (song.length > maxLength) {
                maxLength = song.length;
                longestSong = song;
            }
        }
        return longestSong;
    }
}
