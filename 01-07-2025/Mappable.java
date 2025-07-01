package com.prana;


public interface Mappable {

    String JSON_PROPERTY = """
        "properties": {%s}
        """;

    String getLabel();
    String getMarker();
    Geometry getShape();

    default String toJSON() {
        return String.format(JSON_PROPERTY,
                String.format("\"type\": \"%s\", \"label\": \"%s\", \"marker\": \"%s\"",
                        getShape(), getLabel(), getMarker()));
    }

    static void mapIt(Mappable mappable) {
        System.out.println(mappable.toJSON());

        if (mappable instanceof Building building) {
            System.out.printf("\"name\": \"%s\", \"usage\": \"%s\"%n", building.getName(), building.getUsage());
        } else if (mappable instanceof UtilityLine utilityLine) {
            System.out.printf("\"name\": \"%s\", \"utility\": \"%s\"%n", utilityLine.getName(), utilityLine.getUtility());
        }
    }
}
