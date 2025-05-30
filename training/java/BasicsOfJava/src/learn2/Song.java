package learn2;

public class Song {
	
	private String title;
	private double duration;
	
	public Song(String title,double duration) {
		
		this.duration = duration;
		this.title = title;
	}
	
	public String getTitle() {
		
		return title;
	}
	
	@Override
	public String toString() {
		
		return this.title+": "+this.duration;
	}
}
