package PlayList_Challenge;

import java.util.ArrayList;
import java.util.LinkedList;
public class Main {
	public static void main(String[] args) {
		System.out.println("-----");
		System.out.println("-----");
		ArrayList<Album> albums=new ArrayList<>();
		LinkedList<Songs> playlist=new LinkedList<Songs>();

		Album album=new Album("Stormbringer","Deep blue");
		album.addSong("stroming", 2.42);
		album.addSong("Thunder", 3.42);
		album.addSong("Rain", 3.42);
		album.addSong("Hails", 6.42);
		
		albums.add(album);
		
		Album album2=new Album("For thoise about rock","Academy");
		album2.addSong("Falling", 2.42);
		album2.addSong("The rocks", 3.42);
		album2.addSong("Rolling", 3.42);
		album2.addSong("Weights", 6.42);
		albums.add(album2);
		
		System.out.println(albums.get(0).addToPlayList("adding", playlist));
		System.out.println(albums.get(0).addToPlayList("adding2", playlist));

		System.out.println(albums.get(1).addToPlayList("addding3", playlist));
		System.out.println(albums.get(1).addToPlayList("addding4", playlist));


		
	}

}

