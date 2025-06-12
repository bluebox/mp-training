package junitprograms;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ConverterUtilTest {

    @Test
    public void testConverter() {
        int a = 10;
        int b = 5;
        int expectedOutput = 300;

        assertEquals(expectedOutput, ConverterUtil.converter(a, b));
    }
}
