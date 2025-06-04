import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MiniChallengesFunctionalInterfaces {
	public static void main(String[] args)
	{
		//1
		Consumer<String> printTheParts = (String sentence) -> {
		    String[] parts = sentence.split(" ");
		    for (String part : parts) {
		        System.out.println(part);
		    }
		};
		printTheParts.accept("This is a sample sentence");
		//2
		Function<String, StringBuilder> getOddChars = (source) -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    sb.append(source.charAt(i));
                }
            }
            return sb;
        };

        // Use the lambda (like calling a method)
        StringBuilder result = getOddChars.apply("abcdef");
        System.out.println(result);  // Output: "bdf"
        //3
        UnaryOperator<String> everySecondChar = source -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };
        //4
        String result1 = everySecondChar.apply("1234567890");
        System.out.println(result1); // Output: "24680"
        //5
        YourClass obj = new YourClass();
        String result2 = obj.everySecondCharacter(everySecondChar, "1234567890");
        System.out.println(result2);
        //6
        Supplier<String> iLoveJava = () -> "I love Java";
        //7
        System.out.println(iLoveJava.get());
        
	}
}
class YourClass {
    public String everySecondCharacter(UnaryOperator<String> func, String input) {
        return func.apply(input);
    }
}
