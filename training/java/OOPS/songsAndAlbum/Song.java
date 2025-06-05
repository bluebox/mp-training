package songsAndAlbum;

public class Song {

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	private String name;
	private double duration;
	public Song(String name, double duration) {
		this.name = name;
		this.duration = duration;
	}
	
	public String getTitle()
	{
		return this.name+" name "+ this.duration+" duration";
	}

	
	
}
