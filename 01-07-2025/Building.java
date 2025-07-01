package com.prana;


public class Building implements Mappable {

    private String name;
    private UsageType usage;
    private Color color;
    private PointMarker marker;

    public Building(String name, UsageType usage, Color color, PointMarker marker) {
        this.name = name;
        this.usage = usage;
        this.color = color;
        this.marker = marker;
    }

    @Override
    public String getLabel() {
        return name + " (" + usage + ")";
    }

    @Override
    public String getMarker() {
        return color + " " + marker;
    }

    @Override
    public Geometry getShape() {
        return Geometry.POINT;
    }

    public String getName() {
        return name;
    }

    public UsageType getUsage() {
        return usage;
    }
}
