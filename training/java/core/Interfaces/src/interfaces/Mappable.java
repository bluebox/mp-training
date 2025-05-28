package interfaces;

public interface Mappable {
	String JSON_PROPERTY="properties";
	String getLable();
	String getMarker();
	Geometry getShape();
	public default String toJson()
	{
		
		return "";
		
	}

}
