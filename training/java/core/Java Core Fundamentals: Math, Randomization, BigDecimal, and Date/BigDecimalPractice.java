package javaCoreFundamentalsRegEx;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalPractice {
    public static void main(String[] args) {
        BigDecimal amount1 = new BigDecimal("123.45");
        BigDecimal amount2 = new BigDecimal("67.89");

        // Example of basic operations
        BigDecimal sum = amount1.add(amount2);
        BigDecimal difference = amount1.subtract(amount2);
        BigDecimal product = amount1.multiply(amount2);
        BigDecimal quotient = amount1.divide(amount2, BigDecimal.ROUND_HALF_UP);

        System.out.println("Sum: " + sum);
        System.out.println("Difference: " + difference);
        System.out.println("Product: " + product);
        System.out.println("Quotient: " + quotient);
        
        
        BigDecimal value1 = new BigDecimal("10.123456789");
        BigDecimal value2 = new BigDecimal("3.14");

        // Division with specified scale and rounding mode
        BigDecimal result = value1.divide(value2, 5, RoundingMode.HALF_UP);
        System.out.println("Division Result (5 decimal places): " + result);

        // Setting scale with rounding
        BigDecimal scaledValue = value1.setScale(3, RoundingMode.HALF_UP);
        System.out.println("Scaled Value (3 decimal places): " + scaledValue);

        // Example of multiplying and controlling precision
        BigDecimal multiplier = new BigDecimal("2.5");
        product = value1.multiply(multiplier).setScale(4, RoundingMode.HALF_UP);
        System.out.println("Product (4 decimal places): " + product);
    }
}
