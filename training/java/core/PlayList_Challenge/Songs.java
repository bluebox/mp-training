package PlayList_Challenge;

public class Songs {

	private String title;
	private double duration;
	
	public Songs(String title,double duration) {
		this.title=title;
		this.duration=duration;
	}
	public String getTitle() {
		return title;
	}
	@Override
	public String toString() {
//		System.out.println("Title: "+title);
//		System.out.println("Duration: "+duration);
		return this.title+"-"+this.duration;
	}
}
