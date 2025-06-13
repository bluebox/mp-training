package LinkedList;

import java.util.ArrayList;
import java.util.LinkedList;

class Album{
	private String name;
	private String artist;
	private ArrayList<Song>songs;
	public Album(String name,String artist){
		this.artist=artist;
		this.name=name;
		songs=new ArrayList<>();
	}
	public boolean addSong(String title,double duration) {
		Song a=new Song(title,duration);
		if(songs.contains(a))return false;
		songs.add(a);
		return true;
	}
	public Song findSong(String title) {
		for(Song ele:songs) {
			if(ele.getTitle()==title)return ele;
		}
		return null;
	}
	public ArrayList<Song> getSongs(){
		return this.songs;
	}
}
class Song{
	private String title;
	private double duration;
	public Song(String title,double duration) {
		this.title=title;
		this.duration=duration;
	}
	public String getTitle() {
		return this.title;
	}
	@Override
	public String toString() {
		return this.title+" has a duration of "+this.duration;
	}
}
public class PlayListCreater {

	public static void main(String[] args) {
		Album albums=new Album("anand","abhi");
		albums.addSong("sdfjslf", 9.0);
		albums.addSong("sdlfjsdlfjd",8.9);
		ArrayList<Song> ans=albums.getSongs();
		for(Song ele:ans) {
			System.out.println(ele.toString());
		}
	}

}
