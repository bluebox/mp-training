package dev.lpa.music;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "albums")
public class Album implements Comparable<Album> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="album_id")
    private int albumId;

    @Column(name="album_name")
    private String albumName;

    @OneToMany
    @JoinColumn(name="album_id")
    private List<Song> playList = new ArrayList<>();

    public Album() {
    }

    public Album(String albumName) {
        this.albumName = albumName;
    }

    public Album(int albumId, String albumName) {
        this.albumId = albumId;
        this.albumName = albumName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public List<Song> getPlayList() {
        return playList;
    }

    @Override
    public String toString() {

        playList.sort(Comparator.comparing(Song::getTrackNumber));
        StringBuilder sb = new StringBuilder();
        for (Song s : playList) {
            sb.append("\n\t").append(s);
        }
        sb.append("\n");
        return "Album{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", songs = " + sb +
                '}';
    }

    @Override
    public int compareTo(Album o) {
        return this.albumName.compareTo(o.getAlbumName());
    }
}
