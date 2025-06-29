package june27_constructors;

import java.util.Scanner;

public class CylinderCalculator {

    // Circle Class
    static class Circle {
        private double radius;

        public Circle(double radius) {
            this.radius = (radius < 0) ? 0 : radius;
        }

        public double getRadius() {
            return radius;
        }

        public double getArea() {
            return radius * radius * Math.PI;
        }
    }

    // Cylinder Class extending Circle
    static class Cylinder extends Circle {
        private double height;

        public Cylinder(double radius, double height) {
            super(radius);
            this.height = (height < 0) ? 0 : height;
        }

        public double getHeight() {
            return height;
        }

        public double getVolume() {
            return getArea() * height;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input for Circle
        System.out.println("Enter radius for the Circle:");
        double circleRadius = sc.nextDouble();

        Circle circle = new Circle(circleRadius);
        System.out.println("\n--- Circle Details ---");
        System.out.println("Circle radius = " + circle.getRadius());
        System.out.println("Circle area = " + circle.getArea());

        // Input for Cylinder
        System.out.println("\nEnter radius for the Cylinder:");
        double cylinderRadius = sc.nextDouble();

        System.out.println("Enter height for the Cylinder:");
        double cylinderHeight = sc.nextDouble();

        Cylinder cylinder = new Cylinder(cylinderRadius, cylinderHeight);
        System.out.println("\n--- Cylinder Details ---");
        System.out.println("Cylinder radius = " + cylinder.getRadius());
        System.out.println("Cylinder height = " + cylinder.getHeight());
        System.out.println("Cylinder area = " + cylinder.getArea());
        System.out.println("Cylinder volume = " + cylinder.getVolume());

        sc.close();
    }
}
