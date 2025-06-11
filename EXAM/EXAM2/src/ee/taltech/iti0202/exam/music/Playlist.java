package ee.taltech.iti0202.exam.music;

import java.util.*;
import java.util.stream.Collectors;

public class Playlist {
    private final List<Song> songs = new ArrayList<>();

    /**
     * Adds a song to the playlist if it is not already present.
     *
     * @param song The song to be added to the playlist.
     * @return {@code true} if the song was successfully added; {@code false} if the song is a duplicate.
     */
    public boolean addSong(Song song) {
        if (!songs.contains(song)) {
            songs.add(song);
            return true;
        }
        return false;
    }

    /**
     * Retrieves a list of all songs in the playlist.
     *
     * @return A list of all songs in the playlist.
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * Calculates the total duration of all songs in the playlist.
     *
     * @return The total duration in seconds.
     */
    public int getTotalDuration() {
        int total = 0;
        for (Song song : songs) {
            total += song.durationInSeconds();
        }
        return total;
    }

    /**
     * Retrieves a list of songs by a specific artist.
     * The search is case-insensitive.
     *
     * @param artist The name of the artist to search for.
     * @return A list of songs by the specified artist.
     */
    public List<Song> getSongsByArtist(String artist) {
        return songs.stream().filter(s -> s.artist().equals(artist)).collect(Collectors.toUnmodifiableList());
    }

    /**
     * Removes a song from the playlist based on its title.
     * The search is case-insensitive.
     *
     * @param title The title of the song to be removed.
     * @return {@code true} if a song with the given title was removed; {@code false} if no song with that title was found.
     */
    public boolean removeSongByTitle(String title) {
        boolean found = false;
        for (Song song : songs) {
            if (song.title().equals(title)) {
                songs.remove(song);
                found = true;
            }
        }
        return found;
    }

    /**
     * Retrieves a list of songs sorted by their duration in ascending order.
     *
     * @return A list of songs sorted by duration, from shortest to longest.
     */
    public List<Song> getSongsSortedByDuration() {
        if (songs.isEmpty()) {
            return List.of();
        }
        songs.sort(Comparator.comparing(Song::durationInSeconds));
        return songs;
    }

    /**
     * Generates a summary of the number of songs by each artist in the playlist.
     *
     * @return A map where the keys are artist names and the values are the count of songs
     *         by that artist in the playlist.
     */
    public Map<String, Integer> getArtistSummary() {
        if (songs.isEmpty()) {
            return Map.of();
        }
        Map<String, Integer> artistSummary = new HashMap<>();
        for (Song song : songs) {
            if (!artistSummary.containsKey(song.artist())) {
                artistSummary.put(song.artist(), 1);
            } else {
                artistSummary.put(song.artist(), artistSummary.get(song.artist()) + 1);
            }
        }
        return artistSummary;
    }

    /**
     * Searches for songs in the playlist that match the given keyword.
     * The search is case-insensitive and checks both the title and artist of each song.
     *
     * @param keyword The keyword to search for in song titles and artists.
     * @return A list of songs that contain the keyword in their title or artist name.
     */
    public List<Song> searchSongs(String keyword) {
        if (songs.isEmpty()) {
            return List.of();
        }
        List<Song> results = new ArrayList<>();
        for (Song song : songs) {
            if (song.title().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(song);
            }
            if (song.artist().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(song);
            }
        }
        return results;

    }

    /**
     * Retrieves a list of the top longest songs in the playlist.
     *
     * @param n the number of longest songs to retrieve. If n is greater than the number of songs
     *          in the playlist, all songs will be returned.
     * @return a list of the top n the longest songs, sorted in descending order by duration.
     */
    public List<Song> getTopLongestSongs(int n) {
        if (songs.isEmpty()) {
            return List.of();
        }
        if (n > songs.size()) {
            return songs;
        }
        songs.sort(Comparator.comparing(Song::durationInSeconds));
        return songs.subList(songs.size() - n, songs.size()).reversed();
    }
}
