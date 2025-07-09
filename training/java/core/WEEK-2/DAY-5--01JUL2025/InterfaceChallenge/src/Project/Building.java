package Project;

public class Building implements Mappable {
    private String name;
    private UsageType usage;
    private Color color;
    private PointMarkers marker;

    public Building(String name, UsageType usage) {
        this.name = name;
        this.usage = usage;
        selectMarkerByUsage();
    }

    private void selectMarkerByUsage() {
        switch (usage) {
            case GOVERNMENT -> {
                color = Color.RED;
                marker = PointMarkers.STAR;
            }
            case BUSINESS -> {
                color = Color.BLUE;
                marker = PointMarkers.CIRCLE;
            }
            case ENTERTAINMENT -> {
                color = Color.GREEN;
                marker = PointMarkers.DIAMOND;
            }
            default -> throw new IllegalArgumentException("Unexpected value: " + usage);
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
        return Geometry.POINT;
    }

    @Override
    public String toJSON() {
        return String.format(JSON_PROPERTY,
                String.format("\"type\": \"%s\", \"label\": \"%s\", \"marker\": \"%s\", \"name\": \"%s\", \"usage\": \"%s\"",
                        getShape(), getLabel(), getMarker(), name, usage));
    }
}


