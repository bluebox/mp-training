package challenge_18th_july;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @Test
    public void testAddition() {
        Calculator calculator = new Calculator();
        int result = calculator.add(5, 3);
        assertEquals(8, result); // Asserts that 5 + 3 equals 8
    }

    @Test
    public void testSubtraction() {
        Calculator calculator = new Calculator();
        int result = calculator.subtract(10, 4);
        assertEquals(6, result); // Asserts that 10 - 4 equals 6
    }
    
    @Test
    public void testMultiplication() {
    	Calculator calc=new Calculator();
    	double res=calc.multiply(123, 100);
    	assertEquals(12300, res);
    }
    
}
