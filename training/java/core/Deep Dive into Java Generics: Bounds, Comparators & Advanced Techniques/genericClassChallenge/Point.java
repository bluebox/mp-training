package genericClassChallenge;

public class Point implements Mappable {
    private String name;
    private double latitude,longitude;

    public Point(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void render() {
        System.out.printf("Render %s as POINT (%.4f, %.4f)%n", name, latitude, longitude);
    }
}
