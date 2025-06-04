package interfaces;

public class Main {

	public static void main(String[] args) {
		
		Marker marker = Marker.Marker;
		marker.setPointMarker(PointMarkers.CIRCLE);
		Building b = new Building("Movie theater",UsageType.ENTERTAINMENT,"Madhapur",marker,Geometry.POLYGON);
		
		Mappable.mapit(b);
		System.out.println(b.toJSON());
		
		marker.setLineMarker(LineMarkers.DASHED);
		marker.setPointMarker(null);
		
		UtilityLine ul = new UtilityLine("BHEL FIBEROPTIC LINE", UtilityType.FIBER_OPTIC, "Hyd OPTICAL FIBERS",  marker, Geometry.LINE);
		System.out.println(Mappable.mapit(ul));
	}

}
