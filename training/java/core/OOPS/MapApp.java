package OOPS;

import java.util.ArrayList;
import java.util.List;

interface Mappable {
    String render();
    static double[] stringToLatLon(String location) {
        String[] parts = location.split(",");
        return new double[]{Double.parseDouble(parts[0]), Double.parseDouble(parts[1])};
    }
}

abstract class Point implements Mappable {
    protected double[] location;

    public Point(String location) {
        this.location = Mappable.stringToLatLon(location);
    }

    protected String location() {
        return location[0] + "," + location[1];
    }
}

abstract class Line implements Mappable {
    protected double[][] locations;

    public Line(String[] locations) {
        this.locations = new double[locations.length][];
        for (int i = 0; i < locations.length; i++) {
            this.locations[i] = Mappable.stringToLatLon(locations[i]);
        }
    }

    protected String location() {
        StringBuilder sb = new StringBuilder();
        for (double[] loc : locations) {
            sb.append(loc[0]).append(",").append(loc[1]).append(" ");
        }
        return sb.toString().trim();
    }
}

class Park extends Point {
    private String name;

    public Park(String name, String location) {
        super(location);
        this.name = name;
    }

    public String render() {
        return "Park: " + name + " at " + location();
    }
}

class River extends Line {
    private String name;

    public River(String name, String[] locations) {
        super(locations);
        this.name = name;
    }

    public String render() {
        return "River: " + name + " at " + location();
    }
}

class Layer<T extends Mappable> {
    private List<T> layerElements = new ArrayList<>();

    public void addElement(T element) {
        layerElements.add(element);
    }

    public void addElements(List<T> elements) {
        layerElements.addAll(elements);
    }

    public void renderLayer() {
        for (T element : layerElements) {
            System.out.println(element.render());
        }
    }
}

public class MapApp {
    public static void main(String[] args) {
        Park park1 = new Park("Yellowstone", "44.6,-110.5");
        Park park2 = new Park("Yosemite", "37.8,-119.5");
        River river1 = new River("Mississippi", new String[]{"47.2,-95.2", "35.1,-90.1", "29.1,-89.2"});

        Layer<Mappable> mapLayer = new Layer<>();
        mapLayer.addElement(park1);
        mapLayer.addElement(park2);
        mapLayer.addElement(river1);

        mapLayer.renderLayer();
    }
}
