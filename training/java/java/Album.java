package java;

import java.util.ArrayList;
import java.util.LinkedList;

public class Album {
    private String artist;
    private String name;
    public ArrayList<Song> songs = new ArrayList<>();

    public Album(String artist, String name) {
        this.artist = artist;
        this.name = name;


    }

    public boolean addSong(String titleOfSong, double duration) {
        Song newSong = new Song(titleOfSong, duration);
        if (songs.contains(newSong)) {
            System.out.println("Song is already present in the Playlist!!!");
            return false;
        }
        for (Song item : songs) {
            if (item.getName().equalsIgnoreCase(newSong.getName())) {
                System.out.println("Song is already present in the Playlist with this name!!!");
                return false;
            }
        }

        if (songs.size() == 0) {
            songs.add(newSong);
            return true;
        }

        songs.add(newSong);
        return true;


    }

    public Song findSong(String titleOfSong) {
        for (var item : songs) {
            if (item.getName().equalsIgnoreCase(titleOfSong)) {
                System.out.println("Song is present in the Playlist with this name!!!");
                return item;
            }
        }
        return null;

    }

    public boolean addToPlayList(int songNumber, LinkedList<Song> playList) {
        try {

            playList.add(songs.get(songNumber + 1));
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    public boolean addToPlayList(String songName, LinkedList<Song> playList) {
        for (var item : songs) {
            if (item.getName().equalsIgnoreCase(songName)) {
                playList.add(item);
                return true;
            }
        }
        return false;
    }
}
