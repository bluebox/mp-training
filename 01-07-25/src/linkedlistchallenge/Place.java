package linkedlistchallenge;

public class Place {
    private String name;
    private int distanceFromHyderabad;

    public Place(String name, int distanceFromHyderabad) {
        this.name = name;
        this.distanceFromHyderabad = distanceFromHyderabad;
    }

    public String getName() {
        return name;
    }

    public int getDistanceFromHyderabad() {
        return distanceFromHyderabad;
    }

    @Override
    public String toString() {
        return name + " (" + distanceFromHyderabad + " km)";
    }
}
