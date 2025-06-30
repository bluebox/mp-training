import java.util.Scanner; 

// Circle Class
class Circle {
    private double radius;

    // Constructor
    public Circle(double radius) {
        if (radius < 0) {
            this.radius = 0;
        } else {
            this.radius = radius;
        }
    }

    // Method to get radius
    public double getRadius() {
        return radius;
    }

    // Method to calculate area
    public double getArea() {
        return radius * radius * Math.PI;
    }
}

// Cylinder Class extending Circle
class Cylinder extends Circle {
    private double height;

    // Constructor
    public Cylinder(double radius, double height) {
        super(radius); // Call parent constructor
        if (height < 0) {
            this.height = 0;
        } else {
            this.height = height;
        }
    }

    // Method to get height
    public double getHeight() {
        return height;
    }

    // Method to calculate volume
    public double getVolume() {
        return getArea() * height; // Calculate volume by multiplying area with height
    }
}

// Main class for testing and user input
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object

        // Get Circle radius from user
        System.out.print("Enter radius for Circle: ");
        double circleRadius = scanner.nextDouble();
        Circle circle = new Circle(circleRadius);

        // Display Circle details
        System.out.println("Circle Radius: " + circle.getRadius());
        System.out.println("Circle Area: " + circle.getArea());

        // Get Cylinder radius and height from user
        System.out.print("Enter radius for Cylinder: ");
        double cylinderRadius = scanner.nextDouble();
        System.out.print("Enter height for Cylinder: ");
        double cylinderHeight = scanner.nextDouble();
        Cylinder cylinder = new Cylinder(cylinderRadius, cylinderHeight);

        // Display Cylinder details
        System.out.println("Cylinder Radius: " + cylinder.getRadius());
        System.out.println("Cylinder Height: " + cylinder.getHeight());
        System.out.println("Cylinder Volume: " + cylinder.getVolume());

        scanner.close(); // Close the scanner
    }
}