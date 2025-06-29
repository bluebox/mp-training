package com.day3_;

import java.awt.Point;

public class Point_Class_Main {
    public static void main(String[] args) {
        Point first = new Point(6, 5);
        Point second = new Point(3, 1);

        System.out.println("Distance from first to (0,0): " + first.distance(0, 0));
        System.out.println("Distance from first to second: " + first.distance(second));            
        System.out.println("Distance from first to (2,2): " + first.distance(2, 2));                

        Point point = new Point();
        System.out.println("Distance from point to (0,0): " + point.distance(0, 0));
    }
}