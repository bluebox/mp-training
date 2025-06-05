package songsAndAlbum;

import java.util.*;

public class Album {

	private String name;
	private String artist;
	List<Song> songs;
	public Album(String name, String artist) {
		this.name = name;
		this.artist = artist;
		this.songs= new ArrayList<>();
	}
	
	public boolean addSong(String name,double duration)
	{
		return songs.add(new Song(name,duration));
	}
	
	public Song findSong(String name)
	{
		for(Song song:songs)
		{
			if(song.getTitle()==name)
			{
				return song;
			}
		}
		return null;
	}
	
	public boolean addToPlaylist(int trackNum,LinkedList<Song> playlist)
	{
		
		
		
		return false;
	}
	public boolean addToPlaylist(String name,LinkedList<Song> playlist)
	{
		
		
		return false;
	}
	
}
