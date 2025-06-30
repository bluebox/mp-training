package June30;

public class Place {
	String town;
    int distance;

    Place(String town, int distance) {
        this.town = town;
        this.distance = distance;
    }

    void print() {
        System.out.println(town + " (" + distance + " km)");
    }
}
