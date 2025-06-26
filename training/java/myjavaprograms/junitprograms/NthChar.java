package junitprograms;

public class NthChar {
    
    // Existing methods...
    
    /**
     * Returns an array containing every nth character from the input array
     * @param sourceArray The input character array
     * @param n The step value (select every nth character)
     * @return Array containing every nth character
     */
    public char[] everyNthChar(char[] sourceArray, int n) {
        // Handle edge cases
        if (sourceArray == null || sourceArray.length == 0 || n <= 0) {
            return sourceArray;
        }
        
        // Calculate the size of the result array
        int resultSize = sourceArray.length / n;
        char[] result = new char[resultSize];
        
        // Fill the result array
        int index = 0;
        for (int i = n - 1; i < sourceArray.length; i += n) {
            result[index++] = sourceArray[i];
        }
        
        return result;
    }
}