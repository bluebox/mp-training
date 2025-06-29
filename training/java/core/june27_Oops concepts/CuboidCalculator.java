package june27_constructors;

import java.util.Scanner;

public class CuboidCalculator {

    // Rectangle class
    static class Rectangle {
        private double width;
        private double length;

        public Rectangle(double width, double length) {
            this.width = (width < 0) ? 0 : width;
            this.length = (length < 0) ? 0 : length;
        }

        public double getWidth() {
            return width;
        }

        public double getLength() {
            return length;
        }

        public double getArea() {
            return width * length;
        }
    }

    // Cuboid class extends Rectangle
    static class Cuboid extends Rectangle {
        private double height;

        public Cuboid(double width, double length, double height) {
            super(width, length);
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

        System.out.println("Enter width of rectangle:");
        double width = sc.nextDouble();
        System.out.println("Enter length of rectangle:");
        double length = sc.nextDouble();

        Rectangle rectangle = new Rectangle(width, length);
        System.out.println("\n--- Rectangle Details ---");
        System.out.println("Width = " + rectangle.getWidth());
        System.out.println("Length = " + rectangle.getLength());
        System.out.println("Area = " + rectangle.getArea());

        System.out.println("\nEnter height of cuboid:");
        double height = sc.nextDouble();

        Cuboid cuboid = new Cuboid(width, length, height);
        System.out.println("\n--- Cuboid Details ---");
        System.out.println("Width = " + cuboid.getWidth());
        System.out.println("Length = " + cuboid.getLength());
        System.out.println("Height = " + cuboid.getHeight());
        System.out.println("Area = " + cuboid.getArea());
        System.out.println("Volume = " + cuboid.getVolume());

        sc.close();
    }
}
