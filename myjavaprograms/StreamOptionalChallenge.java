import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamOptionalChallenge{
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Find first name starting with 'D' (which doesn't exist here)
        Optional<String> optionalName = names.stream()
                                             .filter(name -> name.startsWith("B"))
                                             .findFirst();

        // Using orElse: returns fallback immediately
        String nameOrElse = optionalName.orElse(display("k"));
        System.out.println("Using orElse: " + nameOrElse);

        // Using orElseGet: uses Supplier to lazily generate fallback only if needed
        String nameOrElseGet = optionalName.orElseGet(() -> display("Generated Default Name")); 
//        System.out.println("Using orElseGet: " + nameOrElseGet);
    }
    
    public static String display(String s) {
    	System.out.println( s);
    	return "hi";
    }
}
