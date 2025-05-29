package Lambda_Challenge;
import java.util.function.Supplier;
public class MiniChallenge6 {
    


    public static void main(String[] args) {
        Supplier<String> supplier = () -> "i love java";

        String value = supplier.get();

        System.out.println(value);  // Output: i love java
    }
}

