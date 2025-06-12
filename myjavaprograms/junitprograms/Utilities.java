package junitprograms;

public class Utilities {
    
    // Example method 1
    public String concatenateStrings(String str1, String str2) {
        return str1 + str2;
    }
    
    // Example method 2
    public int multiplyNumbers(int a, int b) {
        return a * b;
    }
    
    // Example method 3
    public boolean isEvenNumber(int number) {
        return number % 2 == 0;
    }
    
    // Example method 4
    public String reverseString(String input) {
        return new StringBuilder(input).reverse().toString();
    }
    
    // Example method 5
    public int[] sortArray(int[] array) {
        int[] sorted = array.clone();
        Arrays.sort(sorted);
        return sorted;
    }
}