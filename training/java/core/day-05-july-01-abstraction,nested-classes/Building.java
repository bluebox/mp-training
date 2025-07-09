package day5;

enum UsageType {ENTERTAINMENT, GOVERNMENT, RESIDENTIAL, SPORTS}

public class Building implements Mappable {

    private String name;
    private UsageType usage;

    public Building(String name, UsageType usage) {
        this.name = name;
        this.usage = usage;
    }

    @Override
    public String getLabel() {
        return name + " (" + usage + ")";
    }

    @Override
    public Geometry getShape() {
        return Geometry.POINT;
    }

    @Override
    public String getMarker() {
        String marker;
        switch (usage) {
            case ENTERTAINMENT:
                marker = Color.GREEN + " " + PointMarker.TRIANGLE;
                break;
            case GOVERNMENT:
                marker = Color.RED + " " + PointMarker.STAR;
                break;
            case RESIDENTIAL:
                marker = Color.BLUE + " " + PointMarker.SQUARE;
                break;
            case SPORTS:
                marker = Color.ORANGE + " " + PointMarker.PUSH_PIN;
                break;
            default:
                marker = Color.BLACK + " " + PointMarker.CIRCLE;
        }
        return marker;
    }
    @Override
    public String toJSON() {
        return Mappable.super.toJSON()
                + ", \"name\": \"" + name + "\""
                + ", \"usage\": \"" + usage + "\"";
    }

}
