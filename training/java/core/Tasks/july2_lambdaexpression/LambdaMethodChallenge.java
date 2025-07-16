package corejava.july2_lambdaexpression;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class LambdaMethodChallenge {

	public static void main(String[] args) {
		String[] names = { "alice", "BOB", "Charlie", "deLhi" };

		
		List<Function<String, String>> transformations = List.of(
				String::toLowerCase,
				String::toUpperCase,
				s -> s.substring(0, 1).toUpperCase()+s.substring(1).toLowerCase() +" "+ new StringBuilder(s).reverse().toString().toLowerCase(),
				s -> new StringBuilder(s).reverse().toString(),
				LambdaMethodChallenge::addPrefix         
		        );

		        applyTransformations(names, transformations);
	}
	
	public static void applyTransformations(String[] names, List<Function<String, String>> functions) {
		for (Function<String, String> function : functions) {
			for (int i = 0; i < names.length; i++) {
				names[i] = names[i].transform(function);        
		    }
			System.out.println(Arrays.toString(names));
		}
	}
	
	public static String addPrefix(String name) {
        return "Mr./Ms. " + name;
    }
}
