package interfaceChallenge;

public class Building implements Mappable{
	private String name;
	private BuildingType type;
	
	
	public Building(String name, BuildingType type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public String getLabel() {
		return name + "( "+ type + " )";
	}

	@Override
	public String getMarker() {
		return	"RED STAR" ;
	}

	@Override
	public Geometry getShape() {
		return Geometry.POINT;
	}
	
	private String getProperties() {
		return String.format("""
				"type": "%s", "label": "%s", "marker": "%s"
										""",getShape(),getLabel(),getMarker());
	}
	@Override
	public String toJSON() {
		return String.format(JSON_PROPERTY,getProperties()+String.format(""" 
				"name:" "%s" "usage:" "%s" """,name,type));
	}
}
