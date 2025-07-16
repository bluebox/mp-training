package corejava.july1_InterfaceClass;

import java.util.Scanner;

public class UtilityLine implements Mappable {
    private String type; 
    private String description; 

    public UtilityLine(String type, String description) {
        this.type = type;
        this.description = description;
    }

    
    public UtilityLine() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Utility Line Type (e.g., Fiber Optic, Power, Water): ");
        this.type = scanner.nextLine();
        System.out.print("Enter Utility Line Description (e.g., on College Street): ");
        this.description = scanner.nextLine();
    }

    @Override
    public String getLabel() {
        return type + " " + description;
    }

    @Override
    public String getGeometryType() {
        return "LINE"; 
    }

    @Override
    public String getIconType() {
        return "LINE"; 
    }

    
    @Override
    public String toJSON() {
        return String.format("""
                "{%s: {type: '%s', label: '%s', marker: '%s', lineType: '%s', description: '%s'}}"
                """, JSON_PROPERTY, getGeometryType(), getLabel(), getIconType(), type, description);
    }
}
