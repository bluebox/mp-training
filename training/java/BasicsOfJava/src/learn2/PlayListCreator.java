package learn2;

import java.util.ArrayList;
import java.util.LinkedList;

public class PlayListCreator {
	
    public static void main(String[] args) {

        ArrayList<Album> albums = new ArrayList<>();

        Album album = new Album("Stormbringer", "Deep Purple");
        album.addSong("Stormbringer", 4.6);
        album.addSong("Love Don't Mean a Thing", 4.2);
        albums.add(album);

        LinkedList<Song> playList = new LinkedList<>();
        album.addToPlayList("Stormbringer", playList);
        album.addToPlayList(2, playList);  // Track number 2

        System.out.println("Playlist:");
        for (Song song : playList) {
            System.out.println(song);
        }
    }
}
