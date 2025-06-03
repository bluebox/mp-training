package interfaces;

public class UtilityLine implements Mappable {

	
	private String name;
	private UtilityType type;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UtilityType getType() {
		return type;
	}

	public void setType(UtilityType type) {
		this.type = type;
	}

	@Override
	public String getLabel() {
		
		return null;
	}

	@Override
	public String getMarker() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Geometry getShape() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
