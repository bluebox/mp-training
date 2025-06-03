package interfaces;

public class Building implements Mappable {
	private String name;
	private UsageType type;
	
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
		// TODO Auto-generated method stub
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
