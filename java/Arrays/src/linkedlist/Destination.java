package linkedlist;

public class Destination {
	private String townName;
	private int distance;
	public Destination(String townName, int distance) {
		this.townName=townName;
		this.distance=distance;
	}
	public String getTownName() {
		return townName;
	}
	public int getDistance() {
		return distance;
	}

}
