package LambdaChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

public class MethodLambdaMain {
	public static ArrayList<String> modifyStrings(String [] originalStrings, ArrayList<UnaryOperator<String>> operatorsList) {
		 
		 ArrayList<String> updatedstrings=new ArrayList<>();
		 for(int i=0; i<operatorsList.size(); i++) {
			 updatedstrings.add(operatorsList.get(i).apply(originalStrings[i]));
		 }
		 return updatedstrings;
	}

	public static void main(String [] args) {
		String [] originalStrings={"gopi", "rajesh", "ramanujan", "Harry Potter", "Hello"};
		ArrayList<UnaryOperator<String>> operatorsList= new ArrayList<>(List.of(
				(String str) -> str.toUpperCase(),
				(String str) -> {
					StringBuilder sb=new StringBuilder(str);
					sb.setCharAt((str.length()-1)/2, 'C');
					return sb.toString();
				},
				(String str) -> {
					StringBuilder sb=new StringBuilder(str).reverse();
					String lastName=sb.toString();
					String fullName=str+" "+lastName;
					return fullName;
				},
				(String str) -> str.substring((str.length())/2),
				(String str) -> str.concat(" This is concatenated.")
			));
		System.out.println("Original Strings are : "+Arrays.toString(originalStrings));
		ArrayList<String> output=modifyStrings(originalStrings, operatorsList);
		System.out.print("Modified Strings are : ");
		System.out.println(output);
	}
}
