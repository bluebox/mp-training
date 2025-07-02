
public class UtilityLine implements Mappable {
	 private final String name;
	    private final String utilityType;
	    private final Color color;
	    private final LineMarker lineType;

	    public UtilityLine(String name, String utilityType,
	                       Color color, LineMarker lineType) {
	        this.name = name;
	        this.utilityType = utilityType;
	        this.color = color;
	        this.lineType = lineType;
	    }
	@Override
    public String getLabel() {
        return name + " (" + utilityType + ")";
    }

    @Override
    public Geometry getShape() {
        return Geometry.LINE;
    }

    @Override
    public String getMarker() {
        return switch (lineType) {
            case DASHED -> color.name() + " DASHED";
            case DOTTED -> color.name() + " DOTTED";
            case SOLID -> color.name() + " SOLID";
        };
    }

    @Override
    public String toJSON() {
	    String base = Mappable.super.toJSON().trim();
	    return base.substring(0, base.length() - 1)
	         + String.format(",\"name\":\"%s\",\"utility\":\"%s\"}", name, utilityType);
}
}
