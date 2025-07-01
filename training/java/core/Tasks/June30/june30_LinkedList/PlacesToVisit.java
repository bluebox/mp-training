package corejava.june30_LinkedList;

public class PlacesToVisit {
	private String Place;
	private int distanceInKM;
	
	
	public PlacesToVisit(String place, int distanceInKM) {
		Place = place;
		this.distanceInKM = distanceInKM;
	}


	public String getPlace() {
		return Place;
	}


	public void setPlace(String place) {
		Place = place;
	}


	public int getDistanceInKM() {
		return distanceInKM;
	}


	public void setDistanceInKM(int distanceInKM) {
		this.distanceInKM = distanceInKM;
	}
	
	
}
