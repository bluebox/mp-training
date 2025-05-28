package Playlist;

public class Song {
 String title;
 double duration;
public Song(String title, double duration) {
	super();
	this.title = title;
	this.duration = duration;
}
public String getTitle() {
	return title;
}
public String toString()
{
	return (""+title+": "+duration);
}
 
}
