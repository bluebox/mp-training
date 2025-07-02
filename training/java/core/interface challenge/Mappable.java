import java.util.HashMap;
import java.util.Map;

public interface Mappable {

    String JSON_PROPERTY = "properties";

    
    String getLabel();

    String getGeometryType();

    String getIconType();


    default String toJSON() {
        return String.format("""
                "{%s: {type: '%s', label: '%s', marker: '%s'}}"
                """, JSON_PROPERTY, getGeometryType(), getLabel(), getIconType());
    }

    
    static void printProperties(Mappable mappable) {
        System.out.println("Properties for " + mappable.getLabel() + ":");
        System.out.println("  Geometry Type: " + mappable.getGeometryType());
        System.out.println("  Icon Type: " + mappable.getIconType());
       
        System.out.println(mappable.toJSON()); 
        System.out.println("--------------------");
    }
}