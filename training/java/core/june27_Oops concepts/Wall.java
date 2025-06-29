package june27_constructors;

import java.util.Scanner;

public class Wall {
    private double width;
    private double height;

    // No-args (default) constructor
    public Wall() {
        this.width = 0;
        this.height = 0;
    }

    // Parameterized constructor
    public Wall(double width, double height) {
        this.width = (width < 0) ? 0 : width;
        this.height = (height < 0) ? 0 : height;
    }

    // Getter for width
    public double getWidth() {
        return width;
    }

    // Getter for height
    public double getHeight() {
        return height;
    }

    // Setter for width
    public void setWidth(double width) {
        this.width = (width < 0) ? 0 : width;
    }

    // Setter for height
    public void setHeight(double height) {
        this.height = (height < 0) ? 0 : height;
    }

    // Method to calculate area
    public double getArea() {
        return width * height;
    }

    // Main method to demonstrate both constructors
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Using Default Constructor
        System.out.println("Creating wall using Default Constructor:");
        Wall wall1 = new Wall();

        System.out.print("Enter width for wall1: ");
        double width1 = sc.nextDouble();
        wall1.setWidth(width1);

        System.out.print("Enter height for wall1: ");
        double height1 = sc.nextDouble();
        wall1.setHeight(height1);

        System.out.println("Wall1 -> Width = " + wall1.getWidth() + ", Height = " + wall1.getHeight() + ", Area = " + wall1.getArea());


        //Using Parameterized Constructor
        System.out.println("\nCreating wall using Parameterized Constructor:");
        System.out.print("Enter width for wall2: ");
        double width2 = sc.nextDouble();

        System.out.print("Enter height for wall2: ");
        double height2 = sc.nextDouble();

        Wall wall2 = new Wall(width2, height2);

        System.out.println("Wall2 -> Width = " + wall2.getWidth() + ", Height = " + wall2.getHeight() + ", Area = " + wall2.getArea());

        sc.close();
    }
}
