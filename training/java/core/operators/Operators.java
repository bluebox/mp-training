package operators;

public class Operators {
    public static void main(String[] args) {
        // Arithmetic operators
        int a = 10;
        int b = 5;

        System.out.println("a + b = " + (a + b));   // Addition
        System.out.println("a - b = " + (a - b));   // Subtraction
        System.out.println("a * b = " + (a * b));   // Multiplication
        System.out.println("a / b = " + (a / b));   // Division
        System.out.println("a % b = " + (a % b));   // Modulus

        // Assignment operators
        int c = 10;
        c += 5;  // same as c = c + 5;
        System.out.println("c after += 5: " + c);

        // Comparison operators
        System.out.println("a == b: " + (a == b)); // equal to
        System.out.println("a != b: " + (a != b)); // not equal to
        System.out.println("a > b: " + (a > b));   // greater than
        System.out.println("a < b: " + (a < b));   // less than
        System.out.println("a >= b: " + (a >= b)); // greater than or equal to
        System.out.println("a <= b: " + (a <= b)); // less than or equal to

        // Logical operators
        boolean x = true;
        boolean y = false;

        System.out.println("x && y: " + (x && y)); // AND
        System.out.println("x || y: " + (x || y)); // OR
        System.out.println("!x: " + (!x));         // NOT
    }
}
