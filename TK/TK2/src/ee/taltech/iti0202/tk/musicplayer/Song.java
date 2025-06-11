package ee.taltech.iti0202.tk.musicplayer;


public class Song {

    String name;
    String artist;
    Integer length;
    public boolean playing;
    int count = 0;

    /**
     * Song constructor.
     */
    public Song(String name, String artist, Integer length) {
        this.name = name;
        this.artist = artist;
        this.length = length;
    }
    /**
     * Get seconds.
     */
    public Integer getDurationInSeconds() {
        return length;
    }

    /**
     * Get minutes.
     */
    public Integer getDurationInMinutes() {
        final int seconds = 60;
        return length / seconds;
    }

    /**
     * Get description.
     */
    public String getDescription() {
        StringBuilder status = new StringBuilder();

        status.append(name).append(" - ").append(artist).append(" (").append(length).append(" sec)");
        return status.toString();
    }

    /**
     * Check length.
     */
    public boolean isLongerThan(Song otherSong) {
        return length > otherSong.length;
    }

}
