package July2;

import java.util.function.Function;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

public class MethodAndLambdaChallenge {
	
	public static void applyTransformations(Function<String,String> func, List<String> names) {
		//names.forEach(input -> System.out.println(func.apply(input)));
		for (int i = 0; i < names.size(); i++) {
			names.set(i, func.apply(names.get(i))); 
		}
	}
	
	public static void main(String[] args) {

		List<String> names = Arrays.asList("Sahithi", "manaswini", "hari", "Krishna", "ARCHANA");

		Function<String, String> upperCase = name -> {
			return name.toUpperCase();
		};
		
		Function<String, String> middle = name -> {
			Random random = new Random();
			return name + " " + (char)('A' + random.nextInt(26)) + ".";
		};
		
		Function<String,String> last = name -> {
			int index = name.indexOf(" ");
			String temp = name.substring(0, index);
			StringBuilder res = new StringBuilder();
	        res.append(temp);
	        res.reverse();
			return name + " " + res.toString();
		};
		
		applyTransformations(upperCase,names);
		System.out.println(names);
		
		applyTransformations(middle,names);
		System.out.println(names);
		
		applyTransformations(last,names);
		System.out.println(names);
	}
}
