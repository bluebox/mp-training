package junitprograms;

public public class StringUtilNew {

    /**
     * Returns null if the input string length is odd, otherwise returns the input string.
     */
    public static String nullIfOddLength(String input) {
        if (input == null) return null;
        return input.length() % 2 == 1 ? null : input;
    }
}
 
