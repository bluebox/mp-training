package Day6;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class LambdaChallenges {
	public static void main(String[] args) {
		//challenge 1
		Consumer<String> printTheParts = sentence -> {
            String[] parts = sentence.split(" ");  // Split by space (or another delimiter)
            for (String part : parts) {
                System.out.println(part);
            }
        };
        
        //Challenge 2
        Function<String ,String> everySecondChar=source->{
        	StringBuilder returnVal=new StringBuilder();
        	for(int i=0;i<source.length();i++) {
        		if(i%2==1) {
        			returnVal.append(source.charAt(i));
        		}
        	}
			return returnVal.toString();
        };
        
        //Challenge 3
        UnaryOperator<String> everySecondChar1 = source -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };

        String input = "1234567890";
        String result = everySecondChar1.apply(input);
        System.out.println(result);
        
        //Challenge 5
        String ans=everySecondCharacter1(everySecondChar1,input);
        
        //Challenge 6
        Supplier<String> conv=()->  "I Love You";
        String a=conv.get();
        
        //Challenge 7
        System.out.println(a);
	}
	
	//Challenge 4
	public static String everySecondCharacter1(UnaryOperator<String> function, String value) {
        return function.apply(value);
    }
}
