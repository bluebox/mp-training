package day4;

public class Places{
	private String place;
	private int distance;
	public Places(String place, int distance) {
		this.place=place;
		this.distance=distance;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s : (%d)",getPlace(),getDistance());
	}
	
	
	
}