public class Place implements Comparable<Place> {
    private String name;
    private int distance;

    public Place(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    public String getName() { return name; }
    public int getDistance() { return distance; }

    @Override
    public int compareTo(Place other) {
        return Integer.compare(this.distance, other.distance);
    }

    @Override
    public String toString() {
        return name + " (" + distance + " km)";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Place other) {
            return this.name.equalsIgnoreCase(other.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }
}