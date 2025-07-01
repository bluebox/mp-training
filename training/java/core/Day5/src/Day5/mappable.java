package Day5;

public interface mappable {
	String JSON_PROPERTY="Properties :";
	String getLabel();
	String getMarker();
	Geometry getShape();
	static void mapIt(mappable map) {
		System.out.println(map.toJSON()); 
	}
	default String toJSON() {
		return """
				 "type": "%s", "label": "%s", "marker": "%s"
            """.formatted(getShape(), getLabel(), getMarker());
				
	}
	
}
