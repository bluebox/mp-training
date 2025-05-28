package interfaces;

public class Building implements Mappable {
	Geometry shape;
	private String label;
	private String marker;
	
	public Building(String label, String marker) {
		super();
		shape=Geometry.POINT;
		this.label = label;
		this.marker = marker;
	}

	@Override
	public String getLable() {
		// TODO Auto-generated method stub
		return label;
	}

	@Override
	public String getMarker() {
		// TODO Auto-generated method stub
		return marker;
	}

	@Override
	public Geometry getShape() {
		// TODO Auto-generated method stub
		return shape;
	}


}
