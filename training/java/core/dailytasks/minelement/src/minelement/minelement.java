package minelement;
	

import java.util.Scanner;
import java.util.Arrays;

public class minelement {
public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter a comma-delimited list of numbers:");
    String input = scanner.nextLine();
    System.out.println(readIntegers(input));
    
	
}

    public static int[] readIntegers(String input) {
       
        String[] numbersAsString = input.split(",");
        int[] intArray = new int[numbersAsString.length];

        for (int i = 0; i < numbersAsString.length; i++) {
            intArray[i] = Integer.parseInt(numbersAsString[i].trim()); // .trim() to handle spaces
        }
        return intArray;
    }

}
