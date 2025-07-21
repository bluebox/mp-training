package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculatorTesting {
private static Calculator calc;
	@BeforeClass
	public static void BeforeClass() {
		 calc=new Calculator();
	}
	
	@Test
	public void test() {
		int sum=calc.add(4, 3);
		assertEquals(7,sum );
	}

}
