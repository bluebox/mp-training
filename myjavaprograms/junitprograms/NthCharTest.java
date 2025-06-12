package junitprograms;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class NthCharTest {
    
    @Test
    public void testEveryNthChar_basicScenario() {
        // Arrange
        Utilities utils = new Utilities();
        char[] input = {'h', 'e', 'l', 'l', 'o'};
        int n = 2;
        char[] expected = {'e', 'l'};
        
        // Act
        char[] result = utils.everyNthChar(input, n);
        
        // Assert
        assertArrayEquals("Failed to select every 2nd character", 
                         expected, result);
    }
    
    @Test
    public void testEveryNthChar_emptyArray() {
        // Arrange
        Utilities utils = new Utilities();
        char[] input = {};
        int n = 2;
        
        // Act
        char[] result = utils.everyNthChar(input, n);
        
        // Assert
        assertArrayEquals("Empty array should return empty array", 
                         input, result);
    }
    
    @Test
    public void testEveryNthChar_nullInput() {
        // Arrange
        Utilities utils = new Utilities();
        char[] input = null;
        int n = 2;
        
        // Act
        char[] result = utils.everyNthChar(input, n);
        
        // Assert
        assertNull("Null input should return null", result);
    }
    
    @Test
    public void testEveryNthChar_nLargerThanArray() {
        // Arrange
        Utilities utils = new Utilities();
        char[] input = {'h', 'e', 'l', 'l', 'o'};
        int n = 10;
        char[] expected = {};
        
        // Act
        char[] result = utils.everyNthChar(input, n);
        
        // Assert
        assertArrayEquals("Should return empty array when n > array length", 
                         expected, result);
    }
    
    @Test
    public void testEveryNthChar_nEqualsOne() {
        // Arrange
        Utilities utils = new Utilities();
        char[] input = {'h', 'e', 'l', 'l', 'o'};
        int n = 1;
        char[] expected = {'h', 'e', 'l', 'l', 'o'};
        
        // Act
        char[] result = utils.everyNthChar(input, n);
        
        // Assert
        assertArrayEquals("n=1 should return the original array", 
                         expected, result);
    }
    
    @Test
    public void testEveryNthChar_withSpaces() {
        // Arrange
        Utilities utils = new Utilities();
        char[] input = {'h', ' ', 'e', ' ', 'l', ' ', 'l', ' ', 'o'};
        int n = 2;
        char[] expected = {' ', ' ', ' ', ' '};
        
        // Act
        char[] result = utils.everyNthChar(input, n);
        
        // Assert
        assertArrayEquals("Should correctly handle spaces in array", 
                         expected, result);
    }
}
