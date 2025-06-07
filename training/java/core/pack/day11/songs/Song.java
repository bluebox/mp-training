package day11.songs;

public class Song {

	
	String title;
	double duration;
	Song(String title,double duration){
		this.title=title;
		this.duration=duration;
	}
	public String getTitle() {
		return this.title;
	}
	@Override
	public String toString(){
		return this.title+" : "+this.duration;
	}
}
