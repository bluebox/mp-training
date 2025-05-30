package samplecodes;

import java.util.*;

public class Album {
    private String name, artist;
    private ArrayList<Song> songs;
    
    public Album(String name, String artist){
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<Song>();
    }
    
    public boolean addSong(String title, double duration){
        if(findSong(title) == null){
            songs.add(new Song(title,duration));
            return true;
        }
        return false;
    }
    
    private Song findSong(String title){
        for(int i=0;i<songs.size();i++){
            Song song = songs.get(i);
            if(song.getTitle().equals(title)){
                return song;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        
        ArrayList<Album> albums = new ArrayList<>();
        
        
        Album album1 = new Album("Greatest Hits", "The Band");
        albums.add(album1);
        
       
        album1.addSong("Song One", 3.5);
        album1.addSong("Song Two", 4.2);
        album1.addSong("Song Three", 3.8);
        
        
        Album album2 = new Album("Classic Tracks", "Another Artist");
        albums.add(album2);
        
        
        album2.addSong("Track A", 4.0);
        album2.addSong("Track B", 3.2);
        
        
        LinkedList<Song> playlist = new LinkedList<>();
        
       
        album1.addToPlayList(1, playlist);          
        album1.addToPlayList("Song Two", playlist); 
        album2.addToPlayList(1, playlist);         
        album2.addToPlayList("Track B", playlist);  
        
       
        System.out.println("Playlist contents:");
        for (Song song : playlist) {
            System.out.println(song.toString());
        }
        
       
        boolean added = album1.addToPlayList("Nonexistent Song", playlist);
        System.out.println("\nAdding non-existent song: " + (added ? "Success" : "Failed"));
        
        added = album1.addToPlayList(10, playlist);
        System.out.println("Adding invalid track number: " + (added ? "Success" : "Failed"));
    }
    
    public boolean addToPlayList(int track,LinkedList<Song> playlist){
        int count = track - 1;
        if (count >= 0 && count <= songs.size()) {
            playlist.add(songs.get(count));
            return true;
        }
        return false;
    }
    
    public boolean addToPlayList(String title,LinkedList<Song> playlist){
        Song song = findSong(title);
        if(song != null){
            playlist.add(song);
            return true;
        }
        return false;
    }
}
class Song {
    private String title;
    private double duration;
    
    public Song(String title, double duration){
        this.title = title;
        this.duration = duration;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String toString(){
        return (""+title+": "+duration);
    } 
}
