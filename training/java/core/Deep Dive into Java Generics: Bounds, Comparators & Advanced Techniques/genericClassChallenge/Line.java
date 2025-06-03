package genericClassChallenge;

public class Line implements Mappable {
    private String name;
    private double[][] coordinates;

    public Line(String name, double[][] coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }
    public void render() {
        System.out.print("Render " + name + " as LINE ([");
        for (int i = 0; i < coordinates.length; i++) {
            System.out.printf("%.4f, %.4f", coordinates[i][0], coordinates[i][1]);
            if (i < coordinates.length - 1) {
                System.out.print("], [");
            }
        }
        System.out.println("])");
    }
}
