package ArrayList;


import java.util.ArrayList;

public class Album {
	private String name;
	private String artist;
	private ArrayList<Song> songs;
	public Album(String name, String artist) {
		super();
		this.name = name;
		this.artist = artist;
		this.songs = new ArrayList<>();
	}
	
	public void addsongs(String songTitle, double duration)
	{
		Song newSong = new Song(songTitle, duration);
		songs.add(newSong);
	
	}
	public String toString() {
		return this.name+": "+this.artist+": "+this.songs;
	}
	
	
}