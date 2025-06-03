package genericClassChallenge;

public class Main {
    public static void main(String[] args) {
        Park grandCanyon = new Park("Grand Canyon National Park", 36.0636, -112.1079);
        River mississippiRiver = new River("Mississippi River", new double[][] {
            {47.2160, -95.2348}, {35.1556, -90.0659}, {29.1566, -89.2495}
        });

        Layer<Mappable> layer = new Layer<>();
        layer.add(grandCanyon);
        layer.add(mississippiRiver);
        layer.renderLayer();
    }
}
