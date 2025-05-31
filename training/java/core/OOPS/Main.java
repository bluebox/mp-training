package OOPS;

interface Mappable {
    String JSON_PROPERTY = """
        "properties": {%s}
        """;

    String getLabel();
    String getMarker();
    Geometry getShape();

    default String toJSON() {
        return JSON_PROPERTY.formatted(String.join(", ",
                "\"type\": \"" + getShape() + "\"",
                "\"label\": \"" + getLabel() + "\"",
                "\"marker\": \"" + getMarker() + "\""
        ));
    }

    static void mapIt(Mappable mappable) {
        StringBuilder sb = new StringBuilder();
        sb.append(mappable.toJSON().replace("}", ""));
        if (mappable instanceof Building b) {
            sb.append(", \"name\": \"").append(b.name).append("\"");
            sb.append(", \"usage\": \"").append(b.usage).append("\"");
        } else if (mappable instanceof UtilityLine u) {
            sb.append(", \"name\": \"").append(u.name).append("\"");
            sb.append(", \"utility\": \"").append(u.utilityType).append("\"");
        }
        sb.append("}");
        System.out.println(sb.toString());
    }
}

enum Geometry {
    POINT, LINE, POLYGON
}

enum UsageType {
    BUSINESS, ENTERTAINMENT, GOVERNMENT
}

enum UtilityType {
    ELECTRICAL, FIBER_OPTIC
}

enum Color {
    BLACK, BLUE, GREEN, RED
}

enum PointMarkers {
    CIRCLE, STAR, DIAMOND
}

enum LineMarkers {
    DASHED, DOTTED, SOLID
}

class Building implements Mappable {
    String name;
    UsageType usage;

    Building(String name, UsageType usage) {
        this.name = name;
        this.usage = usage;
    }

    public String getLabel() {
        return name + " (" + usage + ")";
    }

    public String getMarker() {
        return Color.RED + " " + PointMarkers.STAR;
    }

    public Geometry getShape() {
        return Geometry.POINT;
    }
}

class UtilityLine implements Mappable {
    String name;
    UtilityType utilityType;

    UtilityLine(String name, UtilityType utilityType) {
        this.name = name;
        this.utilityType = utilityType;
    }

    public String getLabel() {
        return name + " (" + utilityType + ")";
    }

    public String getMarker() {
        return Color.GREEN + " " + LineMarkers.DOTTED;
    }

    public Geometry getShape() {
        return Geometry.LINE;
    }
}

public class Main {
    public static void main(String[] args) {
        Building b = new Building("Sydney Town Hall", UsageType.GOVERNMENT);
        UtilityLine u = new UtilityLine("College St", UtilityType.FIBER_OPTIC);
        Mappable.mapIt(b);
        Mappable.mapIt(u);
    }
}
