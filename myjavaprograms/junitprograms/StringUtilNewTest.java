package junitprograms;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StringUtilTest {

    @Test
    public void nullIfOddLength() {
        // Test with even-length string (should return the string itself)
        String evenInput = "even"; // length = 4
        assertEquals(evenInput, StringUtil.nullIfOddLength(evenInput));

        // Test with odd-length string (should return null)
        String oddInput = "odd"; // length = 3
        assertNull(StringUtil.nullIfOddLength(oddInput));
    }
}

