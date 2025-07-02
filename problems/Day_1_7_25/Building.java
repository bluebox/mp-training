package Day_1_7_25;

public class Building implements Mappable {
    String name;
    Usagetype type;

    public Building(String name, Usagetype type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getLabel() {
        return " " + type.name().toLowerCase() + " ";
    }

    @Override
    public String getMarker() {
        String marker = "";
        switch (type) {
            case BUISINESS:
                marker = "buisiness";
                break;
            case RESIDENTIAL:
                marker = "home";
                break;
            case ENTERTAINMENT:
                marker = "entertainment spot";
                break;
        }
        return marker;
    }

    @Override
    public Geometry getShape() {
        return Geometry.POLYGON;
    }
}
