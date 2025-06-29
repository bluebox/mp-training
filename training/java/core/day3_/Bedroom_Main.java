package com.day3_;

public class Bedroom_Main {
    public static void main(String[] args) {
        Wall wall1 = new Wall("West");
        Wall wall2 = new Wall("East");
        Wall wall3 = new Wall("South");
        Wall wall4 = new Wall("North");

        Bedroom_Ceiling ceiling = new Bedroom_Ceiling(12, 55);

        Bedroom_Bed bed = new Bedroom_Bed("Modern", 4, 3, 2, 1);

        Bedroom_Lamp lamp = new Bedroom_Lamp("Classic", false, 75);

        Bedroom_Room bedroom = new Bedroom_Room("YOUR NAME HERE", wall1, wall2, wall3, wall4, ceiling, bed, lamp);

        bedroom.makeBed();
        bedroom.getLamp().turnOn();
    }
}