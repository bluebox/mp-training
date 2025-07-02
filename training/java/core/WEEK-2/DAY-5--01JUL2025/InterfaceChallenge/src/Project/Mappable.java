package Project;

public interface Mappable {
	public enum Geometry {
	    LINE,
	    POINT,
	    POLYGON
	}
    String JSON_PROPERTY = "\"properties\": {%s}";

    static void mapIt(Mappable mappable) {
        System.out.println(mappable.toJSON());
    }

    default String toJSON() {
        return String.format(JSON_PROPERTY,
                "\"label\": \"" + getLabel() + "\", " +
                "\"marker\": \"" + getMarker() + "\", " +
                "\"shape\": \"" + getShape() + "\"");
    }

    String getLabel();
    String getMarker();
    Geometry getShape();
}

