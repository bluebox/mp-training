package PlayList_Challenge;

import java.util.ArrayList;
import java.util.LinkedList;

public class Album {

	String albumName;
	String artistname;
	ArrayList<Songs> songs =new ArrayList<Songs>();
	
	public Album(String albumName,String artistName) {
		this.albumName=albumName;
		this.artistname=artistName;
	}
	
	public boolean addSong(String title,double duration) {
		Songs song=new Songs(title,duration);
		if(findSong(song.getTitle())==null) {
//			Songs newSong=new Songs(title,duration);
			songs.add(song);
			System.out.println(title+"song added.");
			return true;
		}
				
		return false;
	}
	public boolean addToPlayList(int track,LinkedList<Songs> playlist) {
		int index=track-1;
		if(index>=0 && index<=songs.size()) {
			playlist.add(songs.get(index));
			return true;
		}
		return false;
		
	}
	public boolean addToPlayList(String title,LinkedList<Songs> playlist) {
		Songs song=findSong(title);
		if(song!=null) {
			playlist.add(song);
			return true;
		}
		return false;
	}
	public Songs findSong(String name) {
		
		for(Songs song:songs) {
			song.getTitle().equals(name);
			return song;
		}
		return null;
	}
}
