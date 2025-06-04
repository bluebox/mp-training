package interfaces;

public class UtilityLine implements Mappable {

	
	private String name;
	private UtilityType type;
	private String Label;
	private Marker marker;
	private Geometry geometry;
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UtilityLine(String name, UtilityType type, String label, Marker marker, Geometry geometry) {
		super();
		this.name = name;
		this.type = type;
		Label = label;
		this.marker = marker;
		this.geometry = geometry;
	}

	public UtilityType getType() {
		return type;
	}

	public void setType(UtilityType type) {
		this.type = type;
	}

	@Override
	public String getLabel() {
		
		return this.Label;
	}

	@Override
	public Marker getMarker() {
		
		return this.marker;
	}

	@Override
	public Geometry getShape() {
		
		return this.geometry;
	}

	
	@Override
	public String toJSON()
	{
		return Mappable.super.toJSON()+"\", \"UtilityType\": \""+getType()+"\", \"Name : \""+getName()+"\"";
	}

}
