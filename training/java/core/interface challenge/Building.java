import java.util.Scanner;

public class Building implements Mappable {
    private String name;
    private String usage; 

    public Building(String name, String usage) {
        this.name = name;
        this.usage = usage;
    }

    
    public Building() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Building Name: ");
        this.name = scanner.nextLine();
        System.out.print("Enter Building Usage (e.g., Government, Residential, Commercial): ");
        this.usage = scanner.nextLine();
    }

    @Override
    public String getLabel() {
        return name + " (" + usage + ")";
    }

    @Override
    public String getGeometryType() {
        return "POINT"; 
    }

    @Override
    public String getIconType() {
        return "BUILDING"; 
    }


    @Override
    public String toJSON() {
        return String.format("""
                "{%s: {type: '%s', label: '%s', marker: '%s', name: '%s', usage: '%s'}}"
                """, JSON_PROPERTY, getGeometryType(), getLabel(), getIconType(), name, usage);
    }
}