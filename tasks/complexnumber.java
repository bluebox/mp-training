import java.util.Scanner;

public class complexnumber {
    private double real;
    private double imaginary;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a real number:");
        double real = sc.nextDouble();
        System.out.println("Enter an imaginary number:");
        double imaginary = sc.nextDouble();
        complexnumber c = new complexnumber(real, imaginary);

        System.out.println("Enter another real number:");
        double real1 = sc.nextDouble();
        System.out.println("Enter another imaginary number:");
        double imaginary1 = sc.nextDouble();
        complexnumber c1 = new complexnumber(real1, imaginary1);

        System.out.println("Addition:");
        complexnumber sum = c.add(c1);
        System.out.println("Result: " + sum);

        System.out.println("Subtraction:");
        complexnumber diff = c.sub(c1);
        System.out.println("Result: " + diff);

        sc.close();
    }

    public complexnumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImage() {
        return imaginary;
    }

    public complexnumber add(complexnumber c) {
        return new complexnumber(this.real + c.real, this.imaginary + c.imaginary);
    }

    public complexnumber sub(complexnumber c) {
        return new complexnumber(this.real - c.real, this.imaginary - c.imaginary);
    }

    @Override
    public String toString() {
        if (imaginary >= 0)
            return real + " + " + imaginary + "i";
        else
            return real + " - " + (-imaginary) + "i";
    }
}