package business;

import java.util.Objects;

/**
 *
 * @author michelle
 */
public class Song {

    private String title;
    private String artist;

    public Song() {
        this.title = "Mmmbop";
        this.artist = "Joe Bloggs";
    }
    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(getArtist(), song.getArtist()) && Objects.equals(getTitle(), song.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArtist(), getTitle());
    }

    @Override
    public String toString() {
        return "Song[" +
                "artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ']';
    }
}
