package day17;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class JunitMethods {
	MathOperations mathOperations = new MathOperations();

	@Test
	void testAssertEquals() {
		int expected = 5;
		int actual = mathOperations.add(2, 3);
		assertEquals(expected, actual);
	}
	
	@Test
	void testAssertNotEquals() {
		int expected = 52;
		int actual = mathOperations.add(2, 3);
		assertNotEquals(expected, actual);
	}
	@Test
    void testAssertSame() { 
        String text = mathOperations.getHello();
        String text2 = "Hello";
        assertSame(text, text2);
    }
}
