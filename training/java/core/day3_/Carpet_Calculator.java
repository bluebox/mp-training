package com.day3_;

public class Carpet_Calculator {
    private Floor floor;
    private Carpet carpet;

    public Carpet_Calculator(Floor floor, Carpet carpet) {
        this.floor = floor;
        this.carpet = carpet;
    }

    public double getTotalCost() {
        return floor.getArea() * carpet.getCost();
    }
}