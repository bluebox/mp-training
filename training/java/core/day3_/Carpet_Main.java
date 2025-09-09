package com.day3_;
public class Carpet_Main {
    public static void main(String[] args) {
        Carpet carpet = new Carpet(3.5);
        Floor floor = new Floor(2.5, 4.5);
        Carpet_Calculator calculator = new Carpet_Calculator(floor, carpet);
        System.out.println("total-1= " + calculator.getTotalCost());

        carpet = new Carpet(1.5);
        floor = new Floor(5.4, 4.5);
        calculator = new Carpet_Calculator(floor, carpet);
        System.out.println("total-2= " + calculator.getTotalCost());
    }
}