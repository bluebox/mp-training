package interfaceChallenge;

public interface Mappable {
	
	String JSON_PROPERTY = """
			properties: {%s} """;
	
	static void mapIt(Mappable mappable) {
		System.out.println(mappable.toJSON());
	}
	
	default String toJSON() {
		return String.format(JSON_PROPERTY,getProperties());
	}
	
	private String getProperties() {
		return String.format("""
				"type": "%s", "label": "%s", "marker": "%s"
										""",getShape(),getLabel(),getMarker());
	}
	String getLabel();
	String getMarker();
	Geometry getShape();
}
