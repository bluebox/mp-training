package Lambda_Challenge;

import java.util.function.Supplier;

public class MiniChallenge7 {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "i love java";

        String supplierResult = supplier.get();

        System.out.println(supplierResult);  // Output: i love java
    }
}
