package com.day3_;

public class Bedroom_Room {
    private String name;
    private Bedroom_Wall wall1;
    private Bedroom_Wall wall2;
    private Bedroom_Wall wall3;
    private Bedroom_Wall wall4;
    private Bedroom_Ceiling ceiling;
    private Bedroom_Bed bed;
    private Bedroom_Lamp lamp;

    public Bedroom_Room(String name, Bedroom_Wall wall1, Bedroom_Wall wall2, Bedroom_Wall wall3, Bedroom_Wall wall4,
                   Bedroom_Ceiling ceiling, Bedroom_Bed bed, Bedroom_Lamp lamp) {
        this.name = name;
        this.wall1 = wall1;
        this.wall2 = wall2;
        this.wall3 = wall3;
        this.wall4 = wall4;
        this.ceiling = ceiling;
        this.bed = bed;
        this.lamp = lamp;
    }

    public Bedroom_Lamp getLamp() {
        return lamp;
    }

    public void makeBed() {
        System.out.print("Bedroom -> Making bed | ");
        bed.make();
    }
}