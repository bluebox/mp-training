package samplecodes;
public class OperatorPrecedence {
    public static void main(String[] args) {
        int a = 5, b = 5, c = 5, d = 30;

        int result = ((++a * b-- + c / (d - a) % 3) << 2 & 8 | 1) == 1 ? 100 : 200;

        System.out.println("Result: " + result);
    }
}