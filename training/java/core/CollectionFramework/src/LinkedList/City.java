package LinkedList;

public class City implements Comparable<City>{
	
	private String city;
	private double distance;
	public City(String city, double distance) {
		super();
		this.city = city;
		this.distance = distance;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	@Override
	public int compareTo(City o) {
		
		return (int)(this.distance-o.distance);
		
	}
	

}
