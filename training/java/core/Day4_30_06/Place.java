package Day4_30_06;

public class Place {
	public String name;
	public int distance;
	public Place(String name, int distance) {
		this.name = name;
		this.distance = distance;
	}
	 @Override
	    public String toString() {
	        return String.format("%s (%d)", name, distance);
	    }

}
