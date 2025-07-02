package day5;

enum UtilityType {ELECTRICAL, FIBER_OPTIC, GAS, WATER}

public class UtilityLine implements Mappable {

    private String name;
    private UtilityType type;

    public UtilityLine(String name, UtilityType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getLabel() {
        return name + " (" + type + ")";
    }

    @Override
    public Geometry getShape() {
        return Geometry.LINE;
    }

    @Override
    public String getMarker() {
        String marker;
        switch (type) {
            case ELECTRICAL:
                marker = Color.RED + " " + LineMarker.DASHED;
                break;
            case FIBER_OPTIC:
                marker = Color.GREEN + " " + LineMarker.DOTTED;
                break;
            case GAS:
                marker = Color.ORANGE + " " + LineMarker.SOLID;
                break;
            case WATER:
                marker = Color.BLUE + " " + LineMarker.SOLID;
                break;
            default:
                marker = Color.BLACK + " " + LineMarker.SOLID;
        }
        return marker;
    }

    @Override
    public String toJSON() {
        return Mappable.super.toJSON()
                + ", \"name\": \"" + name + "\""
                + ", \"utility\": \"" + type + "\"";
    }

}
