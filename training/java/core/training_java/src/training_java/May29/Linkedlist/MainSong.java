package Linkedlist;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainSong {
	public static void main(String[] args) {
		ArrayList<Album> albums=new ArrayList<>();
		Album album=new Album("Stormbringer","DeepTrouble");
		album.addSong("hello", 5.8);
		album.addSong("hi", 9.8);
		albums.add(album);
		Album album2=new Album("Dont care","Wont care");
		album.addSong("Bye", 5.8);
		album.addSong("GoodBye", 9.8);
		albums.add(album2);
		LinkedList<Song> playList=new LinkedList<Song>();
		System.out.println(albums.get(0).addToPlayList("hi", playList));
		System.out.println(albums.get(1).addToPlayList(24, playList));
		
		
		
		
	}

}
