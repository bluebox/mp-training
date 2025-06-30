
import java.util.Scanner; 

public class point {
    
    private int x;
    private int y;

    
    public point() {
        this.x = 0;
        this.y = 0;
    }

    
    public point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    
    public int getX() {
        return x;
    }

    
    public int getY() {
        return y;
    }

    
    public void setX(int x) {
        this.x = x;
    }

    
    public void setY(int y) {
        this.y = y;
    }

    
    public double distance() {
        return Math.sqrt((this.x * this.x) + (this.y * this.y));
    }

    // Method to calculate distance from another Point object
    public double distance(point other) {
        int deltaX = other.x - this.x;
        int deltaY = other.y - this.y;
        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
    }

    // Method to calculate distance from given x, y coordinates
    public double distance(int x, int y) {
        int deltaX = x - this.x;
        int deltaY = y - this.y;
        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
    }

    // Main method for testing and user input
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object [4, 11]

        // Create a Point object using the no-arg constructor
        point p1 = new point();
        System.out.println("Point 1 (default): (" + p1.getX() + ", " + p1.getY() + ")");

        // Get user input for Point 2
        System.out.print("Enter x-coordinate for Point 2: ");
        int x2 = scanner.nextInt();
        System.out.print("Enter y-coordinate for Point 2: ");
        int y2 = scanner.nextInt();
        point p2 = new point(x2, y2); // Create Point 2 with user input

        System.out.println("Point 2 (user input): (" + p2.getX() + ", " + p2.getY() + ")");

        // Demonstrating methods
        System.out.println("Distance of Point 1 from (0,0): " + p1.distance());
        System.out.println("Distance between Point 1 and Point 2: " + p1.distance(p2));

        // Get user input for a third point to calculate distance
        System.out.print("Enter x-coordinate for a third point: ");
        int x3 = scanner.nextInt();
        System.out.print("Enter y-coordinate for a third point: ");
        int y3 = scanner.nextInt();
        System.out.println("Distance of Point 2 from (" + x3 + ", " + y3 + "): " + p2.distance(x3, y3));

        scanner.close(); // Close the scanner
    }
}