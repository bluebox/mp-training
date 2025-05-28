package Playlist;

import java.util.ArrayList;
import java.util.LinkedList;

public class Album {
	String name,artist;
	ArrayList<Song> songs;
	public Album(String name,String artist)
	{
		this.name=name;
		this.artist=artist;
		songs=new ArrayList<Song>();
	}
	public boolean addSong(String titleOfSong,double durationOfSong)
	{
		if(findSong(titleOfSong)==null) {
			songs.add(new Song(titleOfSong,durationOfSong));
			return true;}
		return false;
	}
	public Song findSong(String title)
	{
		for(int i=0;i<songs.size();i++)
		{
			if(title.equals(songs.get(i).title))
					{
						return songs.get(i);
					}
		}
		return null;
	}
	public boolean addToPlaylist(int track,LinkedList<Song> thePlaylist)
	{
		int count=track-1;
		if(count>=0 && count<=songs.size()) {
			thePlaylist.add(songs.get(count));
			return true;}
		return false;
	}
	public boolean addToPlaylist(String title,LinkedList<Song> thePlaylist)
	{
		Song song = findSong(title);
        if(song != null){
            thePlaylist.add(song);
            return true;
        }
        return false;
	}
}
