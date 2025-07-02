package mappableInterface;

public class UtilityLine  implements mappable{
	private String name;
    private UtilityType type;
    private lineMarker marker;

    
	public UtilityLine(String name, UtilityType type, lineMarker marker) {
		this.name = name;
		this.type = type;
		this.marker = marker;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return name + " (" + type + ")";
	}

	@Override
	public String getMarker() {
		// TODO Auto-generated method stub
		 return marker.toString();
	}

	@Override
	public String toJSON() {
		// TODO Auto-generated method stub
		return """
	            "type": "%s", "label": "%s", "marker": "%s", "name": "%s", "usage": "%s"
	            """.formatted(getShape(), getLabel(), getMarker(), name, type);
	}

	@Override
	public Geometry getShape() {
		// TODO Auto-generated method stub
		return Geometry.LINE;
	}
}
