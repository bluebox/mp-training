package jpachallenges;

import javax.persistence.*;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;
    
    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;
    
    // Constructors, getters, and setters
    public Song() {}
    
    public Song(String title, Artist artist, Album album) {
        this.title = title;
        this.artist = artist;
        this.album = album;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Artist getArtist() { return artist; }
    public Album getAlbum() { return album; }
    public void setTitle(String title) { this.title = title; }
    public void setArtist(Artist artist) { this.artist = artist; }
    public void setAlbum(Album album) { this.album = album; }
}

@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    // Constructors, getters, and setters
    public Artist() {}
    
    public Artist(String name) {
        this.name = name;
    }
    
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    // Constructors, getters, and setters
    public Album() {}
    
    public Album(String name) {
        this.name = name;
    }
    
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

public class Entities {
    
}
