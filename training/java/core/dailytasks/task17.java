 import java.util.Scanner;

public class task17 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

  
        System.out.println("--- Circle Area Calculator ---");
        System.out.print("Enter the radius of the circle: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a number for the radius.");
            scanner.next(); // Consume the invalid input
        }
        double radius = scanner.nextDouble();
        double circleArea = AreaCalculator.area(radius);
        if (circleArea == -1.0) {
            System.out.println("Error: Invalid radius entered (must be non-negative).");
        } else {
            System.out.printf("Area of circle with radius %.2f = %.2f%n", radius, circleArea);
        }

       
        System.out.println("\n--- Rectangle Area Calculator ---");
        System.out.print("Enter the length of side X: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a number for side X.");
            scanner.next();
        }
        double sideX = scanner.nextDouble();

        System.out.print("Enter the length of side Y: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a number for side Y.");
            scanner.next();
        }
        double sideY = scanner.nextDouble();

        double rectangleArea = AreaCalculator.area(sideX, sideY);
        if (rectangleArea == -1.0) {
            System.out.println("Error: Invalid side lengths entered (must be non-negative).");
        } else {
            System.out.printf("Area of rectangle with sides %.2f and %.2f = %.2f%n", sideX, sideY, rectangleArea);
        }

        scanner.close();
    }
}
public class AreaCalculator {

   
    public static double area(double radius) {
        if (radius < 0) {
            return -1.0; // Invalid Value
        }
        return Math.PI * radius * radius;
    }

    public static double area(double x, double y) {
        if (x < 0 || y < 0) {
            return -1.0; // Invalid Value
        }
        return x * y;
    }
}
