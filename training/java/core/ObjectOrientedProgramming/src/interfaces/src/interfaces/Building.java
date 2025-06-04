package interfaces;

public class Building implements Mappable {
	private String name;
	private UsageType type;
	private String Label;
	private Marker marker;
	private Geometry geometry;
	
	
	
	
	
	public Building(String name, UsageType type, String label,  Marker marker, Geometry geometry) {
		super();
		this.name = name;
		this.type = type;
		Label = label;
		this.marker =  marker;
		this.geometry = geometry;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UsageType getType() {
		return type;
	}
	public void setType(UsageType type) {
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
	
	public UsageType getUsage() {
		return this.type;
	}
	
	@Override
	public String toJSON()
	{
		return Mappable.super.toJSON()+" \"name\": "+getName()+" \"type\": "+getType()+"}";
	}
}
