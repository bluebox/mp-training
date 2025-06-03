package interfaces;

public interface Mappable {
	
	String JSON_PROPERTY = "properties";
	
	static void mapit(Mappable mappable)
	{
		
	}
	
	default String toJSON()
	{
		return null;
		
	}
	
	String getLabel();
	String getMarker();
	Geometry getShape();
}
