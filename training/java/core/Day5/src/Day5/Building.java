package Day5;

public class Building implements mappable{
	
	private String name;
	private UsageType type;
	private pointMarker marker;

	
	public Building(String name, UsageType type, pointMarker marker) {
		this.name = name;
		this.type = type;
		this.marker = marker;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return name+"("+type+")";
	}

	@Override
	public String getMarker() {
		// TODO Auto-generated method stub
		return marker.toString();
	}

	@Override
	public Geometry getShape() {
		// TODO Auto-generated method stub
		return Geometry.POINT;
	}

	@Override
	public String toJSON() {
		// TODO Auto-generated method stub
		return """
	            "type": "%s", "label": "%s", "marker": "%s", "name": "%s", "usage": "%s"
	            """.formatted(getShape(), getLabel(), getMarker(), name, type);
	}
	
	
}
