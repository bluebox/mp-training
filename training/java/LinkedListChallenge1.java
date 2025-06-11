import java.Album;
import java.Song;
import java.util.LinkedList;
import java.util.ArrayList;

public class LinkedListChallenge1{
    public static  void main(String[] args){
        ArrayList<Album> albums = new ArrayList<>();

        Album album = new Album("Stormbringer", "Deep Purple");
album.addSong("Stormbringer", 4.6);
album.addSong("Love don't mean a thing", 4.22);
album.addSong("Holy man", 4.3);
album.addSong("Hold on", 5.6);
album.addSong("Lady double dealer", 3.21);
album.addSong("You can't do it right", 6.23);
album.addSong("High ball shooter", 4.27);
album.addSong("The gypsy", 4.2);
album.addSong("Soldier of fortune", 3.13);
albums.add(album);
 
album = new Album("For those about to rock", "AC/DC");
album.addSong("For those about to rock", 5.44);
album.addSong("I put the finger on you", 3.25);
album.addSong("Lets go", 3.45);
album.addSong("Inject the venom", 3.33);
album.addSong("Snowballed", 4.51);
album.addSong("Evil walks", 3.45);
album.addSong("C.O.D.", 5.25);
album.addSong("Breaking the rules", 5.32);
album.addSong("Night of the long knives", 5.12);
albums.add(album);
for(Album albumName:albums){
    for(Song item:album.songs){
    System.out.println(item);
}

    
}
System.out.println("-----------------------------------");
LinkedList<Song> playList = new LinkedList<Song>();
albums.get(0).addToPlayList("You can't do it right", playList);
albums.get(0).addToPlayList("Holy man", playList);
albums.get(0).addToPlayList("Speed king", playList);  
System.out.println(albums.get(0).addToPlayList(9, playList));
System.out.println(albums.get(1).addToPlayList(3, playList));
System.out.println(albums.get(1).addToPlayList(2, playList));
System.out.println(albums.get(1).addToPlayList(24, playList));  


    }
}


