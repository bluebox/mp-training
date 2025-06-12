package junitprograms;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

 private Calculator calculator;

 @BeforeEach
 public void setUp() {
     calculator = new Calculator();
 }

 @Test
 public void testAdd() {
     assertEquals(5, calculator.add(2, 3));
     assertEquals(-1, calculator.add(-2, 1));
 }

 @Test
 public void testSubtract() {
     assertEquals(1, calculator.subtract(3, 2));
     assertEquals(-3, calculator.subtract(2, 5));
 }

 @Test
 public void testMultiply() {
     assertEquals(6, calculator.multiply(2, 3));
     assertEquals(0, calculator.multiply(0, 100));
 }

 @Test
 public void testDivide() {
     assertEquals(2, calculator.divide(10, 5));
 }

 @Test
 public void testDivideByZero() {
     Exception exception = assertThrows(IllegalArgumentException.class, () -> {
         calculator.divide(10, 0);
     });

     assertEquals("Cannot divide by zero", exception.getMessage());
 }
}
