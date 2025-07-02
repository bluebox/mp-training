package Day_1_7_25;

public class UtilityLine implements Mappable {
    String name;
    Utilitytype type;

    public UtilityLine(String name, Utilitytype type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getLabel() {
        return "  " + type.name().toLowerCase() + " ";
    }

    @Override
    public String getMarker() {
        String marker = "";
        switch (type) {
            case Electrical:
                marker = "Electric shop";
                break;
            case Fiberoptic:
                marker = "fiberoptic shop";
                break;
            case Copper:
                marker = "copper cable shop";
                break;
        }
        return marker;
    }

    @Override
    public Geometry getShape() {
        return Geometry.LINE;
    }
}

