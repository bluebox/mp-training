package mappableInterface;

public class Building implements mappable {
	private String name;
	private UsageType type;
	private pointMarker marker;

	
	public Building(String name, UsageType type, pointMarker marker) {
		this.name = name;
		this.type = type;
		this.marker = marker;
	}


	public String getLabel() {
		
		return name+"("+type+")";
	}

	
	public String getMarker() {
		
		return marker.toString();
	}


	public Geometry getShape() {
	
		return Geometry.POINT;
	}

	
	public String toJSON() {
		
		return """
	            "type": "%s", "label": "%s", "marker": "%s", "name": "%s", "usage": "%s"
	            """.formatted(getShape(), getLabel(), getMarker(), name, type);
	}
	
}
