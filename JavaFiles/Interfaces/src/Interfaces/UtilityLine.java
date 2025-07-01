package Interfaces;

public class UtilityLine implements Mappable{
	Geometry shape=Geometry.LINE;
	
	private String label;
	private String marker;
	
	

	public UtilityLine( String label, String marker) {
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


	public void map() {
	    Mappable.map(this);
	}

}
