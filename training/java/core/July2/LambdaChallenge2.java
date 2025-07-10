package July2;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class LambdaChallenge2 {
	public static void main(String[] args) {
		
		Function<String,String> everySecondChar = source ->{
			StringBuilder temp = new StringBuilder();
			for(int i=0;i<source.length();i++) {
				if(i%2==1) temp.append(source.charAt(i));
			}
			return temp.toString();
		};
		
		UnaryOperator<String> everySecondChar1 = source ->{
			StringBuilder temp = new StringBuilder();
			for(int i=0;i<source.length();i++) {
				if(i%2==1) temp.append(source.charAt(i));
			}
			return temp.toString();
		};
		
		String input = "abcdefghijklmnopqrstuvwxyz";
		System.out.println(everySecondChar.apply(input));
		System.out.println(everySecondChar1.apply(input));
		
		System.out.println(everySecondChar.apply("1234567890"));
		System.out.println(everySecondChar1.apply("1234567890"));
		
	}
}
