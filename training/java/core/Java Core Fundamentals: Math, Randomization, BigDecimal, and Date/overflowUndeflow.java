package javaCoreFundamentalsRegEx;

public class overflowUndeflow {

    public static void main(String[] args) {
        try {
            long value1 = Long.MAX_VALUE;
            long incrementedValue1 = Math.incrementExact(value1);
            System.out.println("Incremented Value: " + incrementedValue1);
        } catch (ArithmeticException e) {
            System.out.println("Overflow occurred during increment: " + e.getMessage());
        }

        try {
            long value2 = 10;
            long incrementedValue2 = Math.incrementExact(value2);
            System.out.println("Incremented Value: " + incrementedValue2);
        } catch (ArithmeticException e) {
            System.out.println("Overflow occurred during increment: " + e.getMessage());
        }
    }
}
