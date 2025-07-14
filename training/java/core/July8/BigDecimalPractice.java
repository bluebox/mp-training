package July8;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalPractice {

	public static void main(String[] args) {
		
		BigDecimal num1 = new BigDecimal("10.25");
		BigDecimal num2 = new BigDecimal("3.14");

		System.out.println("Sum: " + num1.add(num2));
		System.out.println("Difference: " + num1.subtract(num2));
		System.out.println("Product: " + num1.multiply(num2));
		System.out.println("Quotient: " + num1.divide(num2, 2, RoundingMode.HALF_UP));
		System.out.println("Scaled Value: " + num1.setScale(3, RoundingMode.HALF_UP));
		
		System.out.println("The Unscaled Value of " + num1 + " is " + num1.unscaledValue());
		System.out.println("The Scale Value of " + num1 + " is " + num1.scale());
		System.out.println("The Precision Value of " + num1 + " is " + num1.precision());
		
		BigDecimal one = new BigDecimal("1");
		BigDecimal two = BigDecimal.ONE;
		System.out.println(one.equals(two));
		System.out.println(one==two);
	}
}
