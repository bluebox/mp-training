package Day_1_7_25;

public interface Mappable {
    String JSON_PROPERTY = "properties";

    static void mapIt(Mappable m) {
        System.out.println(m.toJSON());
    }

    default String toJSON() {
        return "{ "+ JSON_PROPERTY +"{label:"+ getLabel()+"},{shape:"+getShape().name()+"},{marker"+getMarker()
        		+ "} }";
    }
        
    String getLabel();
    String getMarker();
    Geometry getShape();

   
}

