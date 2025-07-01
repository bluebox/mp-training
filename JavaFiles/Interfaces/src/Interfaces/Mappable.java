package Interfaces;

public interface Mappable {
		String JSON_PROPERTY="Properties : ";
		String getLable();
		String getMarker();
		Geometry getShape();
		
		public default String toJson()
		{
			
			return "";
			
		}
		
		public static void map(Mappable ma) {
			System.out.println("{"+JSON_PROPERTY+"Lable: "+ma.getLable()+","+"Marker: "+ma.getMarker()+","+"Shape: "+ma.getShape()+"}");
		}

}

