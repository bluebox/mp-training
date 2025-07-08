package RegularExpressions;

public class Main {

	public static void main(String[] args) {
		String Data = """
				A regular expression,
				specified as a string,
				must first be compiled into an instance of this class.
				The resulting pattern can then be used to create a Matcher object,
				that can match arbitrary character sequences against the regular expression. 
				All of the state involved in performing a match resides in the matcher,
				so many matchers can share the same pattern.
				""";
		
		String[] SplitDataByNewLine = Data.split("\n");
		String[] SplitDataByComma = Data.split(",");
		String[] SplitDataBybe= Data.split("be");

		
		
		for(String s: SplitDataByNewLine) {
			System.out.println(s);			
		}
		System.out.println("-----------------------");			
		for(String s: SplitDataByComma) {
			System.out.println(s);			
		}
		System.out.println("-----------------------");			
		for(String s: SplitDataBybe) {
			System.out.println(s);			
		}
		
	}

}
