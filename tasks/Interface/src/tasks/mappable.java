package tasks;
enum Geometry{
	line,point,polygon;
}
enum color{
	black,blue,green;
}
enum pointmarkers{
	circle,diamond;
}
enum linemarkers{
	dashed,dotted,solid;
}

public interface mappable {
	String JSON_PROPERTY="""
			"properties":{%s}""";
	String getlabel();
	Geometry getshape();
	String getmarker();
	default String toJSON() {
		return """ 
				"type":"%s","label":"%s","marker":"%s" """
				.formatted(getshape(),getlabel(),getmarker());
		
	}
	
	

}
