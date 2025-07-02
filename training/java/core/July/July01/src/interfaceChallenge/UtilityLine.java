package interfaceChallenge;

public class UtilityLine implements Mappable {
	
	private String name;
	private UtilityType type;
	
	
	public UtilityLine(String name, UtilityType type) {
		this.name = name;
		this.type = type;
	}
	
	@Override
	public String getLabel() {
		return name + " ( "+ type+ " )";
	}
	@Override
	public String getMarker() {
		return "GREEN DOTTED";
	}
	@Override
	public Geometry getShape() {
		return Geometry.LINE;
	}
	
	private String getProperties() {
		return String.format("""
				"type": "%s", "label": "%s", "marker": "%s"
										""",getShape(),getLabel(),getMarker());
	}
	public String toJSON() {
		return String.format(JSON_PROPERTY,getProperties()+String.format(""" 
				"name:" "%s" "usage:" "%s" """,name,type));
	}
}
