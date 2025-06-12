package junitprograms;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ConverterUtilNewTest {

    @Test
    public void testConverter_ValidInput() {
        int a = 10;
        int b = 5;
        int expectedOutput = 200; // because 10 * 100 / 5 = 200
        assertEquals(expectedOutput, ConverterUtil.converter(a, b));
    }

    @Test
    public void testConverter_DivideByZero() {
        int a = 10;
        int b = 0;

        assertThrows(ArithmeticException.class, () -> {
            ConverterUtil.converter(a, b);
        });
    }
}
