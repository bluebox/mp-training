package com.tulasidhar.june26;


public class ChallengeSimpleCalculator {
    public static void main(String[] args) {
        SimpleCalculator calculator = new SimpleCalculator();
        calculator.setFirstNumber(5.0);
        calculator.setSecondNumber(4);

        System.out.println("Addition = " + calculator.getAdditionResult());
        System.out.println("Subtraction = " + calculator.getSubtractionResult());
        System.out.println("Multiplication = " + calculator.getMultiplicationResult());
        System.out.println("Division = " + calculator.getDivisionResult());

        calculator.setSecondNumber(0);
        System.out.println("Division by zero = " + calculator.getDivisionResult());
    }
}