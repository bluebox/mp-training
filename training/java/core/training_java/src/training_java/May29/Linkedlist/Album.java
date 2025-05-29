package Linkedlist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class Album {
	private String name;
	private String artist;
	private ArrayList<Song> songs=new ArrayList<Song>();;
	public Album(String name,String artist) {
		this.name=name;
		this.artist=artist;
	}
	
	public boolean addSong(String title,double duration) {
		Song song=new Song(title,duration);
		if(findSong(song.getTitle())!=null) {
			return false;
		}
		songs.add(song);
		return true;
	}
	
	private Song findSong(String title) {
		for(Song currentSong:songs) {
			if(currentSong.getTitle().equals(title)) {
				return currentSong;
			}
		}
		return null;	
	}
	public boolean addToPlayList(int track,LinkedList<Song> playlist) {
		int num=songs.size();
		if(num>=track) {
			playlist.add(songs.get(track-1));
			return true;
		}
		return false;
		
		
	}
public boolean addToPlayList(String title,LinkedList<Song> playlist) {
	Song addSong=findSong(title);
	if(addSong!=null) {
		playlist.add(addSong);
		return true;
	}
	return false;		
	}
	
	
	

}
