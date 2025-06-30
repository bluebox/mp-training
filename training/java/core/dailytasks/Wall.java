import java.util.Scanner;

public class Wall {
    private double width;
    private double height;

    // No-args constructor
    public Wall() {
        this.width = 0;
        this.height = 0;
    }

    // Constructor with parameters
    public Wall(double width, double height) {
        if (width < 0) {
            this.width = 0;
        } else {
            this.width = width;
        }

        if (height < 0) {
            this.height = 0;
        } else {
            this.height = height;
        }
    }

    // Method to get width
    public double getWidth() {
        return width;
    }

    // Method to get height
    public double getHeight() {
        return height;
    }

    // Method to set width
    public void setWidth(double width) {
        if (width < 0) {
            this.width = 0;
        } else {
            this.width = width;
        }
    }

    // Method to set height
    public void setHeight(double height) {
        if (height < 0) {
            this.height = 0;
        } else {
            this.height = height;
        }
    }

    // Method to get area
    public double getArea() {
        return width * height;
    }

    // Main method for testing and user input
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter wall width:");
        double userWidth = scanner.nextDouble();

        System.out.println("Enter wall height:");
        double userHeight = scanner.nextDouble();

        // Create a Wall object using the parameterized constructor
        Wall wall = new Wall(userWidth, userHeight);

        System.out.println("Initial Wall Area: " + wall.getArea());

        // Demonstrate setting new values
        System.out.println("Enter new width:");
        double newWidth = scanner.nextDouble();
        wall.setWidth(newWidth);

        System.out.println("Enter new height:");
        double newHeight = scanner.nextDouble();
        wall.setHeight(newHeight);

        System.out.println("Updated Wall Area: " + wall.getArea());

        scanner.close();
    }
}