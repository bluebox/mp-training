package june27_constructors;

import java.util.Scanner;

public class ComplexNumberCalculator {

    private double real;
    private double imaginary;

    // Constructor
    public ComplexNumberCalculator(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    // Get real part
    public double getReal() {
        return real;
    }

    // Get imaginary part
    public double getImaginary() {
        return imaginary;
    }

    // Add real and imaginary values
    public void add(double real, double imaginary) {
        this.real += real;
        this.imaginary += imaginary;
    }

    // Add another complex number
    public void add(ComplexNumberCalculator other) {
        this.real += other.getReal();
        this.imaginary += other.getImaginary();
    }

    // Subtract real and imaginary values
    public void subtract(double real, double imaginary) {
        this.real -= real;
        this.imaginary -= imaginary;
    }

    // Subtract another complex number
    public void subtract(ComplexNumberCalculator other) {
        this.real -= other.getReal();
        this.imaginary -= other.getImaginary();
    }

    // Display complex number
    public void display() {
        System.out.println(real + " + " + imaginary + "i");
    }

    // Main method with dynamic input
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input first complex number
        System.out.println("Enter real part of first complex number:");
        double real1 = sc.nextDouble();
        System.out.println("Enter imaginary part of first complex number:");
        double imaginary1 = sc.nextDouble();
        ComplexNumberCalculator number1 = new ComplexNumberCalculator(real1, imaginary1);

        // Input second complex number
        System.out.println("Enter real part of second complex number:");
        double real2 = sc.nextDouble();
        System.out.println("Enter imaginary part of second complex number:");
        double imaginary2 = sc.nextDouble();
        ComplexNumberCalculator number2 = new ComplexNumberCalculator(real2, imaginary2);

        // Display initial numbers
        System.out.println("\nFirst Complex Number:");
        number1.display();
        System.out.println("Second Complex Number:");
        number2.display();

        // Perform addition
        System.out.println("\nAdding second number to first...");
        number1.add(number2);
        System.out.println("Result after addition (First Number):");
        number1.display();

        // Perform subtraction
        System.out.println("\nSubtracting first number from second...");
        number2.subtract(number1);
        System.out.println("Result after subtraction (Second Number):");
        number2.display();

        sc.close();
    }
}
