import java.util.Comparator;

public class Place {
	String name;
	int distFromSydney;
	
	Place(String name, int dist) {
		this.name = name;
		this.distFromSydney = dist;
	}
	
	public String toString() {
		return "Place: " + name + ", distance from sydney: " + distFromSydney;
	}
}
