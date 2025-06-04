package interfaces;

public interface Mappable {
	
	String JSON_PROPERTY = "\"Properties\": ";
	
	static String mapit(Mappable mappable)
	{
		return mappable.toJSON();
	}
	
	default String toJSON()
	{
		return JSON_PROPERTY+"{\"type\": \""+getShape()+"\", "+
		"\"label\" : \""+getLabel()+
				"\" ,\"marker\": \""+(getMarker().getLineMarker() != null?getMarker().getLineMarker():getMarker().getPointMarker())+"\"";
		
		
		
	}
	
	String getLabel();
//	String getType();
	Geometry getShape();
	//UsageType getUsage();
	Marker getMarker();
}
