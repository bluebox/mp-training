package learn2;

import java.util.ArrayList;
import java.util.LinkedList;

public class PlayListCreator {
	
    public static void main(String[] args) {

        ArrayList<Album> albums = new ArrayList<>();

        Album album = new Album("Stormbringer", "Deep Purple");
        album.addSong("Stormbringer", 4.6);
        album.addSong("Love Don't Mean a Thing", 4.2);
        album.addSong("Hold on", 5.6);
        album.addSong("High ball shooter ", 4.27);
        album.addSong("The gypsy", 4.2);
        albums.add(album);

        
        album = new Album("For those about to rock","AC/DC");
        album.addSong("For those about to rock", 5.44);
        album.addSong("Let's go", 3.45);
        album.addSong("C.O.D.", 5.25);
        album.addSong("Breaking the rules", 5.32);
        
        
        LinkedList<Song> playList = new LinkedList<>();
        
        albums.get(0).addToPlayList("Love Don't Mean a Thing", playList);
        albums.get(0).addToPlayList("Holy man", playList);
        albums.get(1).addToPlayList(2, playList);
        albums.get(1).addToPlayList(10, playList);
    }
}
