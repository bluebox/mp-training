package corejava.july2_lambdaexpression;

import java.util.Scanner;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MiniChallenge3 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a string");
		String yourString=sc.nextLine();
		
		//challenge 3,4,5
		UnaryOperator<String> everySecondChar = str -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(str.charAt(i));
                }
            }
            return returnVal.toString();
        };
        
        
        String result = everySecondChar.apply(yourString);
        System.out.println("second characters in the given string are: " + result);

        //challenge 6
        
        Supplier<String> iLoveJava=()->"I love Java";
        System.out.println(iLoveJava.get());
        
        //challeng 7
        String supplierResult=iLoveJava.get();
        System.out.println(supplierResult);
		sc.close();
	
	
	}
}
