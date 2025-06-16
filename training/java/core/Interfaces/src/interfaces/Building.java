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
		return label;
	}

	@Override
	public String getMarker() {
		return marker;
	}

	@Override
	public Geometry getShape() {
		return shape;
	}
}
