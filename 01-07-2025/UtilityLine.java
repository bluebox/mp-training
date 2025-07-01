package com.prana;


public class UtilityLine implements Mappable {

    private String name;
    private UtilityType utility;
    private Color color;
    private LineMarker marker;

    public UtilityLine(String name, UtilityType utility, Color color, LineMarker marker) {
        this.name = name;
        this.utility = utility;
        this.color = color;
        this.marker = marker;
    }

    @Override
    public String getLabel() {
        return name + " (" + utility + ")";
    }

    @Override
    public String getMarker() {
        return color + " " + marker;
    }

    @Override
    public Geometry getShape() {
        return Geometry.LINE;
    }

    public String getName() {
        return name;
    }

    public UtilityType getUtility() {
        return utility;
    }
}

