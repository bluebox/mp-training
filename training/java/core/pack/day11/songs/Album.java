package day11.songs;

import java.util.ArrayList;
import java.util.LinkedList;

public class Album {

	
	private String name,artist;
	private ArrayList<Song>songs;
	
	Album(String name,String artist){
		this.name=name;
		this.artist=artist;
		songs=new ArrayList<Song>();
		}
	public boolean addSong(String title,double duration) {
		  if(findSong(title)!=null)
			  return false;
		  this.songs.add(new Song(title,duration));
		  System.out.println(title+" added succesfully");
		  return true;
	}
	private Song findSong(String title) {
		 for(Song s:this.songs) {
			 if(s.getTitle().equals(title))
				 return s;
		 }
		 return null;
	}
	public boolean addToPlayList(int trackNumber,LinkedList<Song>playList) {
		int index=trackNumber-1;
		if(index<0 || index>this.songs.size()-1) {
		System.out.println("*****************there is no such song ******************");
			return false;
		}
		Song addSong=this.songs.get(index);
		if(playList.contains(addSong))
			return false;
		playList.add(addSong);
		return true;
	}
	public boolean addToPlayList(String name,LinkedList<Song>playList) {
		Song addSong=findSong(name);
		if(addSong==null) {
			System.out.println("*****************there is no such song ******************");
			return false;
			
		}
		if(playList.contains(addSong))
			return false;
		playList.add(addSong);
		return true;
		
	}
	public void showSongs() {
		for(Song s:this.songs)
			System.out.println(s);
		System.out.println("----------------");
	}
	
}
