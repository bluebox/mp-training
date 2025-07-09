package Project;

public class UtilityLine implements Mappable {
    private String name;
    private UtilityType utility;
    private Color color;
    private LineMarkers marker;

    public UtilityLine(String name, UtilityType utility) {
        this.name = name;
        this.utility = utility;
        selectMarkerByUtility();
    }

    private void selectMarkerByUtility() {
        switch (utility) {
            case ELECTRICAL -> {
                color = Color.BLACK;
                marker = LineMarkers.SOLID;
            }
            case FIBER_OPTIC -> {
                color = Color.GREEN;
                marker = LineMarkers.DOTTED;
            }
            default -> throw new IllegalArgumentException("Unexpected value: " + utility);
        }
    }

    @Override
    public String getLabel() {
        return name;
    }

    @Override
    public String getMarker() {
        return color + " " + marker;
    }

    @Override
    public Geometry getShape() {
        return Geometry.LINE;
    }

    @Override
    public String toJSON() {
        return String.format(JSON_PROPERTY,
                String.format("\"type\": \"%s\", \"label\": \"%s\", \"marker\": \"%s\", \"name\": \"%s\", \"utility\": \"%s\"",
                        getShape(), getLabel(), getMarker(), name, utility));
    }
}


